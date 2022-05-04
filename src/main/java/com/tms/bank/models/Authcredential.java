package com.tms.bank.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "authcredential", schema = "bankofvacancies")
public class Authcredential implements Serializable {

    @Serial
    private static final long serialVersionUID = 212562667735192639L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authcredential that)) return false;
        return id == that.id && login.equals(that.login) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "Authcredential{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
