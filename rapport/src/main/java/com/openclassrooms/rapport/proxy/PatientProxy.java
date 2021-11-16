package com.openclassrooms.rapport.proxy;

import com.openclassrooms.rapport.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "patient", url =  "${patient.url}")
public interface PatientProxy {


    @GetMapping(value = "/api/patient")
    Patient getPatient();


    @GetMapping(value = "/api/patient/{id}")
    Patient getPatientById (@PathVariable int id);


    @GetMapping(value = "/api/patient/getPatientLastAndFirst")
    Patient getPatientByLastAndFirstName (@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName);

}
