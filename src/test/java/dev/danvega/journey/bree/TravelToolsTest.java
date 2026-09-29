package dev.danvega.journey.bree;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TravelToolsTest {

    private final TravelTools tools = new TravelTools();

    @Test
    void currentDateIsToday() {
        assertThat(tools.getCurrentDate()).isEqualTo(LocalDate.now().toString());
    }

    @Test
    void knownPlaceHasWeather() {
        assertThat(tools.getWeather("Rivendell")).isEqualTo("Cold and misty. Rain by evening.");
    }

    // Mordor: the blizzard is why the agent has to re-plan
    @Test
    void caradhrasIsSnowedIn() {
        assertThat(tools.getWeather("Caradhras")).startsWith("Blizzard");
    }

    // tells the model which places it can ask about, so it can try again
    @Test
    void unknownPlaceListsKnownPlaces() {
        assertThat(tools.getWeather("Isengard")).startsWith("Unknown place").contains("Rivendell");
    }
}
