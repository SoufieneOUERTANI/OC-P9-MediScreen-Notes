package com.ouertani.notes.controller;

import com.ouertani.notes.Service.INoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NoteController {
    private final INoteService noteService;

    private static final Logger logger = LogManager.getLogger("NoteController");

    @Autowired
    NoteController(INoteService noteService){
        this.noteService = noteService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/assets/{patientId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseStatus(code = HttpStatus.FOUND)
    public String getPatientAssess(@PathVariable Map<String, String> pathVarsMap, @RequestHeader Map<String, String> headers){

        String patientId = pathVarsMap.get("patientId");

        logger.info("patientId ===> "+patientId);

        headers.forEach((key, value) -> {
            logger.info(String.format("SOUE ===> Header '%s' = %s", key, value));
        });

        if (patientId != null) {
            return "{\"patientDiabetesRisk\":\""+this.noteService.getPatientAssess(patientId)+"\"}";
        }
        return null;
    }
}
