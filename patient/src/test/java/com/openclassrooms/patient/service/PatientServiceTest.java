package com.openclassrooms.patient.service;

import com.openclassrooms.patient.entity.Patient;
import com.openclassrooms.patient.exception.PatientNotFoundException;
import com.openclassrooms.patient.repository.PatientRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    PatientService patientService;

    @Test
    public void testFindAllPatient() {
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        when(this.patientRepository.findAll()).thenReturn(patientList);
        Iterable<Patient> actualFindAllPatientResult = patientService.findAllPatients();
        assertSame(patientList, actualFindAllPatientResult);
        verify(this.patientRepository).findAll();
    }

    @Test
    public void testFindPatientByLastAndFirstName() {
        Patient patient = new Patient();
        patient.setLastName("bouzazi");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setIdPatient(1L);
        patient.setPhoneNumber(122222222L);
        patient.setAddress("14 rue col");
        patient.setFirstName("ghazi");
        patient.setSex("M");
        when(patientRepository.findByLastNameAndFirstName(anyString(), anyString())).thenReturn(patient);
        assertSame(patient, patientService.findPatientByLastAndFirstName("bouzazi", "ghazi"));
        verify(this.patientRepository).findByLastNameAndFirstName(anyString(), anyString());
    }

    @Test
    public void testSavePatient() {
        Patient patient = new Patient();
        patient.setLastName("bouzazi");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setIdPatient(1L);
        patient.setPhoneNumber(122222222L);
        patient.setAddress("14 rue col");
        patient.setFirstName("ghazi");
        patient.setSex("M");
        when(patientRepository.save(patient)).thenReturn(patient);
        patientService.savePatient(patient);
        verify(patientRepository).save(patient);

    }

    @Test
    public void testGetById() {
        Patient patient = new Patient();
        patient.setLastName("bouzazi");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setIdPatient(1L);
        patient.setPhoneNumber(122222222L);
        patient.setAddress("14 rue col");
        patient.setFirstName("ghazi");
        patient.setSex("M");
        Optional<Patient> ofResult = Optional.<Patient>of(patient);
        when(patientRepository.findById(anyLong())).thenReturn(ofResult);
        patientService.getPatientById(1L);
        verify(patientRepository).findById(anyLong());
    }


    @Test
    public void testUpdatePatient() throws PatientNotFoundException {
        Patient patient = new Patient();
        patient.setLastName("bouzazi");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setIdPatient(1L);
        patient.setPhoneNumber(122222222L);
        patient.setAddress("14 rue col");
        patient.setFirstName("ghazi");
        patient.setSex("M");

        Patient patient1 = new Patient();
        patient1.setLastName("dupont");
        patient1.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient1.setIdPatient(1L);
        patient1.setPhoneNumber(122222222L);
        patient1.setAddress("15 rue col");
        patient1.setFirstName("alex");
        patient1.setSex("M");
        when(patientRepository.save((Patient) any())).thenReturn(patient1);
        when(patientRepository.existsById(anyLong())).thenReturn(true);
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        patientService.updatePatient(patient1, 1L);
        verify(patientRepository).save((Patient) any());
        verify(patientRepository).findById(anyLong());
    }


    @Test
    public void testDeletePatient() throws PatientNotFoundException {
        Patient patient = new Patient();
        patient.setLastName("bouzazi");
        patient.setDateOfBirth(LocalDate.ofEpochDay(1L));
        patient.setIdPatient(1L);
        patient.setPhoneNumber(122222222L);
        patient.setAddress("14 rue col");
        patient.setFirstName("ghazi");
        patient.setSex("M");
        doNothing().when(patientRepository).deleteById(anyLong());
        when(patientRepository.existsById(anyLong())).thenReturn(true);
        patientService.deletePatient(1L);
        verify(patientRepository).deleteById(anyLong());
        verify(patientRepository).existsById(anyLong());
    }

}
