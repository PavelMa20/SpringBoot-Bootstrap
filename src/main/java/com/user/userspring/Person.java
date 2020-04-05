package com.user.userspring;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "persons")
//@Proxy(lazy = false)
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private
    Long id;
    @Column(name = "givenName")
    private String givenName;
    @Column(name = "surName")
    private String surName;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private int age;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();


    public Set<Role> getRoles() {
        return this.roles;
    }


    public Person(String givenName, String surName, String password, int age, Set<Role> roles) {
        this.givenName = givenName;
        this.surName = surName;
        this.password = password;
        this.age = age;
        this.roles = roles;

    }


    public Person() {
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Person(String givenName, String password, int age) {
        this.givenName = givenName;
        this.password = password;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person user = (Person) o;
        return getAge() == user.getAge() &&
                getId().equals(user.getId()) &&
                getGivenName().equals(user.getGivenName()) &&
                surName.equals(user.surName) &&
                getPassword().equals(user.getPassword()) &&
                getRoles().equals(user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGivenName(), surName, getPassword(), getAge(), getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public String getUsername() {
        return getGivenName();
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
