package com.yjh.rememberme.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TBL_MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "MEMBER_USERNAME", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name = "MEMBER_NICKNAME", nullable = false, unique = true)
    private String nickname;

    @NotNull
    @JsonIgnore
    @Column(name = "MEMBER_PASSWORD", nullable = false)
    private String password;

    @NotNull
    @Column(name = "MEMBER_REG_DATE", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date regDate;

    @NotNull
    @Email
    @Column(name = "MEMBER_EMAIL", unique = true)
    private String email;

    @NotNull
    @Column(name = "MEMBER_ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(name = "MEMBER_STATUS")
    private String status;

    @Builder
    public Member(Integer id, String username, String nickname, String password, Date regDate, String email, Role role, String status) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.regDate = regDate;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public enum Role {
        USER("user"), ADMIN("admin");

        private final String text;
        Role(String text) {this.text = text;}

        public String getText() { return text; }

    }

}