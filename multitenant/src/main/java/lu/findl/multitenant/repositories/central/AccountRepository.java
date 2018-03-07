package lu.findl.multitenant.repositories.central;

import lu.findl.multitenant.entities.central.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUsername(String username);
    Account findByDatabaseName(String tenantName);

}
