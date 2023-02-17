package com.f1soft.campaign.web.config.provider;

import com.f1soft.campaign.entities.model.ApplicationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AuthApplicationUser implements UserDetails {

    private final ApplicationUser user;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String channel;

    public AuthApplicationUser(ApplicationUser user) {
        this(user, new ArrayList<GrantedAuthority>(), "");
    }

    public AuthApplicationUser(ApplicationUser user, Collection<? extends GrantedAuthority> authorities) {
        this(user, authorities, "");
    }

    public AuthApplicationUser(ApplicationUser user, String channel) {
        this(user, new ArrayList<GrantedAuthority>(), channel);
    }

    public AuthApplicationUser(ApplicationUser user, Collection<? extends GrantedAuthority> authorities, String channel) {
        this.user = user;
        this.authorities = authorities;
        this.channel = channel;
    }

    @JsonIgnore
    public Long getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive() == 'Y';
    }

    @JsonIgnore
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApplicationUser getUser() {
        return user;
    }
}
