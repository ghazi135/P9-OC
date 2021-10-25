package com.openclassrooms.patient.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rendez_vous")
public class Rdv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rdv")
    private Long idRdv;

    @Column(name = "name_patient")
    @NotNull
    private String namePatient;

    @Column(name = "date_rdv")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate datePriseRdv;

    @Column(name = "note_rdv")
    @NotNull
    private String noteRdv;
}
