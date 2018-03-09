package lu.findl.multitenant.persistence.multitenant;

import lu.findl.multitenant.entities.central.Account;
import lu.findl.multitenant.services.ICentralService;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private static final String DEFAULT_TENANT_ID = "tenant_default";
    private Map<String, DataSource> dataSourcesMap;
    private ICentralService centralService;

    @Autowired
    public MultiTenantConnectionProviderImpl(ICentralService centralService) {
        this.centralService = centralService;
    }

    @Bean
    @ConfigurationProperties("tenant.datasource")
    public DataSourceProperties tenantDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("tenant.datasource")
    public DataSource tenantDataSource() {
        //System.out.println("IN tenantDataSource");
        return tenantDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @PostConstruct
    public void load() {
        //System.out.println("IN MultiTenantConnectionProviderImpl.load");
        dataSourcesMap = new HashMap<>();
        dataSourcesMap.put(DEFAULT_TENANT_ID, tenantDataSource());
    }

    @Override
    protected DataSource selectAnyDataSource() {
        //System.out.println("MultiTenantConnectionProviderImpl.selectAnyDataSource");
        return dataSourcesMap.get(DEFAULT_TENANT_ID);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        //System.out.println("MultiTenantConnectionProviderImpl.selectDataSource" + tenantIdentifier);

        DataSource dt;
        if (dataSourcesMap.get(tenantIdentifier) != null) return dataSourcesMap.get(tenantIdentifier);
        else {
            //System.out.println("MultiTenantConnectionProviderImpl.selectDataSource --- Creating new datasource" + tenantIdentifier);
            dt = createDataSource(tenantIdentifier);
            dataSourcesMap.put(tenantIdentifier, dt);
            return dt;
        }
    }

    private DataSource createDataSource(String tenantIdentifier) {
        Account account = centralService.getAccountByTenant(tenantIdentifier);
        return DataSourceBuilder
                .create()
                .url("jdbc:sqlserver://192.168.1.29:1433;databaseName=" + account.getDatabaseName())
                .username("sa")
                .password(account.getDatabasePwd())
                .build();
    }
}
