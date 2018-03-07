package lu.findl.multitenant.persistence.multitenant;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private static final String DEFAULT_TENANT_ID = "tenant_default";
    private Map<String, DataSource> map;

    @Resource
    private DataSource tenantDataSource;
    @Resource
    private DataSource tenant1DataSource;
    @Resource
    private DataSource tenant2DataSource;

    @PostConstruct
    public void load() {
        System.out.println("IN MultiTenantConnectionProviderImpl.load");
        map = new HashMap<>();
        map.put(DEFAULT_TENANT_ID, tenantDataSource);
        map.put("tenant_1", tenant1DataSource);
        map.put("tenant_2", tenant2DataSource);
    }

    @Override
    protected DataSource selectAnyDataSource() {
        System.out.println("MultiTenantConnectionProviderImpl.selectAnyDataSource");
        return map.get(DEFAULT_TENANT_ID);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        System.out.println("MultiTenantConnectionProviderImpl.selectDataSource");
        return map.get(tenantIdentifier);
    }
}
