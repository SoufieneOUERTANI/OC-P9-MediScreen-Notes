package com.ouertani.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// TODO : Récupérer l'age et sexe à partir de la base de données des patients
// TODO : log
// TODO : Tests et couvertures
// TODO : Faire du parallélisme
// TODO : Delete a patient should delete the notes


@SpringBootApplication
@EnableMongoRepositories("com.ouertani.notes.repository")
public class NotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }

}
