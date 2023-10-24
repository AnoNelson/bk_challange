package com.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "likes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
    @Id
    private String id = UUID.randomUUID().toString();
    @OneToOne
    @JsonIgnore
    private UserCore userCore;
    @ManyToOne
    @JsonIgnore
    private Post post;
    @Enumerated(value = EnumType.STRING)
    private EStatus status;
    private Date creationDate;
    private Date updateDate;

    @PrePersist
    public void beforeSave(){
        this.creationDate = new Date();
        this.setStatus(EStatus.ACTIVE);
    }
    @PreUpdate
    public void beforeUpdate(){
        this.updateDate = new Date();
    }
}
