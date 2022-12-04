package com.tms.bank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vacancy", schema = "bankofvacancies")
public class Vacancy implements Serializable {

    private static final long serialVersionUID = 3603355796842120419L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private long id;

    @Column(name = "vocation")
    @JsonProperty
    private String vocationVacancy;

    @Column(name = "description")
    @JsonProperty
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty
    private User user;

    public Vacancy(String vocationVacancy, String description) {
        this.vocationVacancy = vocationVacancy;
        this.description = description;
    }
}
