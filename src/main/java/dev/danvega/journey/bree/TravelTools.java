package dev.danvega.journey.bree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.LocalDate;

public class TravelTools {

    private static final Logger log = LoggerFactory.getLogger(TravelTools.class);

    @Tool(description = "Get today's date")
    String getCurrentDate() {
        log.info("Tool called: getCurrentDate()");
        return LocalDate.now().toString();
    }

    @Tool(description = "Get the current weather for a place in Middle-earth")
    String getWeather(@ToolParam(description = "The place, for example Bree or Rivendell") String place) {
        log.info("Tool called: getWeather({})", place);
        return switch (place.toLowerCase()) {
            case "the shire", "shire", "hobbiton" -> "Sunny and mild. Perfect for second breakfast.";
            case "bree" -> "Grey skies and light drizzle.";
            case "rivendell" -> "Cold and misty. Rain by evening.";
            case "mordor" -> "Ash and smoke. Visibility poor. Do not linger.";
            default -> "Unknown. The map ends here.";
        };
    }

}
