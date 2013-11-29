function CategoryController($scope, $http) {
  $scope.page = {};
  $scope.page.currentPage = 1;
  $scope.apps = [];
  $scope.category = {};

  $http.get('/categories').success(function(data, status, headers, config) {
    if(data.flag){
      $scope.categories = data.data;
      $scope.currentCategory = $scope.categories[0];
    }else{
      alert("Couldn't get any category");
    }
  });
  
  $scope.$watch('[currentCategory, page]', function(){
      if($scope.currentCategory){
	  	$scope.refreshApp();
	  }
  }, true);
  
  $scope.refreshApp = function() {
  		$http.get('/apps/category/' + $scope.currentCategory.id + "/" + $scope.page.currentPage).success(function(data, status, headers, config) {
		    if(data.flag){
		      $scope.page = data.page;
		      $scope.apps = data.data;
		    }else{
		      alert("Couldn't get any app: " + data.errorMessage);
		    }
	    });
  };
  
  $scope.setCurrentCategory = function(category) {
    $scope.currentCategory = category;
    return true;
    //go to currentPage;
  }
  
  $scope.prevPage = function() {
    $scope.page.currentPage = $scope.page.currentPage - 1;
    //go to currentPage;
  }
  
  $scope.nextPage = function() {
    $scope.page.currentPage = $scope.page.currentPage + 1;
    //go to currentPage;
  }

  $scope.gotoPage = function() {
    ;
  };
  
}

