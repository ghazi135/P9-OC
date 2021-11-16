package com.openclassrooms.rapport.proxy;


import com.openclassrooms.rapport.model.Note;
import lombok.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "note", url =  "${notes.url}")
public interface NoteProxy {


    @GetMapping(value = "/notes/findByLastAndFirstName")
    List<Note> getNotesPatientByLastAndFirstName(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName);

}
