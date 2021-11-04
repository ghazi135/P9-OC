package com.openclassrooms.rapport.service;


import com.openclassrooms.rapport.model.Note;
import com.openclassrooms.rapport.model.Patient;
import com.openclassrooms.rapport.model.Rapport;
import com.openclassrooms.rapport.proxy.NoteProxy;
import com.openclassrooms.rapport.proxy.PatientProxy;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class repportServiceTest {

    @Mock
    private NoteProxy noteProxy;

    @Mock
    private PatientProxy patientProxy;

    @InjectMocks
    private RapportService rapportService;


    @Test
    public void testGetPatientAge() {

        Patient patient = new Patient();
        patient.setDateOfBirth(LocalDate.from(LocalDateTime.of(1994, 12, 27, 1, 1)));
        assertEquals(26L, rapportService.getPatientAge(patient));
    }


    @Test
    public void testCalculRisk() {

        Patient patient = mock(Patient.class);
        when(patient.getSex()).thenReturn("M");
        when(patient.getDateOfBirth()).thenReturn(LocalDate.ofEpochDay(1L));
        assertEquals("None", this.rapportService.calculRisk(patient, new ArrayList<Note>()));
        verify(patient).getDateOfBirth();
        verify(patient, times(4)).getSex();
    }


    @Test
    public void testCalculNbDeclencheurs() {

        Note note = mock(Note.class);
        when(note.getNote()).thenReturn("M");

        ArrayList<Note> noteList = new ArrayList<Note>();
        noteList.add(note);
        noteList.add(new Note(1, "Note"));
        assertEquals(0L, rapportService.calculNbDeclencheurs(noteList));
        verify(note).getNote();
    }

    @Test
    public void testGetRapportByLastAndFirstName() {

        Patient         patient  = new Patient(1, "bouzazi", "ghazi", LocalDate.from(LocalDateTime.of(1994, 12, 27, 1, 1)), "15 rue col", 1L, "M");
        ArrayList<Note> noteList = new ArrayList<Note>();
        noteList.add(new Note(123, "Note"));
        noteList.add(new Note(124, "Note1"));
        when(patientProxy.getPatientByLastAndFirstName("bouzazi", "ghazi")).thenReturn(patient);
        when(noteProxy.getNotesPatientByLastAndFirstName("bouzazi", "ghazi")).thenReturn(noteList);
        Rapport rapport = rapportService.getRapportByLastAndFirstName("bouzazi", "ghazi");
        assertEquals(26L, rapport.getAge());
        assertEquals("None", rapport.getStatus());
        assertEquals("M", rapport.getSex());
        assertEquals("bouzazi", rapport.getLastName());
        assertEquals("ghazi", rapport.getFirstName());
        verify(noteProxy).getNotesPatientByLastAndFirstName("bouzazi", "ghazi");
        verify(patientProxy).getPatientByLastAndFirstName("bouzazi", "ghazi");
    }


    @Test
    public void testGetRapportById() {

        Patient patient = new Patient(1, "bouzazi", "ghazi", LocalDate.from(LocalDateTime.of(1994, 12, 27, 1, 1)), "15 rue col", 1L, "M");
        when(this.patientProxy.getPatientById(anyInt())).thenReturn(patient);
        when(this.noteProxy.getNotesPatientByLastAndFirstName(anyString(), anyString())).thenReturn(new ArrayList<Note>());
        Rapport actualRapportById = this.rapportService.getRapportById(1);
        assertEquals(26L, actualRapportById.getAge());
        assertEquals("None", actualRapportById.getStatus());
        assertEquals("M", actualRapportById.getSex());
        assertEquals("bouzazi", actualRapportById.getLastName());
        assertEquals("ghazi", actualRapportById.getFirstName());
        verify(this.noteProxy).getNotesPatientByLastAndFirstName(anyString(), anyString());
        verify(this.patientProxy).getPatientById(anyInt());
    }
}
