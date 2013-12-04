function AppsController($scope, $http) {
  $scope.apps = [];
  $scope.categories = [];
  $scope.category = {};
  
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
      $scope.category = $scope.categories[0].id;
    }else{
      alert("Couldn't get any category");
    }
  });
  
  $scope.addApp = function(item){
    $scope.apps.splice(0, 0, item);
    $scope.newItem = null;
    $scope.$apply();
  }

  $scope.deleteApp = function(row) {
    //delete app;
    var app = $scope.apps[row];
    $http.delete('/developer/' + app.id + "/delete").success(function(data, status, headers, config) {
    if(data == "success"){
      $scope.apps.splice(row, 1);
      alert("Successfully to delete");
    }else{
      alert(data);
    }
  });
  }
}