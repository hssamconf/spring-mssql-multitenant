package lu.findl.multitenant.persistence.multitenant;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MultiTenantDataSourceConfig {

    @Bean
    @ConfigurationProperties("tenant.datasource")
    public DataSourceProperties tenantDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "tenantDataSource")
    @ConfigurationProperties("tenant.datasource")
    public DataSource tenantDataSource() {
        System.out.println("IN tenantDataSource");

        return tenantDataSourceProperties().initializeDataSourceBuilder().build();
    }

    /*Tenant 1 DataSource*/
    @Bean
    @ConfigurationProperties("tenant_1.datasource")
    public DataSourceProperties tenant1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "tenant1DataSource")
    @ConfigurationProperties("tenant_1.datasource")
    public DataSource dataSource1() {
        System.out.println("IN dataSource1");

        return tenant1DataSourceProperties().initializeDataSourceBuilder().build();
    }

    /*Tenant 2 DataSource*/
    @Bean
    @ConfigurationProperties("tenant_2.datasource")
    public DataSourceProperties tenant2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "tenant2DataSource")
    @ConfigurationProperties("tenant_2.datasource")
    public DataSource dataSource2() {
        System.out.println("IN dataSource2");

        return tenant2DataSourceProperties().initializeDataSourceBuilder().build();
    }
}
