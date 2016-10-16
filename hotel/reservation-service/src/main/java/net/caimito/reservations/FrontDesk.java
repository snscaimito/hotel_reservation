package net.caimito.reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FrontDesk {

	private FrontDeskStrategy frontDeskStrategy;

	public FrontDesk(FrontDeskStrategy frontDeskStrategy) {
		this.frontDeskStrategy = frontDeskStrategy ;
	}
	
	public List<Room> requestRoom(ReservationRequest request) {
		if (frontDeskStrategy == null)
			return new ArrayList<>() ;
		
		return frontDeskStrategy.requestRoom(request.getStartDate(), request.getEndDate()) ;
	}

	public void reserve(Room room, LocalDate startDate, LocalDate endDate) {
		frontDeskStrategy.reserve(room, startDate, endDate) ;
	}

}
