package dev.danvega.journey.mordor;

import dev.danvega.journey.bree.TravelTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// an agent: the same tool loop from Bree, given a goal instead of a question
@RestController
class MordorController {

    private final ChatClient chatClient;
    private final SyncMcpToolCallbackProvider mcpTools;

    MordorController(ChatClient.Builder builder, SyncMcpToolCallbackProvider mcpTools) {
        this.chatClient = builder
                .defaultSystem("""
                        You are a route planner for Middle-earth. Reach the goal using your tools.
                        Explore the routes one step at a time. Never invent a route.
                        Check the weather before crossing any mountain pass.
                        Avoid routes that are blocked or closed.
                        When you have a complete route, save it as a markdown file, then summarize it.
                        """)
                .build();
        this.mcpTools = mcpTools;
    }

    @GetMapping("/mordor/plan")
    String plan(@RequestParam String goal) {
        return chatClient.prompt()
                .user(goal)
                .tools(new TravelTools(), new RouteTools())
                .toolCallbacks(mcpTools)
                .call()
                .content();
    }
}
