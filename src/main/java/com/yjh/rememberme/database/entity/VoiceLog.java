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
    @Column(name = "voice_log_id")
    private Integer id;
    @Column(name = "voice_log_date", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date loginDate;
    @Column(name = "member_id", nullable = false)
    private Integer memberId;
    @Column(name = "opponent_id", nullable = false)
    private Integer opponentId;

}
