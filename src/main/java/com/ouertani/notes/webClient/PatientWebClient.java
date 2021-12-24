package com.ouertani.notes.webClient;

import com.ouertani.notes.DTO.Patient;
import com.ouertani.notes.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PatientWebClient {

    Logger logger = LoggerFactory.getLogger(PatientWebClient.class);

    //private static final String BASE_URL = "http://localhost:8081/";

    private final WebClient webClient;

    @Autowired
    public PatientWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

/*    private static final WebClient webClient = WebClient.builder()
            .baseUrl(BASE_URL)
            .build();*/

    public Patient getPatient(Long patientId){


/*        return webClient.get().uri(Constants.URL_PATIENT_PATIENT, patientId)
                .retrieve()
                .bodyToMono(Patient.class)
                .block();*/

        return webClient.get()
                //.uri("http://localhost:8081/patientfornote/1")
                .uri(Constants.URL_PATIENT_PATIENT, patientId)
                .retrieve()
                .bodyToMono(Patient.class)
                .block();

    }

}
