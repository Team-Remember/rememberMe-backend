package com.yjh.rememberme.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TBL_CHAT")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_ID", nullable = false)
    private Integer id;

    @NotNull
    @Lob
    @Column(name = "CHAT_CONTENT", nullable = false)
    private String chatContent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Builder
    public Chat(Integer id, String chatContent, Member member) {
        this.id = id;
        this.chatContent = chatContent;
        this.member = member;
    }
}