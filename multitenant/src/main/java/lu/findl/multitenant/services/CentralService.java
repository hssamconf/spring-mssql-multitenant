package lu.findl.multitenant.services;

import com.google.common.collect.Lists;
import lu.findl.multitenant.entities.central.Account;
import lu.findl.multitenant.repositories.central.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentralService implements ICentralService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account a) {
        return accountRepository.save(a);
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public Account getAccount(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAllAccounts() {
        return Lists.newArrayList(accountRepository.findAll());
    }

    @Override
    public Account getAccountByTenant(String databaseName) {
        return accountRepository.findByDatabaseName(databaseName);
    }
}
