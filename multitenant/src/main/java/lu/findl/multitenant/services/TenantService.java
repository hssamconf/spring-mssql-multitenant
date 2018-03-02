package lu.findl.multitenant.services;

import com.google.common.collect.Lists;
import lu.findl.multitenant.entities.tenant.Customer;
import lu.findl.multitenant.repositories.tenant.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService implements ITenantService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer c) {
        return customerRepository.save(c);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer getCustomer(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return Lists.newArrayList(customerRepository.findAll());
    }

}
