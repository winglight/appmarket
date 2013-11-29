function NewestController($scope, $http) {
  $scope.page = {};
  $scope.page.currentPage = 1;
  $scope.hotapps = {};

  $http.get('/apps/newest/' + $scope.page.currentPage).success(function(data, status, headers, config) {
    if(data.flag){
      $scope.page = data.page;
      $scope.apps = data.data;
      
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

