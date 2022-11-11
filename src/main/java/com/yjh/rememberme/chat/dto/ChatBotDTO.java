package com.yjh.rememberme.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class ChatBotDTO {

    @NotNull
    private String chatRequest;
    private String memberNickname;
    private String weNickname;



    public ChatBotDTO(){}

    public ChatBotDTO(String chatRequest, String memberNickname, String weNickname) {
        this.chatRequest = chatRequest;
        this.memberNickname = memberNickname;
        this.weNickname = weNickname;
    }

    public String getChatRequest() {
        return chatRequest;
    }

    public void setChatRequest(String chatRequest) {
        this.chatRequest = chatRequest;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getWeNickname() {
        return weNickname;
    }

    public void setWeNickname(String weNickname) {
        this.weNickname = weNickname;
    }

    @Override
    public String toString() {
        return "ChatBotDTO{" +
                "chatRequest='" + chatRequest + '\'' +
                ", memberNickname='" + memberNickname + '\'' +
                ", weNickname='" + weNickname + '\'' +
                '}';
    }
}