// Define the `MultitenantApp` module
var MultitenantApp = angular.module('MultitenantApp', ['ui.router', 'oc.lazyLoad']);

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
                            'controllers/LoginController.js'
                        ]
                    });
                }]
            }
        });

});