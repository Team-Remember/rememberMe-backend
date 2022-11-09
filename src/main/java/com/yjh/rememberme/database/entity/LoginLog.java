package com.yjh.rememberme.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"TBL_LOGIN_LOG\"")
public class LoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"LOGIN_LOG_ID\"", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "\"LOGIN_DATE\"", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date loginDate;

    @Column(name = "MEMBER_ID", nullable = false)
    private int memberId;

}