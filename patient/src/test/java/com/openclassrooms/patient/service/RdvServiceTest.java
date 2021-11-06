package com.openclassrooms.patient.service;


import com.openclassrooms.patient.entity.Rdv;
import com.openclassrooms.patient.exception.RdvNotFoundException;
import com.openclassrooms.patient.repository.RdvRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RdvServiceTest {


    @Mock
    RdvRepository rdvRepository;

    @InjectMocks
    RdvService rdvService;


    @Test
    public void testGetPatientAge() {

        Rdv  rdv = new Rdv();
        List<Rdv> rdvs = new ArrayList<Rdv>();
        when(rdvRepository.findAll()).thenReturn(rdvs);
        List<Rdv> rdvs1 = new ArrayList<Rdv>();
               rdvs1 = rdvService.findAll();
        assertEquals(rdvs,rdvs1);

    }

    @Test
    public void testFindById() throws RdvNotFoundException {

        Rdv  rdv = new Rdv();
        rdv.setIdRdv(1L);
        when(rdvRepository.existsById(anyLong())).thenReturn(true);
        when(rdvRepository.findById(anyLong())).thenReturn(java.util.Optional.of(rdv));
        Rdv rdv1  = rdvService.getRdvById(1L);
        assertEquals(rdv,rdv1);

    }

    @Test
    public void testDeleteById() throws RdvNotFoundException {

        Rdv  rdv = new Rdv();
        rdv.setIdRdv(1L);
        when(rdvRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(rdvRepository).deleteById(anyLong());
        rdvService.deleteRdv(1L);
        verify(rdvRepository).existsById(anyLong());


    }

    @Test
    public void testUpdateRdv() throws RdvNotFoundException {
        Rdv  rdv = new Rdv();
        when(rdvRepository.existsById(anyLong())).thenReturn(true);
        when(rdvRepository.findById(anyLong())).thenReturn(java.util.Optional.of(rdv));
        when(rdvRepository.save((Rdv) any())).thenReturn(rdv);

        Rdv rdv1  = rdvService.updateRdv(rdv, 1L);
        verify(rdvRepository).existsById(anyLong());
        verify(rdvRepository).findById(anyLong());
        assertEquals(rdv,rdv1);


    }

    @Test
    public void testSaveRdv()  {
        Rdv  rdv = new Rdv();
        when(rdvRepository.save((Rdv) any())).thenReturn(rdv);
        rdvService.saveRdv(rdv);
        verify(rdvRepository).save((Rdv) any());

    }


}
