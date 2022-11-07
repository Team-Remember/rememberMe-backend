package com.yjh.rememberme.database.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
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
    @Column(name = "\"CHAT_CONTENTS\"", nullable = false, columnDefinition = "jsonb")
    private List<Map<String, java.lang.Object>> data;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"MEMBER_ID\"", nullable = false)
    private Member member;

}