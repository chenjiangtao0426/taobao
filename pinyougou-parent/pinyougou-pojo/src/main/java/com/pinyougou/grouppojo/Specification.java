package com.pinyougou.grouppojo;

import java.io.Serializable;
import java.util.List;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
/**
 * 包涵规则表和选则项的类
 * @author Administrator
 *
 */
public class Specification implements Serializable{
	private TbSpecification specification;//规则表
	private List<TbSpecificationOption> specificationOptionList;//规则选项表的集合
	
	public TbSpecification getSpecification() {
		return specification;
	}
	public void setSpecification(TbSpecification specification) {
		this.specification = specification;
	}
	public List<TbSpecificationOption> getSpecificationOptionList() {
		return specificationOptionList;
	}
	public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
		this.specificationOptionList = specificationOptionList;
	}
	@Override
	public String toString() {
		return "Specification [specification=" + specification + ", specificationOptionList=" + specificationOptionList
				+ "]";
	}
	
	
}
