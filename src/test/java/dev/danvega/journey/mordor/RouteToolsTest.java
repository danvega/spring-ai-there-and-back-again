package dev.danvega.journey.mordor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class RouteToolsTest {

    private final RouteTools routes = new RouteTools();

    // the demo depends on this path: Caradhras is snowed in, so the safe way goes through Moria
    @Test
    void safeRouteToMountDoomTakes22Days() {
        List<String> path = List.of("Rivendell", "Moria", "Lothlorien", "Emyn Muil",
                "Dead Marshes", "Cirith Ungol", "Mount Doom");

        int days = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String next = path.get(i + 1);
            days += routes.getRoutes(path.get(i)).stream()
                    .filter(route -> route.to().equals(next))
                    .findFirst()
                    .orElseThrow()
                    .days();
        }

        assertThat(days).isEqualTo(22);
    }

    @Test
    void acceptsSmallSpellingDifferences() {
        assertThat(routes.getRoutes("Lothlórien")).isNotEmpty();
        assertThat(routes.getRoutes("the Dead Marshes")).isNotEmpty();
        assertThat(routes.getRoutes("Mines of Moria")).isNotEmpty();
    }
}
