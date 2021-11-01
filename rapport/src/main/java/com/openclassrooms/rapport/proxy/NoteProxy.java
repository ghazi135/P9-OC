package com.openclassrooms.rapport.proxy;


import com.openclassrooms.rapport.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "note", url = "http://localhost:8082")
public interface NoteProxy {

    @GetMapping(value = "/notes/findByLastAndFirstName")
    List<Note> getNotesPatientByLastAndFirstName(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName);

}
