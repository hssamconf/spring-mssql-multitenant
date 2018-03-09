package lu.findl.multitenant.persistence.multitenant;

import lu.findl.multitenant.entities.central.Account;
import lu.findl.multitenant.security.AppUserDetails;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
    private static final String DEFAULT_TENANT_ID = "tenant_default";

    @Override
    public String resolveCurrentTenantIdentifier() {
        try {
            //System.out.println("IN CurrentTenantIdentifierResolverImpl.resolveCurrentTenantIdentifier");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account account = ((AppUserDetails) auth.getPrincipal()).getAccount();
            //System.out.println("CurrentTenantIdentifierResolverImpl.resolveCurrentTenantIdentifier => " + account.getDatabaseName());
            return account.getDatabaseName();
        } catch (Exception e) {
            //System.out.println("CurrentTenantIdentifierResolverImpl.resolveCurrentTenantIdentifier => " + DEFAULT_TENANT_ID);
            return DEFAULT_TENANT_ID;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        //System.out.println("IN CurrentTenantIdentifierResolverImpl.validateExistingCurrentSessions => true");
        return true;
    }
}
