package com.tms.bank.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "bankofvacancies")
public class User implements Serializable {

    private static final long serialVersionUID = 1506510455917493450L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "vocation")
    private String vocation;
    @Column(name = "role")
    private String role;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "authCredential_Id")
    private Authcredential authcredential;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_Id")
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && age == user.age && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(vocation, user.vocation) && Objects.equals(authcredential, user.authcredential) && Objects.equals(company, user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, vocation, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", vocation='" + vocation + '\'' +
                ", role='" + role + '\'' +
                ", authcredential=" + authcredential +
                ", company=" + company +
                '}';
    }
}
