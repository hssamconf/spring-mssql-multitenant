package lu.findl.multitenant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lu.findl.multitenant.entities.Account;
import lu.findl.multitenant.repositories.AccountRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(arg0);
		if (account == null) {
			throw new UsernameNotFoundException(String.format("Account [%s] inexistant", arg0));
		}
		return new AppUserDetails(account);
	}

}
