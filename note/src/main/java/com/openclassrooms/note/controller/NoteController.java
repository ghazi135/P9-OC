package com.openclassrooms.note.controller;


import com.openclassrooms.note.exception.NoteNotFoundException;
import com.openclassrooms.note.model.Notes;
import com.openclassrooms.note.serviceTest.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(value = "/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping
    public List<Notes> findAllNotes() {
        return noteService.findAllNotes();
    }


    @GetMapping("/{id}")
    public Notes getNotesById(@PathVariable String id) throws NoteNotFoundException {
       return noteService.findNoteById(id).get();


    }


    @GetMapping("/findByLastAndFirstName")
    public List<Notes>  getNotesByFirstAndLastName(@RequestParam("lastName") String patientLastName, @RequestParam("firstName") String patientFirstName) throws NoteNotFoundException {
       return  noteService.findNoteByLastAndFirstName(patientLastName, patientFirstName);

    }

    @PostMapping
    public Notes addNotes(@RequestBody Notes notes) {

        return noteService.saveNote(notes);

    }

    @PutMapping(value = "/{id}")
    public void updateNotes(@PathVariable String id, @RequestBody Notes notes) throws NoteNotFoundException {

        noteService.updateNotes(id,notes);
    }

    @DeleteMapping("/{id}")
    public void deleteNotes(@PathVariable("id") String id) throws NoteNotFoundException {
        noteService.deleteNotes(id);
    }

}
