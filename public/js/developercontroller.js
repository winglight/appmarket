function HotController($scope) {
  $scope.page = {};
  $scope.hotapps = {};

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