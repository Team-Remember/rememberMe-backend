package com.yjh.rememberme.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@Entity
@Table(name = "\"TBL_CHARACTER\"")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"CHARACTER_ID\"", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "\"CHARACTER_HAIR_NUM\"", nullable = false)
    private String hairNum;

    @NotNull
    @Column(name = "\"CHARACTER_JACKET_NUM\"", nullable = false)
    private String jacketNum;

    @NotNull
    @Column(name = "\"CHARACTER_CHEST_NUM\"", nullable = false)
    private String chestNum;

    @NotNull
    @Column(name = "\"CHARACTER_TIE_NUM\"", nullable = false)
    private String tieNum;

    @NotNull
    @Column(name = "\"CHARACTER_LEGS_NUM\"", nullable = false)
    private String legsNum;

    @NotNull
    @Column(name = "\"CHARACTER_FEET_NUM\"", nullable = false)
    private String feetNum;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"MEMBER_ID\"", nullable = false)
    private Member member;

    @Builder
    public Character(Integer id, String hairNum, String jacketNum, String chestNum, String tieNum, String legsNum, String feetNum, Member member) {
        this.id = id;
        this.hairNum = hairNum;
        this.jacketNum = jacketNum;
        this.chestNum = chestNum;
        this.tieNum = tieNum;
        this.legsNum = legsNum;
        this.feetNum = feetNum;
        this.member = member;
    }
}