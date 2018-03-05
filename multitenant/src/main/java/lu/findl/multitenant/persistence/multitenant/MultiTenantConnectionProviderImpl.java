package lu.findl.multitenant.persistence.multitenant;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import javax.sql.DataSource;

public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Override
    protected DataSource selectAnyDataSource() {
        return null;
    }

    @Override
    protected DataSource selectDataSource(String s) {
        return null;
    }
}
