package lu.findl.multitenant.repositories;

import org.springframework.data.repository.CrudRepository;

import lu.findl.multitenant.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByUsername(String username);

}
