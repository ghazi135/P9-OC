package com.openclassrooms.patient.service;


import com.openclassrooms.patient.entity.Patient;
import com.openclassrooms.patient.exception.PatientNotFoundException;
import com.openclassrooms.patient.repository.PatientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PatientService {

    @Autowired
    public PatientRepository patientRepository;


    public List<Patient> findAllPatients() {
        log.info("find all patients");

            return patientRepository.findAll();
    }


    public Optional<Patient> getPatientById (Long id) throws PatientNotFoundException {
        if(patientRepository.existsById(id)){
            log.info("find  patient by id:" + id);
            return patientRepository.findById(id);

        }
        else
            log.error("Patient not found with ID : " + id);
            throw new PatientNotFoundException("no patient found with ID:" + id);
    }


    public void savePatient (Patient patient) {
        try{
            log.info("save patient");
            patientRepository.save(patient);

        }
        catch (Exception e){
            log.error("problem occurred with saving patient ", e);
        }
    }


    public Patient findPatientByLastAndFirstName (String lastName, String firstName) throws PatientNotFoundException {
        if(patientRepository.existsByLastNameAndFirstName(lastName,firstName)){
            log.info("find  patient by first name and last name");
            return patientRepository.findByLastNameAndFirstName(lastName, firstName);
        }
        else {
            log.error("no patient found with " + "last Name:" + lastName + "first Name:" + firstName);
            throw new PatientNotFoundException("no patient found with " + "last Name:" + lastName + "first Name:" + firstName);
        }
    }



    public void updatePatient(Patient patient, Long id) throws PatientNotFoundException {

        if(patientRepository.existsById(id)){
            Patient patient1 = patientRepository.findById(id).get();
            patient1.setFirstName(patient.getFirstName());
            patient1.setLastName(patient.getLastName());
            patient1.setDateOfBirth(patient.getDateOfBirth());
            patient1.setSex(patient.getSex());
            patient1.setAddress(patient.getAddress());
            patient1.setPhoneNumber(patient.getPhoneNumber());
            patientRepository.save(patient1);
            log.info("update patient by id:" + id);
        }
        else{
            log.error("Patient not found with ID :" + id);
            throw  new PatientNotFoundException("Patient not found with ID :" + id);
        }

    }


    public void deletePatient(Long id) throws PatientNotFoundException {

        if(patientRepository.existsById(id)){
            log.info("delete patient by id:" + id);
            patientRepository.deleteById(id);
        }
        else
            log.error("Patient not found with ID : " + id);
            throw new PatientNotFoundException("Patient not found with ID : " + id);

    }

}
