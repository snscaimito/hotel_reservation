package net.caimito.reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeFrontDeskStrategy implements FrontDeskStrategy {
	private LocalDate availableFromDate = null ;
	private LocalDate availableToDate = null ;
	private Map<String, Room> rooms = new HashMap<>() ;

	protected FakeFrontDeskStrategy(LocalDate fromDate, LocalDate toDate) {
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

	@Override
	public void reserve(Room room, LocalDate startDate, LocalDate endDate) {
		rooms.put(room.getRoomDesignator(), room) ;
	}

	@Override
	public boolean isReserved(Room room) {
		return rooms.get(room.getRoomDesignator()) != null ;
	}

}
