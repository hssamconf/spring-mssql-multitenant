// Define the `LoginController` controller on the `MultitenantApp` module
angular.module('MultitenantApp').controller('LoginController', function LoginController($scope, API_URL, $http, $httpParamSerializerJQLike) {
    console.log("Init LoginController");
    var LoginController = $scope.LoginController;
    //init user object which contains credentials(username and password)
    LoginController.user = {};

    LoginController.login = function (isValid) {
        if (isValid) {
            $http({
                url: API_URL + '/auth/login',
                method: 'POST',
                data: $httpParamSerializerJQLike(LoginController.user),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
                .then(function (res) {
                    //$state.go('accueil');
                    alert("SUCCESS");
                })
                .catch(function (res) {
                    if (res.status === 401) {
                        alert("401 ERROR");
                    }
                });

        } else {
            alert("FORM ERROR");
        }
    };

    LoginController.logout = function () {
        $http.post('/auth/logout', {});
    };

});