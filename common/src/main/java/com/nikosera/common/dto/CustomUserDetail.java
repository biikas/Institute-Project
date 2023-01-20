package com.nikosera.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikosera.entity.ApplicationUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class CustomUserDetail implements OAuth2User, UserDetails {
    private final ApplicationUser currentUser;
    private Map<String, Object> attributes;

    @Setter
    @Getter
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomUserDetail create(ApplicationUser user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new CustomUserDetail(user);
    }

    public static CustomUserDetail create(ApplicationUser user, Map<String, Object> attributes) {
        CustomUserDetail userPrincipal = CustomUserDetail.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public CustomUserDetail(ApplicationUser currentUser) {
        this.currentUser = currentUser;
    }

    public ApplicationUser getCurrentUser() {
        return this.currentUser;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return currentUser.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.currentUser.getActive() != 'Y';
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.currentUser.getActive() != 'Y';
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.currentUser.getPassword();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.currentUser.getActive() == 'Y';
    }

    @JsonIgnore
    public boolean isMfaEnabled() {
        return this.currentUser.getIsMfaEnabled() == 'Y';
    }


    @JsonIgnore
    public boolean hasForcedMfa() {
        return this.currentUser.getHasEnforcedMfa() == 'Y';
    }


    @JsonIgnore
    public String getSecretKey() {
        return this.currentUser.getSecret();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return this.currentUser.getName();
    }

    public Long getId() {
        return this.currentUser.getId();
    }
}
