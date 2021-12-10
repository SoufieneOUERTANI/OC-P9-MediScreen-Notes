package com.ouertani.notes.repository;

import com.ouertani.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface NotesRepository extends MongoRepository<Note, String> {

    Note findByPatientIdEquals(@NonNull String patientId);

}
