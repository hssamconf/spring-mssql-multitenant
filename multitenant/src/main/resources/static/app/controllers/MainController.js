// Define the `MainController` controller on the `MultitenantApp` module
angular.module('MultitenantApp').controller('MainController', function MainController($scope) {
    console.log("Init MainController");

    $scope.app = {};
    $scope.LoginController = {};
    $scope.AccueilController = {};

});