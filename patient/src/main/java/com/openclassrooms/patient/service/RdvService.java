package com.openclassrooms.patient.service;


import com.openclassrooms.patient.entity.Rdv;
import com.openclassrooms.patient.exception.RdvNotFoundException;
import com.openclassrooms.patient.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RdvService {

    @Autowired
    private RdvRepository rdvRepository;

    public void saveRdv (Rdv rdv) {
        rdvRepository.save(rdv);
    }

    public Rdv getRdvById (Long id) {
        return rdvRepository.findById(id).get();
    }


    public void updateRdv ( Rdv rdv, Long id) throws RdvNotFoundException {
        if(rdvRepository.existsById(id)){
            Rdv rdv1 = rdvRepository.findById(id).get();
            rdv1.setNoteRdv(rdv.getNoteRdv());
            rdv1.setDatePriseRdv(rdv.getDatePriseRdv());
            rdv1.setNamePatient(rdv.getNamePatient());
            rdvRepository.save(rdv1);
        }
        else{
            throw new RdvNotFoundException("RDV not found with ID :" + id);
        }



    }


    public void deleteRdv( Long id) throws RdvNotFoundException {
        if(rdvRepository.existsById(id)){
            rdvRepository.deleteById(id);
        }
        else{
            throw  new RdvNotFoundException("RDV not found with this with ID" + id);
        }



    }

}
