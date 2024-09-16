package com.example.chatgptbasedcookingingredients.model;

import java.util.List;

public record OpenAiRequest(
    String model,
    List<OpenAiMessage> messages,
    double temperature
) {
}
