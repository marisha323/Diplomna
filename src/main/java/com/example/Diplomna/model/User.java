package com.example.Diplomna.model;

import com.example.Diplomna.classValid.AddImgUser;
import com.example.Diplomna.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder

@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String userName;
    @Column(unique = true)
    private String email;
    @Column(nullable = true)
    private String password;
    @Column(nullable = true)
    private String externalId;
    private String photoUrl;
    private boolean isActivated;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Enumerated (EnumType.STRING)
    private Role role;

    public User(Long userId) {
        this.id = userId;

    }

    public User() {
        this.role = Role.USER;
    }
    public Long getId() {
        return id;
    }



    public String getUserName() {return userName;}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", externalId='" + externalId + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", isActivated=" + isActivated +
                ", role=" + role +
                '}';
    }
}
