package com.example.chatgptbasedcookingingredients.model;

public record OpenAiMessage(
    String role,
    String content
) {
}
