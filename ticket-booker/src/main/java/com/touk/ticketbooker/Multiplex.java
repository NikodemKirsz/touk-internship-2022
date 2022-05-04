package com.touk.ticketbooker;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Multiplex {
    private Multiplex() {}

    public static final String MULTIPLEX_NAME = "Nic's Theatre";

    public static final double TICKET_PRICE_ADULT = 25.0;
    public static final double TICKET_PRICE_STUDENT = 18.0;
    public static final double TICKET_PRICE_CHILD = 12.5;
    public static final int TICKET_EXPIRATION_TIME = 30;
    public static final int MAX_RESERVATION_TIME_BEFORE_SCREENING = 15;

    public static final int DEFAULT_SEARCH_LENGTH = 1440; // One day

    public static final int MIN_NAME_LENGTH = 3;
    public static final int MIN_SURNAME_LENGTH = 3;
    public static final String SURNAME_SEPARATOR = "-";
    public static final int MIN_SURNAME_PART_LENGTH = 3;

    public static final int ROOM_ROWS = 10;
    public static final int ROOM_SEATS_PER_ROW = 20;
    public static final int ROOM_SEATS_CAPACITY = ROOM_ROWS * ROOM_SEATS_PER_ROW;

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Europe/Warsaw");
    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER =
            DateTimeFormatter
                    .ofPattern("yyyy-MM-dd_HH:mm") // Example: 2022-04-30_20:21
                    .withZone(DEFAULT_ZONE_ID);
}
