package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.grouppojo.Specification;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	
	@Autowired
	private TbSpecificationOptionMapper TbSpecificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		if(specification!=null) {
			//插入规则名
			TbSpecification tbspecification = specification.getSpecification();
			specificationMapper.insert(tbspecification);
		
			//插入规则选项
			List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
			for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
				//设置specId，在规则插入的时候返回主键
				tbSpecificationOption.setSpecId(tbspecification.getId());
				TbSpecificationOptionMapper.insert(tbSpecificationOption);
			}
		}
	}
		

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		//修改的时候先修改规则表名称‘'
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		//修改规则表后，由于很难判定规则选项的更新，所以先清空规则选项，然后重新保存
		//获得条件
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(tbSpecification.getId());
		TbSpecificationOptionMapper.deleteByExample(example);
		//删除后重新保存
		List<TbSpecificationOption> optionList = specification.getSpecificationOptionList();
		for (TbSpecificationOption tbSpecificationOption : optionList) {
			tbSpecificationOption.setSpecId(tbSpecification.getId());
			TbSpecificationOptionMapper.insert(tbSpecificationOption);
		}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		//根据id查询。查询选项的时候同时查规则选项
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		//规则的id就是规则选择项的外键，
		//获得条件
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(id);
		//查询规则选项
		List<TbSpecificationOption> list = TbSpecificationOptionMapper.selectByExample(example);
		//赋值给规则和规则选项的实体类
		Specification specification = new Specification();
		specification.setSpecification(tbSpecification);
		specification.setSpecificationOptionList(list);
		//返回
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		if(ids!=null) {
			//当删除规则的时候。同时也要删除规则选择项
			for(Long id:ids){
				specificationMapper.deleteByPrimaryKey(id);
				TbSpecificationOptionExample example=new TbSpecificationOptionExample();
				com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
				criteria.andSpecIdEqualTo(id);
				//规则的id就是规则选择项的外键，
				TbSpecificationOptionMapper.deleteByExample(example);
			}		
		}
	}
	
	/**
	 * 条件查询分页列表
	 */
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
		/**
		 * 下拉列表
		 */
		@Override
		public List<Map> findSpecificationList() {
			return specificationMapper.findSpecificationList();
		}
	
}
