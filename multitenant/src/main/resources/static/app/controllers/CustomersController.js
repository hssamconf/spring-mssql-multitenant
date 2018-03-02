// Define the `CustomersController` controller on the `MultitenantApp` module
angular.module('MultitenantApp').controller('CustomersController', function CustomersController($scope, customers) {
    console.log("Init CustomersController");

    var CustomersController = $scope.CustomersController;
    CustomersController.customers = angular.copy(customers.data.data);
});