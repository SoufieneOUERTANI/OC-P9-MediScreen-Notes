package com.ouertani.notes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.stereotype.Repository;

import java.time.LocalDate;
//import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "notes")
public class Note {
    @Id
    private String patientId;

    private String gender;

    private LocalDate dob;

    private List<Comment> commentList;

    @PersistenceConstructor
    public Note(String patientId, List<Comment> commentList) {
        this.patientId = patientId;
        this.commentList = commentList;
    }
}
