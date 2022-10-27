package com.yjh.rememberme.database.entity;

import lombok.*;

import javax.persistence.*;
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
    @Lob
    @Column(name = "\"MEMBER_USERNAME\"", nullable = false)
    private String Username;

    @NotNull
    @Lob
    @Column(name = "\"MEMBER_PASSWORD\"", nullable = false)
    private String Password;

    @NotNull
    @Column(name = "\"MEMBER_REG_DATE\"", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date RegDate;

    @Lob
    @Column(name = "\"MEMBER_EMAIL\"")
    private String Email;

    @NotNull
    @Lob
    @Column(name = "member_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role Role;

    @Builder
    public Member(Integer id, String username, String password, Date regDate, String email, Role role) {
        this.id = id;
        Username = username;
        Password = password;
        RegDate = regDate;
        Email = email;
        Role = role;
    }
    public enum Role {
        USER("user"), ADMIN("admin");

        private String text;
        Role(String text) {this.text = text;}

        public String getText() { return text; }

    }

}