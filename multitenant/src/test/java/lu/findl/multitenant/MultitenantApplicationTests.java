package lu.findl.multitenant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

		metier.saveAccount(new Account("user_1", "Casa123++", "tenant1_multitenant", "Casa123++"));
		metier.saveAccount(new Account("user_2", "Casa123++", "tenant2_multitenant", "Casa123++"));
		metier.saveAccount(new Account("user_3", "Casa123++", "tenant3_multitenant", "Casa123++"));
		metier.saveAccount(new Account("user_4", "Casa123++", "tenant4_multitenant", "Casa123++"));

	}

}
