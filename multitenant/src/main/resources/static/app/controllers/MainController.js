// Define the `MainController` controller on the `MultitenantApp` module
angular.module('MultitenantApp').controller('MainController', function MainController($rootScope, $http, $state, $scope, AUTH_EVENTS, toastr) {
    console.log("Init MainController");

    $scope.MainController = {};
    $scope.LoginController = {};
    $scope.AccueilController = {};

    var MainController = $scope.MainController;

    // tell us if the user is authentified or no
    MainController.isAuthentified = false;

    MainController.logout = function () {
        $http.post('/auth/logout');
        $rootScope.user = null;
        MainController.isAuthentified = false;
        if ($state.current.name !== "login")
            $state.go("login");
    };

    $scope.$on(AUTH_EVENTS.notAuthorized, function (event, args) {
        toastr.warning('Your are not authorized !', 'Warning');
    });

    $scope.$on(AUTH_EVENTS.fatalError, function (event, args) {
        toastr.error('FATAL ERROR', 'Error');
    });

    $scope.$on(AUTH_EVENTS.authenticated, function (event, args) {
        MainController.isAuthentified = true;
        $rootScope.user = args.user;
    });

});