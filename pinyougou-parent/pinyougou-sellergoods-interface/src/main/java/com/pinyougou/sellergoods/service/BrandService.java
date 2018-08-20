package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

/**
 * 品牌的接口
 * 
 * @author Administrator
 *
 */
public interface BrandService {
	/**
	 * 返回所有品牌
	 * 
	 * @return
	 */
	public List<TbBrand> findAll();

	/**
	 * 分页列表
	 */
	public PageResult findPage(int pageNum, int pageSize);

	/**
	 * 添加功能
	 */
	public void addBrand(TbBrand brand);

	/**
	 * 修改功能.先查出来，在修改
	 */
	public TbBrand findOne(long id);

	public void update(TbBrand brand);

	/**
	 * 批量删除
	 */
	public void delete(long[] ids);
	/**
	 * 条件查询
	 */
	public PageResult findPage(TbBrand brand,int pageNum, int pageSize);
		
	/**
	 * 下拉列表
	 */
	public List<Map> findBrandList();

}
