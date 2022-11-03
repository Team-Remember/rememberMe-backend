package com.yjh.rememberme.database.entity;

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


}