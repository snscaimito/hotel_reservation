package net.caimito.reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestAvailabilityStrategy implements AvailabilityStrategy {
	private LocalDate availableFromDate = null ;
	private LocalDate availableToDate = null ;

	protected TestAvailabilityStrategy(LocalDate fromDate, LocalDate toDate) {
		this.availableFromDate = fromDate ;
		this.availableToDate = toDate ;
	}
	
	@Override
	public List<Room> requestRoom(LocalDate startDate, LocalDate endDate) {
		List<Room> availableRooms = new ArrayList<>() ;
		
		if (startDate.isAfter(availableFromDate) && endDate.isBefore(availableToDate)) {
			availableRooms.add(new Room()) ;
		}
		
		return availableRooms ;
	}

}
