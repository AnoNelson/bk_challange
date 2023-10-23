package com.challenge.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "likes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    private String id;
    @OneToOne
    private UserCore userCore;
    private Date date;
    @ManyToOne
    private Post post;
    @Enumerated(value = EnumType.STRING)
    private EStatus status;
    private Date creationDate;

    @PrePersist
    public void beforeSave(){
        this.creationDate = new Date();
        this.setStatus(EStatus.ACTIVE);
    }

}
