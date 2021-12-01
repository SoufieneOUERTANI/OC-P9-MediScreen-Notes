package com.ouertani.notes.repository;

import com.ouertani.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public interface NotesRepository extends MongoRepository<Note, String > {

    List<Note> findByCommentListCommentLikeIgnoreCase(String sickness);

}
