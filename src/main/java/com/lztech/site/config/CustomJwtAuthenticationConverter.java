package com.lztech.site.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;

public class CustomJwtAuthenticationConverter extends JwtAuthenticationConverter {

    private final RealmRoleConverter realmRoleConverter;

    public CustomJwtAuthenticationConverter(RealmRoleConverter realmRoleConverter) {
        this.realmRoleConverter = realmRoleConverter;
    }

    @Override
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        return realmRoleConverter.convert(jwt);
    }
}
