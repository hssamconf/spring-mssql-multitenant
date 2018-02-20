angular.module('MultitenantApp').factory('AuthInterceptor', function ($rootScope, $q, AUTH_EVENTS) {
    console.log('init auth interceptor');
    return {
        responseError: function (response) {
            console.log("intercept error :"+response.status);
            $rootScope.$broadcast({
                403: AUTH_EVENTS.notAuthorized,
                500: AUTH_EVENTS.fatalError
            }[response.status], response);
            return $q.reject(response);
        }
    };
}).config(function ($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
});