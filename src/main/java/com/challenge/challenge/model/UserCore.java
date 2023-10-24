package com.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user_table")
public class UserCore {
    @Id
    public String id = UUID.randomUUID().toString();
    protected String username;
    @JsonIgnore
    protected String password;
    @Transient
    protected List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
    protected String permissions;

    @JsonIgnore
    protected boolean firstAuth = true;
    @JsonIgnore
    protected boolean accountExpired = false;
    @JsonIgnore
    protected boolean accountLocked = false;
    @JsonIgnore
    protected boolean credentialsExpired = false;
    protected boolean accountEnabled = true;
    @OneToMany(mappedBy = "userCore")
    @JsonIgnore
    private List<Post> posts;
    @OneToMany(mappedBy = "userCore")
    @JsonIgnore
    private List<Comment> comments;
    @OneToOne(mappedBy = "userCore")
    private Likes likes;

    @JsonIgnore
    protected int risk;

//    @PrePersist
//    protected void runBeforeSave(){
//        this.permissions =  String.join(", ", this.authorityList.stream().map(auth -> auth.getAuthority()).collect(Collectors.toList()));
//    }


    public UserCore(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<SimpleGrantedAuthority> getAuthorityList() {
        if(this.permissions.contains(","))
            return Arrays.asList(this.permissions.split(",")).stream()
                .map(auth -> new SimpleGrantedAuthority(auth)).collect(Collectors.toList());
        if(this.permissions != null && !this.permissions.equals(""))
            return Arrays.asList(new SimpleGrantedAuthority(this.permissions));
        return new ArrayList<>();
    }

    public void addAuthority(String authority) {
        if (authorityList == null)
            authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(authority));
        permissions = String.join(",", this.authorityList.stream().map(auth -> auth.getAuthority()).collect(Collectors.toList()));
    }

    public String getAuthorities() {
        return permissions;
    }

    @Override
    public String toString() {
        return "UserCore{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorityList=" + authorityList +
                ", permissions='" + permissions + '\'' +
                ", firstAuth=" + firstAuth +
                ", accountExpired=" + accountExpired +
                ", accountLocked=" + accountLocked +
                ", credentialsExpired=" + credentialsExpired +
                ", accountEnabled=" + accountEnabled +
                ", risk=" + risk +
                '}';
    }
}
