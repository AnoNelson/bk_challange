package com.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment{
    @Id
    private String id = UUID.randomUUID().toString();
    @ManyToOne
    @JsonIgnore
    private UserCore userCore;
    private String comment;
    @ManyToOne
    @JsonIgnore
    private Post post;
    private Date creationDate;

    @PrePersist
    public void beforeSave(){
        this.creationDate = new Date();
    }

}
