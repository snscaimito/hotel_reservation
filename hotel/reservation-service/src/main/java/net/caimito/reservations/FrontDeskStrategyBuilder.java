package net.caimito.reservations;

import java.time.LocalDate;

public interface FrontDeskStrategyBuilder {

	FrontDeskStrategy build();

	FrontDeskStrategyBuilder addRoom(Room room);

	FrontDeskStrategyBuilder makeReserved(String roomDesignator, LocalDate fromDate, LocalDate toDate);

}