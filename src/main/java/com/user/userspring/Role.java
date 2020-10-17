package com.user.userspring;

import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "roleName",unique = true)
    private String roleName;

    public Role(String id ) {
        this.id = Long.parseLong(id);
    }


    @Override
    public String getAuthority() {
        return getRoleName();
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId().equals(role.getId()) &&
                getRoleName().equals(role.getRoleName()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoleName());
    }
}
