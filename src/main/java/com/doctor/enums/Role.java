package com.doctor.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    ROLE_DOCTOR(List.of(
            "doctor:read",
            "doctor:update",
            "doctor:create",
            "doctor:delete"
    )),
    ROLE_PATIENT(List.of(
            "patient:read",
            "patient:create",
            "patient:update",
            "patient:delete"
    ));

    @Getter
    private final List<String> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority(this.name())); // Adds the role itself
        return authorities;
    }
}