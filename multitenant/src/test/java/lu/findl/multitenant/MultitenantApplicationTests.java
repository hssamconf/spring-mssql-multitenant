package lu.findl.multitenant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

import lu.findl.multitenant.entities.Account;
import lu.findl.multitenant.services.IMetier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultitenantApplicationTests {

	@Autowired
	private IMetier metier;

	@Test
	public void contextLoads() {
		String crypt = BCrypt.hashpw("Casa123++", BCrypt.gensalt());

		metier.saveAccount(new Account("user_1", BCrypt.hashpw("Casa123++", BCrypt.gensalt()), "tenant1_multitenant", "Casa123++","ROLE_USER"));
		metier.saveAccount(new Account("user_2", BCrypt.hashpw("Casa123++", BCrypt.gensalt()), "tenant2_multitenant", "Casa123++","ROLE_USER"));
		metier.saveAccount(new Account("user_3", BCrypt.hashpw("Casa123++", BCrypt.gensalt()), "tenant3_multitenant", "Casa123++","ROLE_USER"));
		metier.saveAccount(new Account("user_4", BCrypt.hashpw("Casa123++", BCrypt.gensalt()), "tenant4_multitenant", "Casa123++","ROLE_USER"));

	}

}
