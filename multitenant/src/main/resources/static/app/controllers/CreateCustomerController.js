// Define the `CreateCustomerController` controller on the `MultitenantApp` module
angular.module('MultitenantApp').controller('CreateCustomerController', function CreateCustomerController($state, $scope) {
    console.log("Init CreateCustomerController");
    var CreateCustomerController = $scope.CreateCustomerController;
    CreateCustomerController.customer = {};

    CreateCustomerController.create = function (isValid) {
        if (isValid) {
            /* $http({
                 url: API_URL + '/auth/login',
                 method: 'POST',
                 data: $httpParamSerializerJQLike(CreateCustomerController.user),
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
                 });*/
            alert("form correct");
        } else {
            alert("FORM ERROR");
        }
    };
});