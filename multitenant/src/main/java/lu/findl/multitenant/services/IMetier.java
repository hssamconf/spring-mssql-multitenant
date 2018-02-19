package lu.findl.multitenant.services;

import java.util.List;

import lu.findl.multitenant.entities.Account;

public interface IMetier {

	public Account saveAccount(Account a);
	public Account getAccount(Long id);
	public Account getAccount(String username);
	public List<Account> getAllAccounts();
	
}
