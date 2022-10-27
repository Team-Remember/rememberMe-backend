package com.yjh.rememberme.database.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"TBL_CHARACTER\"")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"CHARACTER_ID\"", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "\"CHARACTER_STATE_A\"", nullable = false)
    private String characterStateA;

    @NotNull
    @Column(name = "\"CHARACTER_STATE_B\"", nullable = false)
    private String characterStateB;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"MEMBER_ID\"", nullable = false)
    private Member member;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCharacterStateA() {
        return characterStateA;
    }

    public void setCharacterStateA(String characterStateA) {
        this.characterStateA = characterStateA;
    }

    public String getCharacterStateB() {
        return characterStateB;
    }

    public void setCharacterStateB(String characterStateB) {
        this.characterStateB = characterStateB;
    }

    public Member getMember() {
        return member;
    }

    public void setMemberId(Member member) {
        this.member = member;
    }

}