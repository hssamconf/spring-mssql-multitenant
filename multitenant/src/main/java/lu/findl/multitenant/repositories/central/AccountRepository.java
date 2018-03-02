package lu.findl.multitenant.repositories.central;

import org.springframework.data.repository.CrudRepository;

import lu.findl.multitenant.entities.central.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByUsername(String username);

}
