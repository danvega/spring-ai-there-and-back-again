package dev.danvega.journey.shire;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.ai.converter.BeanOutputConverter;

class JourneyTest {

    // .entity(Journey.class) does this conversion for us, so we can test it without calling a model
    @Test
    void modelReplyBecomesJourney() {
        var converter = new BeanOutputConverter<>(Journey.class);
        String reply = """
                {
                  "traveler": "Frodo Baggins",
                  "departure": "2026-09-23",
                  "stops": [
                    {"place": "Bree", "arrival": "2026-09-29", "lodging": "The Prancing Pony", "confirmationCode": "PP-0929"},
                    {"place": "Mordor", "arrival": "2027-03-25", "lodging": null, "confirmationCode": "MD-0325"}
                  ]
                }
                """;

        Journey journey = converter.convert(reply);

        assertThat(journey.traveler()).isEqualTo("Frodo Baggins");
        assertThat(journey.departure()).isEqualTo(LocalDate.of(2026, 9, 23));
        assertThat(journey.stops()).extracting(Journey.Stop::place).containsExactly("Bree", "Mordor");
    }
}
