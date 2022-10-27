package com.yjh.rememberme.database.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.Array;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "\"TBL_CHAT\"")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"CHAT_ID\"", nullable = false)
    private Integer id;

    @NotNull
    @Type(type = "json")
    @Column(name = "\"CHAT_CONTENTS\"", nullable = false, columnDefinition = "json")
    private List<Map<String, java.lang.Object>> chatContents;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"MEMBER_ID\"", nullable = false)
    private Member member;

    @Builder
    public Chat(Integer id, List<Map<String, java.lang.Object>> chatContents, Member member) {
        this.id = id;
        this.chatContents = chatContents;
        this.member = member;
    }
}