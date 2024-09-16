package com.example.chatgptbasedcookingingredients.model;

import java.util.List;

public record OpenAiResponse(
    List<OpenAiChoices> choices
) {
    public String getAnswer() {
        return choices().get(0).message().content();
    }
}
