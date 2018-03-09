// Define the `CreateCustomerController` controller on the `MultitenantApp` module
angular.module('MultitenantApp').controller('CreateCustomerController', function CreateCustomerController($state, $scope, API_URL, $http) {
    console.log("Init CreateCustomerController");
    var CreateCustomerController = $scope.CreateCustomerController;
    CreateCustomerController.customer = {};

    CreateCustomerController.create = function (isValid) {
        if (isValid) {
            $http.post(API_URL + '/api/createCustomer', CreateCustomerController.customer)
                .then(function (res) {
                    console.log(res)
                }).catch(function (res) {
                if (res.status === 401) {
                    toastr.warning('Credentials Incorrect!', 'Warning');
                }
            });
        } else {
            alert("FORM ERROR");
        }
    };
});