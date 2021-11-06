package com.openclassrooms.note.serviceTest;


import com.openclassrooms.note.exception.NoteNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {


    @Mock
    NoteRepository noteRepository;

    @InjectMocks
    NoteService noteService;


    @Test
    public void testGetNotesById() throws NoteNotFoundException {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("10");
        notes.setPatientLastName("test");
        notes.setPatientId(12);
        notes.setPatientFirstName("test1");
        notes.setNote("Note");
        Optional<Notes> ofResult = Optional.<Notes>of(notes);
        when(this.noteRepository.findById(anyString())).thenReturn(ofResult);
        when(this.noteRepository.existsById(anyString())).thenReturn(true);

        Optional<Notes> actualNotesById = this.noteService.findNoteById("10");
        assertSame(ofResult, actualNotesById);
        assertTrue(actualNotesById.isPresent());
        verify(noteRepository).findById(anyString());
    }


    @Test
    public void testSaveNotes() {
        when(this.noteRepository.save((Notes) any())).thenReturn(new Notes());
        noteService.saveNote(new Notes());
        verify(this.noteRepository).save((Notes) any());

    }


    @Test
    public void testFindNotesByLastAndFirstName() throws NoteNotFoundException {
        ArrayList<Notes> notesList = new ArrayList<Notes>();
        when(this.noteRepository.findByPatientLastNameAndPatientFirstName(anyString(), anyString())).thenReturn(notesList);
        when(this.noteRepository.existsByPatientLastNameAndAndPatientFirstName(anyString(), anyString())).thenReturn(true);

        List<Notes> actualFindNotesByLastAndFirstNameResult = this.noteService.findNoteByLastAndFirstName("Doe", "Jane");
        assertSame(notesList, actualFindNotesByLastAndFirstNameResult);
        assertTrue(actualFindNotesByLastAndFirstNameResult.isEmpty());
        verify(this.noteRepository).findByPatientLastNameAndPatientFirstName(anyString(), anyString());
    }

    @Test
    public void testUpdateNotes() throws NoteNotFoundException {
        Notes notes = new Notes();
        notes.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes.setId("10");
        notes.setPatientLastName("test");
        notes.setPatientId(123);
        notes.setPatientFirstName("test1");
        notes.setNote("Note");
        Optional<Notes> ofResult = Optional.<Notes>of(notes);

        Notes notes1 = new Notes();
        notes1.setDateNote(LocalDateTime.of(1, 1, 1, 1, 1));
        notes1.setId("10");
        notes1.setPatientLastName("test2");
        notes1.setPatientId(123);
        notes1.setPatientFirstName("test3");
        notes1.setNote("Note");
        when(noteRepository.save((Notes) any())).thenReturn(notes1);
        when(noteRepository.findById(anyString())).thenReturn(ofResult);
        when(noteRepository.existsById(anyString())).thenReturn(true);

        noteService.updateNotes("10",notes1);
        verify(noteRepository).findById(anyString());
        verify(noteRepository).save((Notes) any());
    }


    @Test
    public void testDeleteNote() throws NoteNotFoundException {
        doNothing().when(noteRepository).deleteById(anyString());
        when(noteRepository.existsById(anyString())).thenReturn(true);
        noteService.deleteNotes("123456");
        verify(noteRepository).deleteById(anyString());
    }



}
