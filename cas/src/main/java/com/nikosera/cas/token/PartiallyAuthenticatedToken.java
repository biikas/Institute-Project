package com.nikosera.cas.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class PartiallyAuthenticatedToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 520L;

    private Object principal;
    private Object credentials;
    @Getter
    private String token;
    @Getter
    @Setter
    private boolean isFirstTimeTotpAuthentication;

    public PartiallyAuthenticatedToken(String token) {
        super(null);
        this.token = token;
        super.setAuthenticated(false);
    }

    public PartiallyAuthenticatedToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, boolean isFirstTimeTotpAuthentication) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.isFirstTimeTotpAuthentication = isFirstTimeTotpAuthentication;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}

