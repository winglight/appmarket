function AppsController($scope, $http) {
  $scope.apps = [];
  $scope.categories = [];
  
  $http.get('/developer/apps').success(function(data, status, headers, config) {
    if(data.flag){
      $scope.apps = data.data;
    }else{
      alert("Couldn't get any app: " + data.errorMessage);
    }
  });
  
  $http.get('/categories').success(function(data, status, headers, config) {
    if(data.flag){
      $scope.categories = data.data;
    }else{
      alert("Couldn't get any category");
    }
  });

  $scope.deleteApp = function(id) {
    //delete app;
  }
}