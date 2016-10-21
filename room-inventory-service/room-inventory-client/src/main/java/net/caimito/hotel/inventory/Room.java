package net.caimito.hotel.inventory;

import java.time.LocalDate;

public class Room {

	private String designator ;
	private LocalDate blockedFromDate ;
	private LocalDate blockedToDate ;
	
	public Room(String designator) {
		this.designator = designator ;
	}

	public String getDesignator() {
		return designator ;
	}

	public void setBlockedFromDate(LocalDate date) {
		this.blockedFromDate = date ;
	}
	
	public void setBlockedToDate(LocalDate blockedToDate) {
		this.blockedToDate = blockedToDate;
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
