package com.pinyougou.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;
import entity.Result;

@RestController
@RequestMapping("/brand")
public class BrandContraller {
	// 注入BrandService
	@Reference
	private BrandService brandService;

	/**
	 * 查找所有的品牌
	 */
	@RequestMapping("/findAll")
	public List<TbBrand> fineAll() {
		return brandService.findAll();
	}

	/**
	 * 分页查询
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows) {
		return brandService.findPage(page, rows);
	}

	/**
	 * 添加功能
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbBrand brand) {

		try {
			brandService.addBrand(brand);
			return new Result(true, "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "添加失败！");
		}
	}

	/**
	 * 修改功能
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbBrand brand) {
		try {
			brandService.update(brand);
			return new Result(true, "修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改成功！");
		}
	}

	/**
	 * 单条查询
	 */
	@RequestMapping("/findOne")
	public TbBrand findOne(long id) {
		return brandService.findOne(id);

	}

	/**
	 * 批量删除
	 */
	@RequestMapping("/delete")
	public Result delete(long[] ids) {
		try {
			brandService.delete(ids);
			return new Result(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除成功！");
		}
	}

	/**
	 * 条件查询
	 */
	@RequestMapping("/search")
	public PageResult search(int page, int rows, @RequestBody TbBrand brand) {

		return brandService.findPage(brand, page, rows);

	}
	/**
	 * 下拉列表
	 */
	@RequestMapping("/findBrandList")
	public List<Map> findBrandList(){
		return brandService.findBrandList();
	}
}
