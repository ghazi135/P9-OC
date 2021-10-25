package com.openclassrooms.patient.controller;

import com.openclassrooms.patient.entity.Patient;
import com.openclassrooms.patient.exception.PatientNotFoundExecption;
import com.openclassrooms.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {


    @Autowired
    public PatientService patientService;



    @GetMapping("/patient")
    public List<Patient> getPatient() {

        return patientService.findAllPatients();
    }


    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id).get();
    }


    @GetMapping("/patient/getPatientLastAndFirst")
    public Patient getPatientByLastAndFirstName (@RequestParam("last") String lastName, @RequestParam("first") String firstName) {

        return patientService.findPatientByLastAndFirstName(lastName, firstName);
    }


    @PostMapping("/patient")
    public void addPatient(@RequestBody  Patient patient) {

        patientService.savePatient(patient);

    }


    @PutMapping("/patient/{id}")
    public void updatePatient(@RequestBody Patient patient, @PathVariable Long id) throws PatientNotFoundExecption {

        patientService.updatePatient(patient,id);

    }



    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) throws PatientNotFoundExecption {

        patientService.deletePatient(id);

    }

}
