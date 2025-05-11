package com.soukenka.cardealer.app.usr.user.model;

import com.soukenka.cardealer.app.usr.role.model.UsrRole;
import com.soukenka.cardealer.core.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a user in the system.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "usr_user")
@AttributeOverride(name = "id", column = @Column(name = "id_usr_user", nullable = false))
public class UsrUser extends BaseEntity implements UserDetails {
    /**
     * Username of the user
     */
    @NotBlank
    @Size(max = 255)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Password of the user
     */
    @NotBlank
    @Size(max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * User roles
     */
    @ManyToMany
    @JoinTable(name = "usr_user_role",
            joinColumns = @JoinColumn(name = "id_usr_user"),
            inverseJoinColumns = @JoinColumn(name = "id_usr_role")
    )
    @ToString.Exclude
    private Set<UsrRole> usrRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usrRoles;
    }
}
