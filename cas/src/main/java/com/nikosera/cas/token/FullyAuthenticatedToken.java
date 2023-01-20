package com.nikosera.cas.token;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class FullyAuthenticatedToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 520L;

    private Object principal;
    private Object credentials;
    @Getter
    private String token;

    public FullyAuthenticatedToken(String token) {
        super(null);
        this.token = token;
        super.setAuthenticated(false);
    }

    public FullyAuthenticatedToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
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
