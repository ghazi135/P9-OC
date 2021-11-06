package com.openclassrooms.patient.controller;

import com.openclassrooms.patient.entity.Rdv;
import com.openclassrooms.patient.exception.RdvNotFoundException;
import com.openclassrooms.patient.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rdv")
@CrossOrigin(origins = "http://localhost:4200")
public class RdvController {


    @Autowired
    private RdvService rdvService;

    @GetMapping
    public List<Rdv> getRdv() {
        return rdvService.findAll();
    }

    @GetMapping("/{id}")
    public Rdv getRdvById(@PathVariable Long id) throws RdvNotFoundException {
       return rdvService.getRdvById(id);

    }

    @PostMapping
    public void addRdv(@RequestBody Rdv rdv) {

        rdvService.saveRdv(rdv);

    }


    @PutMapping("/{id}")
    public Rdv updateRdv (@RequestBody Rdv rdv, @PathVariable Long id) throws RdvNotFoundException {
     return rdvService.updateRdv(rdv,id);
    }


    @DeleteMapping("/{id}")
    public void deleteRdv(@PathVariable Long id) throws RdvNotFoundException {
        rdvService.deleteRdv(id);
    }

}
