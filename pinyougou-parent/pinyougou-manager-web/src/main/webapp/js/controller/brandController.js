app.controller('brandController', function($scope, brandService, $controller) {

	$controller('baseController', {
		$scope : $scope
	});// 继承

	$scope.findAll = function() {
		brandService.findAll().success(function(data) {
			$scope.list = data;
		});
	}

	// 分页
	$scope.findPage = function(page, rows) {
		brandService.findPage(page, rows).success(function(data) {
			$scope.list = data.rows;
			$scope.paginationConf.totalItems = data.total; // 更新总记录数
		});
	}
	// 添加保存
	$scope.save = function() {
		// 如果id存在，就执行修改方法，不存在就修改保存
		var object = null;
		if ($scope.entity.id != null) {
			object = brandService.update($scope.entity);
		} else {
			object = brandService.add($scope.entity);
		}
		object.success(function(data) {
			if (data.success) {
				alert(data.message);
				// 重新查询
				$scope.reloadList();
			} else {
				alert(data.message);
			}
		});
	}
	// 根据id查询
	$scope.findOne = function(id) {
		brandService.findOne(id).success(function(data) {
			$scope.entity = data;
		})
	}
	// 删除

	// 批量删除
	$scope.deleteBrands = function() {
		brandService.deleteBrands($scope.selectIds).success(function(data) {
			if (data.success) {
				alert(data.message);
				// 重新查询
				$scope.reloadList();
			} else {
				alert(data.message);
			}
		});
	}
	// 条件查询
	// 定义查询对象
	$scope.searchEntity = {};
	$scope.search = function(page, rows) {
		brandService.search(page, rows, $scope.searchEntity).success(
				function(data) {
					$scope.list = data.rows;
					$scope.paginationConf.totalItems = data.total; // 更新总记录数
				});
	}
});