package com.phd.RemoteEducationHost.enteties.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STUDENT, TEACHER, ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
