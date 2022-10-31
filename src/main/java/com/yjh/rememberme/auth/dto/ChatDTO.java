package com.yjh.rememberme.auth.dto;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.yjh.rememberme.database.entity.Object;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private List<Map<String, java.lang.Object>> data;
}

