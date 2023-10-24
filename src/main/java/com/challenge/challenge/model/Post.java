package com.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    @JsonIgnore
    private UserCore userCore;
    @OneToMany(mappedBy = "post")
    private List<Likes> likes;
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

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", status=" + status +
                '}';
    }
}
