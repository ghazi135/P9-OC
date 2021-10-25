package com.openclassrooms.note.service;

import com.openclassrooms.note.model.Notes;
import com.openclassrooms.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    public NoteRepository noteRepository;

    public List<Notes> findAllNotes(){
        return noteRepository.findAll();
    }

    public Optional<Notes> findNoteById(String id) {
        return noteRepository.findById(id);
    }


    public void saveNote (Notes notes) {
        noteRepository.save(notes);
    }


    public List<Notes> findNoteByLastAndFirstName (String patientLastName, String patientFirstName) {
        return noteRepository.findByPatientLastNameAndPatientFirstName(patientLastName, patientFirstName);
    }



    public void updateNotes(String id, Notes notes) {
        Notes note = noteRepository.findById(id).get();

        note.setNote(notes.getNote());
        note.setDateNote(notes.getDateNote());
        note.setPatientId(notes.getPatientId());
        noteRepository.save(note);

    }


    public void  deleteNotes(String id) {

        noteRepository.deleteById(id);

    }

}
