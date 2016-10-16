package net.caimito.reservations;

import java.time.LocalDate;
import java.util.List;

public interface FrontDeskStrategy {

	List<Room> requestRoom(LocalDate startDate, LocalDate endDate);

	void reserve(Room room, LocalDate startDate, LocalDate endDate);

}
