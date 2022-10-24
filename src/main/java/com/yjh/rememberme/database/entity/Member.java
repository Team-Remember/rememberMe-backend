package com.yjh.rememberme.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"TBL_MEMBER\"")
public class Member {
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
    private Date RegDate;

    @Lob
    @Column(name = "\"MEMBER_EMAIL\"")
    private String Email;

    @NotNull
    @Lob
    @Column(name = "member_role", nullable = false)
    private String Role;


}