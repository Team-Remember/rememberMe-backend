package com.yjh.rememberme.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_CHAT_BOT")
public class ChatBot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_BOT_ID")
    private Integer chatBotId;

    @NotNull
    @Column(name = "CHAT_BOT_DATE", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date chatBotDate;

    @NotNull
    @Column(name = "MEMBER_ID")
    private Integer memberId;

}
