function HotController($scope, $http) {
  $scope.page = {};
  $scope.page.currentPage = 1;
  $scope.hotapps = {};

  $http.get('/apps/hots/' + $scope.page.currentPage).success(function(data, status, headers, config) {
    if(data.flag){
      $scope.page = data.page;
      $scope.hotapps = data.data;
      
      $scope.$evalAsync(refreshProduct());
    }else{
      alert("Couldn't get any app: " + data.errorMessage);
    }
  });
  
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

