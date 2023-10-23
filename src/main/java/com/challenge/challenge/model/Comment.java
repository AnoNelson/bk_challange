package com.challenge.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment{
    @Id
    private String id;
    @ManyToOne
    private UserCore userCore;
    private Date date;
    private String comment;
    @ManyToOne
    private Post post;
    private Date creationDate;

    @PrePersist
    public void beforeSave(){
        this.creationDate = new Date();
    }

}
