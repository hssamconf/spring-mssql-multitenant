// Define the `MultitenantApp` module
var MultitenantApp = angular.module('MultitenantApp', ['ngAnimate', 'ui.router', 'oc.lazyLoad', 'toastr']);

MultitenantApp.constant('API_URL', '');

MultitenantApp.constant('AUTH_EVENTS', {
    notAuthorized: 'auth-not-authorized',
    fatalError: 'auth-fatal-error'
});

// angular toaster config
MultitenantApp.config(function (toastrConfig) {
    angular.extend(toastrConfig, {
        closeButton: true,
        progressBar: true,
        preventDuplicates: true,
        newestOnTop: true,
        target: 'body'
    });
});

// ocLazyLoad Config
MultitenantApp.config(['$ocLazyLoadProvider', function ($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        debug: true
    });
}]);

// ui router Config
MultitenantApp.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/accueil.html');

    $stateProvider
        .state('accueil', {
            url: "/accueil.html",
            templateUrl: "views/accueil.html",
            controller: "AccueilController",
            resolve: {
                user: function ($http) {
                    return $http.get("/api/whoami");
                },
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MultitenantApp',
                        files: [
                            'controllers/AccueilController.js'
                        ]
                    });
                }]
            }
        })
        .state('login', {
            url: "/login.html",
            templateUrl: "views/login.html",
            controller: "LoginController",
            resolve: {
                deps: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name: 'MultitenantApp',
                        files: [
                            'assets/css/login.css',
                            'controllers/LoginController.js'
                        ]
                    });
                }]
            }
        });

});