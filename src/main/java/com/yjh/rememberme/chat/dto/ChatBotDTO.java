package com.yjh.rememberme.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class ChatBotDTO {

    @NotNull
    private String chatRequest;
    private float memberId;
    private float weId;



    public ChatBotDTO(){}

    public ChatBotDTO(String chatRequest, float memberId, float weId) {
        this.chatRequest = chatRequest;
        this.memberId = memberId;
        this.weId = weId;
    }

    public String getChatRequest() {
        return chatRequest;
    }

    public void setChatRequest(String chatRequest) {
        this.chatRequest = chatRequest;
    }

    public float getMemberId() {
        return memberId;
    }

    public void setMemberId(float memberId) {
        this.memberId = memberId;
    }

    public float getWeId() {
        return weId;
    }

    public void setWeId(float weId) {
        this.weId = weId;
    }

    @Override
    public String toString() {
        return "ChatBotDTO{" +
                "chatRequest='" + chatRequest + '\'' +
                ", memberId=" + memberId +
                ", weId=" + weId +
                '}';
    }
}