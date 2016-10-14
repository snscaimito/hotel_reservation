package net.caimito.reservations;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface AvailabilityStrategy {

	List<Room> requestRoom(LocalDate startDate, LocalDate endDate);

}
