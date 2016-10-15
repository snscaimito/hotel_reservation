package net.caimito.reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomInventoryService {
	
	private Map<String, Room> rooms = new HashMap<>() ;

	public void add(Room room) {
		rooms.put(room.getRoomDesignator(), room) ;
	}

	public void block(String roomDesignator, LocalDate fromDate, LocalDate toDate) {
		rooms.get(roomDesignator).block(fromDate, toDate);
	}

	public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
		List<Room> availableRooms = new ArrayList<>() ;
		
		for (Room room : rooms.values()) {
			if (room.isAvailable(startDate, endDate))
				availableRooms.add(room) ;
		}
				
		return availableRooms ;
	}

}
