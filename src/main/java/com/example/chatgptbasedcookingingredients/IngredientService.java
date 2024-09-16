package com.example.chatgptbasedcookingingredients;

import com.example.chatgptbasedcookingingredients.model.OpenAiMessage;
import com.example.chatgptbasedcookingingredients.model.OpenAiRequest;
import com.example.chatgptbasedcookingingredients.model.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class IngredientService {

    private RestClient restClient;

    public IngredientService(@Value("${NF_OPENAI_API_KEY}") String apiKey,
                             @Value("${OPENAI_API_URL}") String apiUrl) {

        this.restClient = RestClient.builder()
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .baseUrl(apiUrl)
                .build();
    }

    public String categorizeIngredient(String ingredient) {
        OpenAiRequest openAiRequest = new OpenAiRequest(
                "gpt-4o-mini",
                List.of(new OpenAiMessage("user", "Please categorize the following ingredient as either vegan, vegetarian or regular. Only answer with 'vegan', 'vegetarian', 'regular' or 'Not an ingredient': " + ingredient)),
                0.3
        );

        OpenAiResponse openAiResponse = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(openAiRequest)
                .retrieve().body(OpenAiResponse.class);

        return openAiResponse.getAnswer();
    }
}
