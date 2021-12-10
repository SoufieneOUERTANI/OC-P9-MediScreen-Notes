package com.ouertani.notes.Service.Impl;

import com.ouertani.notes.DTO.Patient;
import com.ouertani.notes.Service.INoteService;
import com.ouertani.notes.model.Note;
import com.ouertani.notes.repository.NotesRepository;
import com.ouertani.notes.webClient.PatientWebClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

@Service
public class NoteService implements INoteService {

    private static final Logger logger = LogManager.getLogger("NoteService");

    static final List<String> assessList = Arrays.asList("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");

    NotesRepository notesRepository;
    PatientWebClient patientWebClient;

    @Autowired
    NoteService(NotesRepository notesRepository, PatientWebClient patientWebClient){
        this.notesRepository=notesRepository;
        this.patientWebClient=patientWebClient;
    }

    @Override
    public String getPatientAssess(String patientId) {
        Long patientIdLong;
        try {
            patientIdLong = Long.parseLong(patientId);
        } catch (NumberFormatException nfe) {
            return (nfe.getMessage());
        }

        Patient patient = patientWebClient.getPatient(patientIdLong);

        int ageInYears  = Period.between(patient.getBirthday(), LocalDate.now()).getYears();
        String gender  = patient.getGender();
        logger.info("ageInYears : "+ageInYears);
        logger.info("gender : "+gender);


        Note note = this.notesRepository.findByPatientIdEquals(patientId);
        note.getCommentList().forEach(s -> logger.info((s.getComment())));

        int countAssess = 0;
        for (String asses:assessList
             ) {
            countAssess+= note.getCommentList().stream().filter(comment -> comment.getComment().toLowerCase().contains(asses.toLowerCase())).count();
        }

        logger.info("countAssess ===> "+countAssess);

        switch(countAssess) {
            case 0:{
                logger.info("countAssess ===> " + countAssess);
                return "None";
            }
            case 2:
                if (ageInYears >= 30){
                    logger.info("countAssess ===> " + countAssess);
                return "Borderline";
                }
            case 3:
                if (ageInYears < 30 && gender.equals("Male")) {
                    logger.info("countAssess ===> " + countAssess);
                    return "In Danger";
                }
            case 4:
                if (ageInYears < 30 && gender.equals("Female")){
                    logger.info("countAssess ===> " + countAssess);
                    return "In Danger";
                }
            case 5:
                if (ageInYears < 30 && gender.equals("Male")){
                    logger.info("countAssess ===> " + countAssess);
                    return "Early onset";
                }
            case 6:
                if (ageInYears >= 30){
                    logger.info("countAssess ===> " + countAssess);
                    return "In Danger";
                }
            case 7:
                if (ageInYears < 30 && gender.equals("Female")){
                    logger.info("countAssess ===> " + countAssess);
                    return "Early onset";
                }
            case 8:
                if (ageInYears> 30){
                    logger.info("countAssess ===> " + countAssess);
                    return "Early onset";
                }
            default :
                if (countAssess> 8) {
                    logger.info("countAssess ===> " + countAssess);
                    return "Early onset";
                }
        }
        return  "Les paramétarge ne permet pas de déterminer le degrès du risque";
    }
}
