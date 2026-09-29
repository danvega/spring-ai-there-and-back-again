package dev.danvega.journey.shire;

import java.time.LocalDate;
import java.util.List;

record Journey(String traveler,
               LocalDate departure,
               List<Stop> stops) {

    record Stop(String place,
                LocalDate arrival,
                String lodging,
                String confirmationCode) {}

}


