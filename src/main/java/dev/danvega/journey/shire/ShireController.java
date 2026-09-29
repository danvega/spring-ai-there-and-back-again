package dev.danvega.journey.shire;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class ShireController {

    private final ChatClient chatClient;

    public ShireController(ChatClient.Builder builder) {
        this.chatClient = builder
                .build();
    }

    @GetMapping("/shire/ask")
    public String ask(@RequestParam String prompt) {
        var system = """
            You are a hobbit who has never left the Shire.
            Answer in 3 short bullet points.
            Be suspicious of adventures.
        """;

        return chatClient.prompt()
                .system(system)
                .user(prompt)
                .call()
                .content();
    }

    // streaming response
    @GetMapping("/shire/stream")
    public Flux<String> stream(@RequestParam String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .stream()
                .content();
    }

    // structured output: messy email in, typed Journey record out
    // point at: .entity(Journey.class), the LocalDate fields, stops in order
    @PostMapping("/shire/journey")
    public Journey journey(@RequestBody String email) {
        return chatClient.prompt()
                .system("Extract the journey details from this travel confirmation email. List the stops in order.")
                .user(email)
                .call()
                .entity(Journey.class);
    }

}
