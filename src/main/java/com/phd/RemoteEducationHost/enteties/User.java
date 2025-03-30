package com.phd.RemoteEducationHost.enteties;

import com.phd.RemoteEducationHost.enteties.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    Integer id;
    String email;
    String password;
    String firstName;
    String lastName;
    Date createAt;
    Date birthdayDate;
    List<Role> roles;

    public User(Integer id, String email, String password, String firstName, String lastName, Date createAt, Date birthdayDate, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createAt = createAt;
        this.birthdayDate = birthdayDate;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream()
//                .map(role -> (GrantedAuthority) role).toList();
        return new LinkedList<>();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
