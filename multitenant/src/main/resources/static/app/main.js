// Define the `MultitenantApp` module
var MultitenantApp = angular.module('MultitenantApp', []);

// Define the `MainController` controller on the `MultitenantApp` module
MultitenantApp.controller('MainController', function PhoneListController($scope) {
    $scope.angularVersion = "1.6.7";
    $scope.bootstrapVersion = "4.0.0";
});