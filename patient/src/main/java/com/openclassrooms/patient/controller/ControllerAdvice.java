package com.openclassrooms.patient.controller;

import com.openclassrooms.patient.exception.PatientNotFoundException;
import com.openclassrooms.patient.exception.RdvNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handlePatientNotFoundExecption(PatientNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Patient Not Found!!!" + ex);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RdvNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleRdvNotFoundException(RdvNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "RDV Not Found!!!"+ ex);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
