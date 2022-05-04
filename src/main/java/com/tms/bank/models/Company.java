package com.tms.bank.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "company", schema = "bankofvacancies")
public class Company implements Serializable {

    private static final long serialVersionUID = 1687555901063348378L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Vacancy> vacancies;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return id == company.id && Objects.equals(name, company.name) && Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
