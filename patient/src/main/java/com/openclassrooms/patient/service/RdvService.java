package com.openclassrooms.patient.service;


import com.openclassrooms.patient.entity.Rdv;
import com.openclassrooms.patient.exception.RdvNotFoundException;
import com.openclassrooms.patient.repository.RdvRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RdvService {

    @Autowired
    private RdvRepository rdvRepository;

    public List<Rdv> findAll(){
            log.info("find all meetings");
            return rdvRepository.findAll();

    }

    public void saveRdv (Rdv rdv) {
        try{
            log.info("save meeting");

            rdvRepository.save(rdv);

        }
        catch (Exception e) {
            log.error("problem exist with saving meeting", e);
        }
    }

    public Rdv getRdvById (Long id) throws RdvNotFoundException {
        if(rdvRepository.existsById(id)){
            log.info("find meeting by id:" + id);
            return rdvRepository.findById(id).get();
        }
        else{
            log.error(" no meeting found with ID:" + id);
            throw new RdvNotFoundException(" no meeting found with ID:" + id);
        }
    }


    public Rdv updateRdv ( Rdv rdv, Long id) throws RdvNotFoundException {
        if(rdvRepository.existsById(id)){
            log.info("update meeting by id:" + id);
            Rdv rdv1 = rdvRepository.findById(id).get();
            rdv1.setNoteRdv(rdv.getNoteRdv());
            rdv1.setDatePriseRdv(rdv.getDatePriseRdv());
            rdv1.setNamePatient(rdv.getNamePatient());
            rdvRepository.save(rdv1);
            return rdv1;
        }
        else{
            log.error("mmeting not found with ID :" + id);
            throw new RdvNotFoundException("RDV not found with ID :" + id);
        }

    }


    public void deleteRdv( Long id) throws RdvNotFoundException {
        if(rdvRepository.existsById(id)){
            log.info("delete meeting by id:" + id);
            rdvRepository.deleteById(id);
        }
        else{
            throw  new RdvNotFoundException("meeting not found with this with ID" + id);
        }
    }

}
