package dev.danvega.journey.bree;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BreeController {

    private final ChatClient chatClient;

    BreeController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/bree/ask")
    String ask(@RequestParam String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .tools(new TravelTools())
                .call()
                .content();
    }

}
