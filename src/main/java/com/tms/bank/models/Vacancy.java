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
    private String vocationVacancy;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Vacancy(String vocationVacancy, String description) {
        this.vocationVacancy = vocationVacancy;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && Objects.equals(vocationVacancy, vacancy.vocationVacancy) && Objects.equals(description, vacancy.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vocationVacancy, description);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", vocationVacancy='" + vocationVacancy + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
