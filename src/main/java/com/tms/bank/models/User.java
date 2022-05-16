package com.tms.bank.models;

import com.tms.bank.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private Role role;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "authCredential_Id")
    private Authcredential authcredential;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Vacancy> vacancies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && age == user.age && firstName.equals(user.firstName) && lastName.equals(user.lastName) && Objects.equals(vocation, user.vocation) && role.equals(user.role) && authcredential.equals(user.authcredential);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, vocation, role, authcredential);
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
                '}';
    }
}
