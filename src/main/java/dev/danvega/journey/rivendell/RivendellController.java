package dev.danvega.journey.rivendell;

import dev.danvega.journey.bree.TravelTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// your app as an MCP client: our own tools plus the filesystem server's tools
@RestController
class RivendellController {

    private final ChatClient chatClient;
    private final SyncMcpToolCallbackProvider mcpTools;

    RivendellController(ChatClient.Builder builder, SyncMcpToolCallbackProvider mcpTools) {
        this.chatClient = builder.build();
        this.mcpTools = mcpTools;
    }

    @GetMapping("/rivendell/ask")
    String ask(@RequestParam String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .tools(new TravelTools())
                .toolCallbacks(mcpTools)
                .call()
                .content();
    }
}
