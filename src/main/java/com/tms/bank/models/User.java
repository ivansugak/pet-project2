package com.tms.bank.models;

import com.tms.bank.enums.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "age")
    private int age;

    @Column(name = "vocation")
    private String vocation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @NotNull
    @Size(min = 4, message = "Login must be at least 4 characters long")
    @Column(name = "login")
    private String login;

    @NotNull
    @Size(min = 4, message = "Password must be at least 4 characters and numbers long")
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Vacancy> vacancies;


    public User(long id, String firstName, String lastName, int age, String vocation, Role role, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.vocation = vocation;
        this.role = role;
        this.login = login;
        this.password = password;
    }
}
