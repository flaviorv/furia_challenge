package com.furia.chat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FAN")
@Data @AllArgsConstructor @NoArgsConstructor
public class Fan {
    @Id
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "PROFILE_PICTURE")
    private String profilePicture;
    @Column(name = "BIO")
    private String bio;
    @Column(name = "ROLE_TYPE")
    private String roleType;

    public Fan(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
