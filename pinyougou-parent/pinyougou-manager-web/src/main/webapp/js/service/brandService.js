app.service('brandService',function($http){
	//全部查询
	this.findAll=function(){
	return $http.get("../brand/findAll.do");
	}
	//分页
	this.findPage=function(page, rows){
		return $http.get('../brand/findPage.do?page=' + page + '&rows=' + rows);
	}
	//保存
	this.add=function(entity){
		return $http.post('../brand/add.do', entity);
	}
	//修改
	this.update=function(entity){
		return $http.post('../brand/update.do', entity);
	}
	//根据id查询
	this.findOne=function(id){
		return $http.get('../brand/findOne.do?id=' + id);
	}
	//批量删除
	this.deleteBrands=function(ids){
		return $.get('../brand/delete.do?ids=' + ids);
	}
	//条件查询
	this.search=function(page,rows,searchEntity){
		return $http.post('../brand/search.do?page='+page+'&rows='+rows,searchEntity);
	}
	//下拉列表
	this.findBrandList=function(){
		return $http.get('../brand/findBrandList.do');
	}
});
