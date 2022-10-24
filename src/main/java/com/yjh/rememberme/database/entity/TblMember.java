package com.yjh.rememberme.database.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "\"TBL_MEMBER\"")
public class TblMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"MEMBER_ID\"", nullable = false)
    private Integer id;

    @NotNull
    @Lob
    @Column(name = "\"MEMBER_USERNAME\"", nullable = false)
    private String Username;

    @NotNull
    @Lob
    @Column(name = "\"MEMBER_PASSWORD\"", nullable = false)
    private String Password;

    @NotNull
    @Column(name = "\"MEMBER_REG_DATE\"", nullable = false)
    private Instant RegDate;

    @Lob
    @Column(name = "\"MEMBER_EMAIL\"")
    private String Email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Instant getRegDate() {
        return RegDate;
    }

    public void setRegDate(Instant RegDate) {
        this.RegDate = RegDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}