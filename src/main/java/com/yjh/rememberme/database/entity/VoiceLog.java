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
@Table(name = "tbl_voice_log")
public class VoiceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOICE_LOG_ID")
    private Integer id;
    @Column(name = "VOICE_LOG_DATE", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date loginDate;
    @Column(name = "MEMBER_ID", nullable = false)
    private Integer memberId;
    @Column(name = "OPPONENT_ID", nullable = false)
    private Integer opponentId;

}
