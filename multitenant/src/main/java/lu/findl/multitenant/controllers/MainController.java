package lu.findl.multitenant.controllers;

import lu.findl.multitenant.entities.central.Account;
import lu.findl.multitenant.helpers.Reponse;
import lu.findl.multitenant.models.PostCreateCustomer;
import lu.findl.multitenant.security.AppUserDetails;
import lu.findl.multitenant.services.ICentralService;
import lu.findl.multitenant.services.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    @Value("${db.username}")
    private String DB_USERNAME;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.url}")
    private String DB_URL;

    @Autowired
    private ICentralService centralService;

    @Autowired
    private ITenantService tenantService;

    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    public ResponseEntity<?> whoami() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = ((AppUserDetails) auth.getPrincipal()).getAccount();

            Map<String, Object> res = new HashMap<>();
            res.put("idAccount", account.getId());
            res.put("username", account.getUsername());
            res.put("roleName", account.getRoleName());

            return ResponseEntity.status(HttpStatus.OK).body(new Reponse(0, res));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Reponse(0, tenantService.getAllCustomers()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody PostCreateCustomer post) {
        try {

            String sql = "restore database tenant_" + post.getName() + "\n" +
                    "from disk='default_multitenant.bak'\n" +
                    "WITH move 'default_multitenant' to  'C:\\Program Files\\Microsoft SQL Server\\MSSQL13.MSSQLSERVER\\MSSQL\\DATA\\tenant_" + post.getName() + ".mdf',\n" +
                    "move 'default_multitenant_log' to  'C:\\Program Files\\Microsoft SQL Server\\MSSQL13.MSSQLSERVER\\MSSQL\\DATA\\tenant_" + post.getName() + "_log.ldf'";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String crypt = BCrypt.hashpw(post.getPassword(), BCrypt.gensalt());

            centralService.saveAccount(
                    new Account(post.getName(), crypt, "tenant_" + post.getName(), "", "ROLE_USER")
            );

            return ResponseEntity.status(HttpStatus.OK).body(new Reponse(0, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
    }

}
