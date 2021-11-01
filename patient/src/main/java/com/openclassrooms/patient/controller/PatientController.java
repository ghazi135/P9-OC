package com.openclassrooms.patient.controller;

import com.openclassrooms.patient.entity.Patient;
import com.openclassrooms.patient.exception.PatientNotFoundException;
import com.openclassrooms.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
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
    public Patient getPatientByLastAndFirstName (@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName) {

        return patientService.findPatientByLastAndFirstName(lastName, firstName);
    }


    @PostMapping("/patient")
    public void addPatient(@RequestBody  Patient patient) {

        patientService.savePatient(patient);

    }


    @PutMapping("/patient/{id}")
    public void updatePatient(@RequestBody Patient patient, @PathVariable Long id) throws PatientNotFoundException {

        patientService.updatePatient(patient,id);

    }



    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) throws PatientNotFoundException {

        patientService.deletePatient(id);

    }

}
