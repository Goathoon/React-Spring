package com.example.SpringAndReact.Domain;

import jakarta.persistence.*;



@Entity
public class Member {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Authority authority;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthority(Authority authority){
        this.authority = authority;
    }

    public void getAuthority(){
        this.authority = authority;
    }
}

;