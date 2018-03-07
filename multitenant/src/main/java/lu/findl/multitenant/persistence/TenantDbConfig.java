package lu.findl.multitenant.persistence;

import lu.findl.multitenant.entities.tenant.Customer;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        transactionManagerRef = "tenantTransactionManager",
        entityManagerFactoryRef = "tenantEntityManager",
        basePackages = "lu.findl.multitenant.repositories.tenant"
)
public class TenantDbConfig {

    @Autowired
    private MultiTenantConnectionProvider multiTenantConnectionProvider;
    @Autowired
    private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;
    @Autowired
    private DataSource dataSource;

    // getting hibernate properties from hibernate.properties
    private Map<String, Object> hibernateProperties() {

        Resource resource = new ClassPathResource("hibernate.properties");

        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey().toString(),
                            e -> e.getValue())
                    );
        } catch (IOException e) {
            return new HashMap<String, Object>();
        }
    }

    @Bean(name = "tenantEntityManager")
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> hp = hibernateProperties();

        hp.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        hp.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        hp.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
        System.out.println("IN TenantDbConfig.LocalContainerEntityManagerFactoryBean");
        return builder
                .dataSource(dataSource)
                .packages(Customer.class)
                .properties(hp)
                .persistenceUnit("tenantPU")
                .build();
    }

    @Bean(name = "tenantTransactionManager")
    public PlatformTransactionManager tenantTransactionManager(@Qualifier("tenantEntityManager") EntityManagerFactory entityManagerFactory) {
        System.out.println("IN TenantDbConfig.tenantTransactionManager");
        return new JpaTransactionManager(entityManagerFactory);
    }
}