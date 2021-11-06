package com.openclassrooms.rapport.serviceTest;

import com.openclassrooms.rapport.model.Note;
import com.openclassrooms.rapport.model.Patient;
import com.openclassrooms.rapport.model.Rapport;
import com.openclassrooms.rapport.proxy.NoteProxy;
import com.openclassrooms.rapport.proxy.PatientProxy;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RapportService {

    private final NoteProxy    noteProxy;
    private final PatientProxy patientProxy;

    String [] declencheurs = {
                              "Hemoglobin A1C",
                              "Microalbumine",
                              "Height",
                              "Weight",
                              "Smoker",
                              "Abnormal",
                              "Cholesterol",
                              "Dizziness",
                              "Relapse",
                              "Reaction",
                              "Antibodies"
    };

    public RapportService(NoteProxy noteProxy, PatientProxy patientProxy) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;

    }

    public long getPatientAge (Patient patient) {
        return ChronoUnit.YEARS.between(patient.getDateOfBirth(), LocalDate.now());
    }
    public String calculRisk (Patient patient, List<Note> notes) {
        long nbDeclencheurs = calculNbDeclencheurs(notes);
        long age = getPatientAge(patient);
        String status = "None";


        if( (age> 30 && nbDeclencheurs >= 8) ||
            (patient.getSex().contains("F") && age < 30 && nbDeclencheurs >= 7) ||
            (patient.getSex().contains("M") && age < 30 && nbDeclencheurs >= 5)
        ) {
            status = "EarlyOnset";
        } else if ( (age> 30 && nbDeclencheurs>= 6 ) ||
                    (patient.getSex().contains("F") && age < 30 && nbDeclencheurs>= 4 && nbDeclencheurs <= 6) ||
                    (patient.getSex().contains("M") && age < 30 && nbDeclencheurs>= 3 && nbDeclencheurs <= 4)
        ) {
            status = "InDanger";
        } else if ( age > 30 && nbDeclencheurs >= 2
        ) {
            status = "Borderline";
        }

        return status;
    }

     long calculNbDeclencheurs(List<Note> notes) {
        String noteToStream = notes.stream()
                                   .map(Note::getNote)
                                   .map(String::trim)
                                   .collect(Collectors.joining());

        return  Arrays.stream(declencheurs)
                                    .filter(noteToStream::contains)
                                    .distinct()
                                    .count();

    }


    public Rapport getRapportByLastAndFirstName (String lastName, String firstName) {
        Patient    patient = patientProxy.getPatientByLastAndFirstName(lastName, firstName);
        List<Note> notes   = noteProxy.getNotesPatientByLastAndFirstName(lastName, firstName);
        String     status  = calculRisk(patient, notes);
        return new Rapport(patient.getLastName(), patient.getFirstName(), patient.getSex(), getPatientAge(patient), status);
    }

    public Rapport getRapportById (int id) {
        Patient patient = patientProxy.getPatientById(id);
        List<Note> notes = noteProxy.getNotesPatientByLastAndFirstName(patient.getLastName(), patient.getFirstName());
        String status = calculRisk(patient, notes);
        return new Rapport(patient.getLastName(), patient.getFirstName(), patient.getSex(), getPatientAge(patient), status);
    }
}
