package lu.findl.multitenant.services;

import lu.findl.multitenant.entities.tenant.Customer;

import java.util.List;

public interface ITenantService {

    Customer saveCustomer(Customer c);
    Customer getCustomer(Long id);
    Customer getCustomer(String name);
    List<Customer> getAllCustomers();

}
