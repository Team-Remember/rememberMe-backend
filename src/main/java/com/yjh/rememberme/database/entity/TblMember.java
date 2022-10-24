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
    private String memberUsername;

    @NotNull
    @Lob
    @Column(name = "\"MEMBER_PASSWORD\"", nullable = false)
    private String memberPassword;

    @NotNull
    @Column(name = "\"MEMBER_REG_DATE\"", nullable = false)
    private Instant memberRegDate;

    @Lob
    @Column(name = "\"MEMBER_EMAIL\"")
    private String memberEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public Instant getMemberRegDate() {
        return memberRegDate;
    }

    public void setMemberRegDate(Instant memberRegDate) {
        this.memberRegDate = memberRegDate;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

}