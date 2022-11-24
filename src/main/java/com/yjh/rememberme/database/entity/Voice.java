package com.yjh.rememberme.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_voice")
public class Voice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOICE_ID")
    private Integer id;
    @Column(name = "VOICE_DATE", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date loginDate;
    @Column(name = "VOICE_PATH", nullable = false)
    private String voicePath;
    @Column(name = "VOICE_NAME",nullable = false)
    private String voiceName;
    @Column(name = "MEMBER_ID", nullable = false)
    private int memberId;

}
