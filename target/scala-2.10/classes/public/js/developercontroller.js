function AppsController($scope, $http) {
  $scope.apps = [];
  
  $http.get('/developer/apps').success(function(data, status, headers, config) {
    if(data.flag){
      $scope.apps = data.data;
    }else{
      alert("Couldn't get any app: " + data.errorMessage);
    }
  });

  $scope.deleteApp = function(id) {
    //delete app;
  }
}