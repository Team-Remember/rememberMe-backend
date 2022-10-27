package com.yjh.rememberme.database.entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
@Getter
@NoArgsConstructor
@Entity
@Table(name = "\"TBL_MEMBER\"")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"MEMBER_ID\"", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "\"MEMBER_USERNAME\"", nullable = false)
    private String username;

    @NotNull
    @Column(name = "\"MEMBER_PASSWORD\"", nullable = false)
    private String password;

    @NotNull
    @Column(name = "\"MEMBER_REG_DATE\"", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date regDate;

    @NotNull
    @Column(name = "\"MEMBER_EMAIL\"")
    private String email;

    @NotNull
    @Column(name = "\"MEMBER_ROLE\"", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(Integer id, String username, String password, Date regDate, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.email = email;
        this.role = role;
    }

    public enum Role {
        USER("user"), ADMIN("admin");

        private String text;
        Role(String text) {this.text = text;}

        public String getText() { return text; }

    }

}