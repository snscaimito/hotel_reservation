package net.caimito.reservations;

import java.time.LocalDate;

public class Room {
	private String roomDesignator = "DEFAULT_NOT_FOR_PRODUCTION" ;
	private LocalDate blockedFromDate;
	private LocalDate blockedToDate;
	
	public Room(String designator) {
		this.roomDesignator = designator ;
	}

	public String getRoomDesignator() {
		return roomDesignator;
	}

	public void block(LocalDate fromDate, LocalDate toDate) {
		this.blockedFromDate = fromDate ;
		this.blockedToDate = toDate ;
	}

	public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
		if (blockedFromDate == null && blockedToDate == null)
			return true ;

		if (startDate.isAfter(blockedToDate))
			return true ;
		
		if (endDate.isBefore(blockedFromDate))
			return true ;
		
		return false ;
	}

}
