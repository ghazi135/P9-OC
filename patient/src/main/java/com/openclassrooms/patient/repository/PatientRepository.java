package com.openclassrooms.patient.repository;

import com.openclassrooms.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByLastNameAndFirstName (String lastName, String firstName);
    boolean existsByLastNameAndFirstName(String lastName, String firstName);

}
