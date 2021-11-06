package com.openclassrooms.note.serviceTest;

import com.openclassrooms.note.exception.NoteNotFoundException;
import com.openclassrooms.note.model.Notes;
import com.openclassrooms.note.repository.NoteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class NoteService {

    @Autowired
    public NoteRepository noteRepository;

    public List<Notes> findAllNotes(){
            log.info("find all notes");
            return noteRepository.findAll();
    }

    public Optional<Notes> findNoteById(String id) throws NoteNotFoundException {
        if(noteRepository.existsById(id)){
            log.info("find note by id");
            return noteRepository.findById(id);

        }
        else {
            log.error("note not found with ID:" + id);
            throw  new NoteNotFoundException("note not found with ID:" + id);
        }



    }


    public Notes saveNote (Notes notes) {
        try{
            log.info("save note");
            noteRepository.save(notes);
            return notes;
        }
        catch (Exception e){
            log.error("problem occurred with saving note");
        }
        finally {
            return null;
        }

    }


    public List<Notes> findNoteByLastAndFirstName (String patientLastName, String patientFirstName) throws NoteNotFoundException {

        if(noteRepository.existsByPatientLastNameAndAndPatientFirstName(patientLastName,patientFirstName)){
            log.info("find note with first name and last name");
            return noteRepository.findByPatientLastNameAndPatientFirstName(patientLastName, patientFirstName);

        }
        else {
            log.error("note not found with first Name:"+ patientFirstName + "last Name:" + patientLastName);
            throw new NoteNotFoundException("note not found with first Name:"+ patientFirstName + "last Name:" + patientLastName);
        }
    }



    public void updateNotes(String id, Notes notes) throws NoteNotFoundException {
        if(noteRepository.existsById(id)){
            Notes note = noteRepository.findById(id).get();
            note.setNote(notes.getNote());
            note.setDateNote(notes.getDateNote());
            note.setPatientId(notes.getPatientId());
            noteRepository.save(note);
            log.info("update note by id:" + id);

        }
        else{
            log.error("not not found to update with Id:" + id);
            throw new NoteNotFoundException("not not found to update with Id:" + id);

        }


    }


    public void  deleteNotes(String id) throws NoteNotFoundException {

        if(noteRepository.existsById(id)){
            log.info("Delete note by id:" + id);
            noteRepository.deleteById(id);
        }
        else
        {
            log.error("no note found to delete with Id:" + id);
            throw new NoteNotFoundException("no note found to delete with Id:" + id);

        }

    }

}
