package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;

@Service
public class BrandServiceImpl implements BrandService {
	// 注入TbBrandMapper
	@Autowired
	private TbBrandMapper tbBrandMapper;

	// 查找全部品牌
	@Override
	public List<TbBrand> findAll() {
		return tbBrandMapper.selectByExample(null);
	}

	// 分页列表
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		// 应用myBatis的分页插件
		PageHelper.startPage(pageNum, pageSize);
		// 进行查询
		Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	// 添加功能
	@Override
	public void addBrand(TbBrand brand) {
		tbBrandMapper.insert(brand);

	}

	// 根据id查询
	@Override
	public TbBrand findOne(long id) {
		TbBrand brand = tbBrandMapper.selectByPrimaryKey(id);
		return brand;
	}

	// 根据id修改数据
	@Override
	public void update(TbBrand brand) {
		tbBrandMapper.updateByPrimaryKey(brand);

	}

	// 删除
	@Override
	public void delete(long[] ids) {
		if (ids != null) {
			for (long id : ids) {
				tbBrandMapper.deleteByPrimaryKey(id);
			}
		}

	}
	//条件查询
	@Override
	public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
		//开始分页
		PageHelper.startPage(pageNum, pageSize);
		//添加判断条件
		TbBrandExample example= new TbBrandExample();
		Criteria criteria = example.createCriteria();
		//判断品牌名的条件是否存在
		if(brand.getName()!=null) {
			criteria.andNameLike("%"+brand.getName()+"%");
		}
		if(brand.getFirstChar()!=null) {
			criteria.andFirstCharLike("%"+brand.getFirstChar()+"%");
		}
		
		//条用方法
		Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getResult());
	}
	/**
	 * 下拉列表
	 */
	@Override
	public List<Map> findBrandList() {
		return tbBrandMapper.findBrandList();
	}

}
