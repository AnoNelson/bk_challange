package com.challenge.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    @Id
    private String id = UUID.randomUUID().toString();
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Date creationDate;
    private Date updateDate;
    @Enumerated(value = EnumType.STRING)
    private EStatus status;
    @ManyToOne
    private UserCore userCore;
    @OneToMany(mappedBy = "post")
    private List<Like> likes;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @PrePersist
    public void beforeCreate(){
        this.creationDate = new Date();
        this.setStatus(EStatus.ACTIVE);
    }

    @PreUpdate
    public void beforeUpdate(){
        this.updateDate = new Date();
    }
}
