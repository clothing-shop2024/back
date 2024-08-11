package com.project.back.common.object;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {
    private String id;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean status;
    private String joinPath;

    public CustomOAuth2User(String id, Map<String, Object> attributes, boolean status, String joinPath) {
        this.id = id;
        this.attributes = attributes;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        this.status = status;
        this.joinPath = joinPath;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.id;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getJoinPath() {
        return this.joinPath;
    }
}