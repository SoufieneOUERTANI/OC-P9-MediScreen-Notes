package com.ouertani.notes.repository;

import com.ouertani.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends MongoRepository<Note, String > {

    List<Note> findByCommentListCommentLikeIgnoreCase(String sickness);

}
