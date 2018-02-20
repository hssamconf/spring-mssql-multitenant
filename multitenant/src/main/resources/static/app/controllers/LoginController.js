// Define the `LoginController` controller on the `MultitenantApp` module
angular.module('MultitenantApp').controller('LoginController', function LoginController($state, $scope, API_URL, $http, $httpParamSerializerJQLike, toastr) {
    console.log("Init LoginController");
    var LoginController = $scope.LoginController;

    //init user object which contains credentials(username and password)
    LoginController.user = {username: "user_1", password: "Casa123++"};

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
                    $state.go('accueil');
                })
                .catch(function (res) {
                    if (res.status === 401) {
                        toastr.warning('Credentials Incorrect!', 'Warning');
                    }
                });

        } else {
            alert("FORM ERROR");
        }
    };
});