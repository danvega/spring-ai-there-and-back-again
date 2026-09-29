package dev.danvega.journey.rivendell;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class McpServerConfigTest {

    // the same TravelTools from Bree are the tools we share over MCP
    @Test
    void sharesTravelTools() {
        var tools = new McpServerConfig().travelTools().getToolCallbacks();

        assertThat(tools).extracting(tool -> tool.getToolDefinition().name())
                .containsExactlyInAnyOrder("getCurrentDate", "getWeather");
    }
}
