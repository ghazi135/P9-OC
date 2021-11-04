package com.openclassrooms.note.service;


import com.openclassrooms.note.model.Notes;
import com.openclassrooms.note.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {


    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteService noteService;


    @Test
    public void testGetNotesById() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(12);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");
        Optional<Notes> ofResult = Optional.<Notes>of(notes);
        when(this.noteRepository.findById(anyString())).thenReturn(ofResult);
        Optional<Notes> actualNotesById = this.noteService.findNoteById("42");
        assertSame(ofResult, actualNotesById);
        assertTrue(actualNotesById.isPresent());
        verify(this.noteRepository).findById(anyString());
    }


    @Test
    public void testCreateNotes() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(123);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");

        when(this.noteRepository.save(anyObject())).thenReturn(notes);
        Notes notes1 = this.noteService.saveNote(notes);
        assertEquals(notes,notes1);
    }


    @Test
    public void testFindNotesByLastAndFirstName() {
        ArrayList<Notes> notesList = new ArrayList<Notes>();
        when(this.noteRepository.findByPatientLastNameAndPatientFirstName(anyString(), anyString())).thenReturn(notesList);
        List<Notes> actualFindNotesByLastAndFirstNameResult = this.noteService.findNoteByLastAndFirstName("Doe", "Jane");
        assertSame(notesList, actualFindNotesByLastAndFirstNameResult);
        assertTrue(actualFindNotesByLastAndFirstNameResult.isEmpty());
        verify(this.noteRepository).findByPatientLastNameAndPatientFirstName(anyString(), anyString());
    }

    @Test
    public void testUpdateNotes() {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("42");
        notes.setPatientLastName("Doe");
        notes.setPatientId(123);
        notes.setPatientFirstName("Jane");
        notes.setNote("Note");
        Optional<Notes> ofResult = Optional.<Notes>of(notes);

        Notes notes1 = new Notes();
        notes1.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes1.setId("42");
        notes1.setPatientLastName("Doe");
        notes1.setPatientId(123);
        notes1.setPatientFirstName("Jane");
        notes1.setNote("Note");
        when(noteRepository.save((Notes) any())).thenReturn(notes1);
        when(noteRepository.findById(anyString())).thenReturn(ofResult);
        noteService.updateNotes("42",notes1);
        verify(noteRepository).findById(anyString());
        verify(noteRepository).save((Notes) any());
    }


    @Test
    public void testDeleteNote() {
        doNothing().when(noteRepository).deleteById(anyString());
        noteService.deleteNotes("123456");
        verify(noteRepository).deleteById(anyString());
    }



}
