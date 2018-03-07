package lu.findl.multitenant.services;

import lu.findl.multitenant.entities.central.Account;

import java.util.List;

public interface ICentralService {

    Account saveAccount(Account a);
    Account getAccount(Long id);
    Account getAccount(String username);
    Account getAccountByTenant(String databaseName);
    List<Account> getAllAccounts();

}
