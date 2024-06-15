package com.bao.baocard.modules.openai.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {

    private String model;
    private List<Message> messages = new ArrayList<>();

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages.add(new Message("user", prompt));
    }
}
