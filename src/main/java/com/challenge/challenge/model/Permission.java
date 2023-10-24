package com.challenge.challenge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Permission {
    @Id
    private int id;
    private String name;
}
