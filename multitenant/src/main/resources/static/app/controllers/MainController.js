// Define the `MainController` controller on the `MultitenantApp` module
angular.module('MultitenantApp').controller('MainController', function MainController($state, $scope, AUTH_EVENTS, toastr) {
    console.log("Init MainController");

    $scope.app = {};
    $scope.LoginController = {};
    $scope.AccueilController = {};

    $scope.$on(AUTH_EVENTS.notAuthorized, function (event, args) {
        toastr.warning('Your are not authorized !', 'Warning');
    });

    $scope.$on(AUTH_EVENTS.fatalError, function (event, args) {
        toastr.error('FATAL ERROR', 'Error');
    });

});