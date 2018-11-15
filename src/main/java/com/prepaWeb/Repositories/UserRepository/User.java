package com.prepaWeb.Repositories.UserRepository;



import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(value="users_ihm")
public class User extends SecurityProperties.User implements UserDetails {
    @PrimaryKeyColumn(
            name = "email",ordinal = 0, type = PrimaryKeyType.PARTITIONED
    )
    private String email;
    @Column
    private String password;
    @Column()
    private String roles;


    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (String role : getRole())
        {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getRole() {
        List<String> listRoles = new ArrayList<String>();
        listRoles.add(roles);
        return listRoles;
    }

}
