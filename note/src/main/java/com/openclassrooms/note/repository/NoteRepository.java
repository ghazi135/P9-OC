package com.openclassrooms.note.repository;

import com.openclassrooms.note.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Notes, String> {

    List<Notes> findByPatientLastNameAndPatientFirstName (String patientLastName, String patientFirstName);
    boolean existsByPatientLastNameAndAndPatientFirstName(String patientLastName, String patientFirstName);

}
