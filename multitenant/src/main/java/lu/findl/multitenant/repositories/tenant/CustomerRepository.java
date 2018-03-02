package lu.findl.multitenant.repositories.tenant;

import lu.findl.multitenant.entities.tenant.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByName(String username);

}
