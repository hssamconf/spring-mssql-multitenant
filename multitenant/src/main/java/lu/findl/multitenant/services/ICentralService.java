package lu.findl.multitenant.services;

import java.util.List;

import lu.findl.multitenant.entities.central.Account;

public interface ICentralService {

	Account saveAccount(Account a);
	Account getAccount(Long id);
	Account getAccount(String username);
	List<Account> getAllAccounts();
	
}
