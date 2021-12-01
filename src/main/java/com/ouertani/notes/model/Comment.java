package com.ouertani.notes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private String comment;
    private Date creationDate;
    private Date updateDate;
}
