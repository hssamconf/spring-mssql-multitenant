package lu.findl.multitenant.persistence;

import lu.findl.multitenant.entities.central.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        transactionManagerRef = "centralTransactionManager",
        entityManagerFactoryRef = "centralEntityManager",
        basePackages = "lu.findl.multitenant.repositories.central"
)
public class CentralDbConfig {

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

    @Bean
    @Primary
    @ConfigurationProperties("central.datasource")
    public DataSourceProperties centralDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("central.datasource")
    public DataSource centralDataSource() {
        return centralDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "centralEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean centralEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(centralDataSource())
                .packages(Account.class)
                .properties(hibernateProperties())
                .persistenceUnit("centralPU")
                .build();
    }

    @Bean(name = "centralTransactionManager")
    @Primary
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("centralEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}