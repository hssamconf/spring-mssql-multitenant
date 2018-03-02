package lu.findl.multitenant;

import lu.findl.multitenant.entities.central.Account;
import lu.findl.multitenant.entities.tenant.Customer;
import lu.findl.multitenant.services.ICentralService;
import lu.findl.multitenant.services.ITenantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultitenantApplicationTests {

    @Autowired
    private ICentralService centralService;
    @Autowired
    private ITenantService tenantService;


    public void contextLoads() {
        centralService.saveAccount(new Account("user_1", BCrypt.hashpw("Casa123++", BCrypt.gensalt()), "tenant1_multitenant", "Casa123++", "ROLE_USER"));
        centralService.saveAccount(new Account("user_2", BCrypt.hashpw("Casa123++", BCrypt.gensalt()), "tenant2_multitenant", "Casa123++", "ROLE_USER"));
        centralService.saveAccount(new Account("user_3", BCrypt.hashpw("Casa123++", BCrypt.gensalt()), "tenant3_multitenant", "Casa123++", "ROLE_USER"));
        centralService.saveAccount(new Account("user_4", BCrypt.hashpw("Casa123++", BCrypt.gensalt()), "tenant4_multitenant", "Casa123++", "ROLE_USER"));
    }

    @Test
    public void populateCustomers() {
        tenantService.saveCustomer(new Customer("ADIDAS"));
        tenantService.saveCustomer(new Customer("NIKE"));
        tenantService.saveCustomer(new Customer("REEBOK"));
        tenantService.saveCustomer(new Customer("ASICS"));
    }

}
