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

	public LocalDate getBlockedFromDate() {
		return blockedFromDate;
	}

	public void setBlockedFromDate(LocalDate blockedFromDate) {
		this.blockedFromDate = blockedFromDate;
	}

	public LocalDate getBlockedToDate() {
		return blockedToDate;
	}

	public void setBlockedToDate(LocalDate blockedToDate) {
		this.blockedToDate = blockedToDate;
	}

	public void block(LocalDate fromDate, LocalDate toDate) {
		setBlockedFromDate(fromDate) ;
		setBlockedToDate(toDate) ;
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
