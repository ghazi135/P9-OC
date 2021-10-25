package com.openclassrooms.patient.service;


import com.openclassrooms.patient.entity.Patient;
import com.openclassrooms.patient.exception.PatientNotFoundExecption;
import com.openclassrooms.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    public PatientRepository patientRepository;


    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }


    public Optional<Patient> getPatientById (Long id) {
        return patientRepository.findById(id);
    }


    public void savePatient (Patient patient) {
        patientRepository.save(patient);
    }


    public Patient findPatientByLastAndFirstName (String lastName, String firstName) {
        return patientRepository.findByLastNameAndFirstName(lastName, firstName);
    }



    public void updatePatient(Patient patient, Long id) throws PatientNotFoundExecption {

        if(patientRepository.existsById(id)){
            Patient patient1 = patientRepository.findById(id).get();
            patient1.setFirstName(patient.getFirstName());
            patient1.setLastName(patient.getLastName());
            patient1.setDateOfBirth(patient.getDateOfBirth());
            patient1.setSex(patient.getSex());
            patient1.setAddress(patient.getAddress());
            patient1.setPhoneNumber(patient.getPhoneNumber());
            patientRepository.save(patient1);
        }
        else{
            throw  new PatientNotFoundExecption("Patient not found with ID :" + id);
        }



    }


    public void deletePatient( Long id) throws PatientNotFoundExecption {

        if(patientRepository.existsById(id)){
            patientRepository.deleteById(id);
        }
        else
            throw new PatientNotFoundExecption("Patient not found with ID : " + id);

    }

}
