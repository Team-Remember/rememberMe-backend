package com.yjh.rememberme.database.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_OBJECT")
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OBJECT_ID", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "OBJECT_PLACE_A")
    private String objectPlaceA;

    @Lob
    @Column(name = "OBJECT_PLACE_B")
    private String objectPlaceB;

    @Lob
    @Column(name = "OBJECT_PLACE_C")
    private String objectPlaceC;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectPlaceA() {
        return objectPlaceA;
    }

    public void setObjectPlaceA(String objectPlaceA) {
        this.objectPlaceA = objectPlaceA;
    }

    public String getObjectPlaceB() {
        return objectPlaceB;
    }

    public void setObjectPlaceB(String objectPlaceB) {
        this.objectPlaceB = objectPlaceB;
    }

    public String getObjectPlaceC() {
        return objectPlaceC;
    }

    public void setObjectPlaceC(String objectPlaceC) {
        this.objectPlaceC = objectPlaceC;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}