package com.ouertani.notes.webClient;

import com.ouertani.notes.DTO.Patient;
import com.ouertani.notes.constants.Constants;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PatientWebClient {

    private static final String BASE_URL = "http://localhost:8081/";

    private static final WebClient webClient = WebClient.builder()
            .baseUrl(BASE_URL)
            .build();

    public Patient getPatient(Long patientId){


        return webClient.get().uri(Constants.PATIENT_PATIENT, patientId)
                .retrieve()
                .bodyToMono(Patient.class)
                .block();
    }

}
