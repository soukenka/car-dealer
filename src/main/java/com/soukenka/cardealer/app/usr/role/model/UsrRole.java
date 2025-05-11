package com.soukenka.cardealer.app.usr.role.model;

import com.soukenka.cardealer.core.model.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entity representing a user role in the system.
 *
 * @author Ing. Bc. Daniel Soukenka
 * @version 1.0
 * @created 08.05.2025
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "usr_role")
@AttributeOverride(name = "id", column = @Column(name = "id_usr_role", nullable = false))
public class UsrRole extends BaseEntity implements GrantedAuthority, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Name of a user role
     */
    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
