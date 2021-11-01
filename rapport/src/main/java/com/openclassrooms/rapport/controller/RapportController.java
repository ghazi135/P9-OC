package com.openclassrooms.rapport.controller;

import com.openclassrooms.rapport.model.Rapport;
import com.openclassrooms.rapport.service.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class RapportController {

    @Autowired
    private RapportService rapportService;

    @GetMapping("/reports/lastAndFirstName")
    public Rapport getRapportByLastAndFirstName(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName) {

        return rapportService.getRapportByLastAndFirstName(lastName, firstName);
    }

    @GetMapping("/reports/{id}")
    public Rapport getRapportById (@PathVariable int id) {
        return  rapportService.getRapportById(id);
    }

}
