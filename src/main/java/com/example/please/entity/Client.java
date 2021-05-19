package com.example.please.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "client")
    @JsonManagedReference
    private List<Account> accounts;
    @Nullable
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="client_role", joinColumns = @JoinColumn(name = "client_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roleSet;

    public void addItem(Account account){
        if(accounts == null){
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        account.setClient(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleSet();
    }

    @Override
    public String getUsername() {
        return firstName;
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
}
