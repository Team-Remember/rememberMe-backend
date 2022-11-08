package com.yjh.rememberme.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class ChatBotDTO {

    @NotNull
    private String chatRequest;
    @NotNull
    private int userId;
    @NotNull
    private int weId;

    public ChatBotDTO(){}


    public ChatBotDTO(String chatRequest, int userId, int weId) {
        this.chatRequest = chatRequest;
        this.userId = userId;
        this.weId = weId;
    }

    public String getChatRequest() {
        return chatRequest;
    }

    public void setChatRequest(String chatRequest) {
        this.chatRequest = chatRequest;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWeId() {
        return weId;
    }

    public void setWeId(int weId) {
        this.weId = weId;
    }

    @Override
    public String toString() {
        return "ChatBotDTO{" +
                "chatRequest='" + chatRequest + '\'' +
                ", userId=" + userId +
                ", weId=" + weId +
                '}';
    }
}