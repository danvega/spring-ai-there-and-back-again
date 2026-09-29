package dev.danvega.journey.mordor;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

public class RouteTools {

    private static final Logger log = LoggerFactory.getLogger(RouteTools.class);

    public record Route(String to, int days, String conditions) {}

    private static final Map<String, List<Route>> ROUTES = Map.of(
            "rivendell", List.of(
                    new Route("Caradhras", 2, "A high mountain pass. Check the weather before crossing."),
                    new Route("Moria", 4, "Old mines under the mountains. Open, but something stirs in the deep.")),
            "caradhras", List.of(new Route("Lothlorien", 3, "Down the far side of the mountains.")),
            "moria", List.of(new Route("Lothlorien", 2, "A forest of the elves. Safe.")),
            "lothlorien", List.of(new Route("Emyn Muil", 6, "By boat down the great river.")),
            "emyn muil", List.of(new Route("Dead Marshes", 3, "Do not follow the lights.")),
            "dead marshes", List.of(
                    new Route("Black Gate", 2, "Closed and heavily guarded."),
                    new Route("Cirith Ungol", 4, "A hidden pass. Unguarded, mostly.")),
            "black gate", List.of(new Route("Mount Doom", 2, "Only if the gate opens. It will not.")),
            "cirith ungol", List.of(new Route("Mount Doom", 3, "The end of the road.")));

    @Tool(description = "List the routes leaving a place in Middle-earth, with travel days and conditions")
    List<Route> getRoutes(@ToolParam(description = "The place to leave from, for example Rivendell") String from) {
        log.info("Tool called: getRoutes({})", from);
        return ROUTES.getOrDefault(from.toLowerCase(), List.of());
    }

    @Tool(description = "Ask the Great Eagles to fly you to a destination")
    String requestEagles(@ToolParam(description = "Where you want to fly") String destination) {
        log.info("Tool called: requestEagles({})", destination);
        return "Declined. The Eagles are not a taxi service. Walk.";
    }
}
