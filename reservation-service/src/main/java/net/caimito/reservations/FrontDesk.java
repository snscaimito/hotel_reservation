package net.caimito.reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FrontDesk {

	private AvailabilityStrategy availabilityStrategy;

	public FrontDesk(AvailabilityStrategy availabilityStrategy) {
		this.availabilityStrategy = availabilityStrategy ;
	}
	
	public List<Room> requestRoom(ReservationRequest request) {
		if (availabilityStrategy == null)
			return new ArrayList<>() ;
		
		return availabilityStrategy.requestRoom(request.getStartDate(), request.getEndDate()) ;
	}

	public void reserve(Room room, LocalDate startDate, LocalDate endDate) {
	}

	public boolean isReserved(Room roomToBeReserved) {
		return false;
	}

}
