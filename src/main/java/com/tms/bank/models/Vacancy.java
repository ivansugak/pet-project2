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
@Table(name = "vacancy", schema = "bankofvacancies")
public class Vacancy implements Serializable {

    private static final long serialVersionUID = 3603355796842120419L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "vocation")
    private String vocation;
    @Column(name = "description")
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_Id")
    private Company company;

    public Vacancy(String vocation, String description) {
        this.vocation = vocation;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy vacancy)) return false;
        return id == vacancy.id && Objects.equals(vocation, vacancy.vocation) && Objects.equals(description, vacancy.description) && Objects.equals(company, vacancy.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vocation, description, company);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", vocation='" + vocation + '\'' +
                ", description='" + description + '\'' +
                ", company=" + company +
                '}';
    }
}
