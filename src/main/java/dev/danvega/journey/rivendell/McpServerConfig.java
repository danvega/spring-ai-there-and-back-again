package dev.danvega.journey.rivendell;

import dev.danvega.journey.bree.TravelTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// your app as an MCP server: the same TravelTools from Bree, now any MCP client can call them
@Configuration
class McpServerConfig {

    @Bean
    ToolCallbackProvider travelTools() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(new TravelTools())
                .build();
    }
}
