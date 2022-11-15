package com.yjh.rememberme.database.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import net.minidev.json.JSONArray;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"TBL_CHAT\"")
@TypeDef(name = "text", typeClass = JsonStringType.class)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"CHAT_ID\"", nullable = false)
    private Integer id;

//    @Type(type = "jsonb")
//    @Column(name = "\"CHAT_CONTENTS\"", nullable = false, columnDefinition = "jsonb")
    @Column(name = "\"CHAT_CONTENTS\"", nullable = false)
    @Type(type = "text")
    private List<Map<String,String>> data;


    @Column(name = "MEMBER_ID", nullable = false)
    private int memberId;

    @Column(name = "opponent_id", nullable = false)
    private int opponentId;

}
