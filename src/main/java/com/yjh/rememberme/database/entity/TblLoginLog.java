package com.yjh.rememberme.database.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "\"TBL_LOGIN_LOG\"")
public class TblLoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"LOGIN_LOG_ID\"", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "\"LOGIN_DATE\"", nullable = false)
    private Instant loginDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"MEMBER_ID\"", nullable = false)
    private TblMember memberId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Instant loginDate) {
        this.loginDate = loginDate;
    }

    public TblMember getMemberId() {
        return memberId;
    }

    public void setMemberId(TblMember memberId) {
        this.memberId = memberId;
    }

}