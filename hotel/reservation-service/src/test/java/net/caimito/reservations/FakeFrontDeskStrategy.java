package net.caimito.reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.caimito.hotel.Room;

public class FakeFrontDeskStrategy implements FrontDeskStrategy {
	private Map<String, Room> rooms ;

	protected FakeFrontDeskStrategy(Map<String, Room> rooms) {
		this.rooms = rooms ;
	}
	
	@Override
	public List<Room> requestRoom(LocalDate startDate, LocalDate endDate) {
		List<Room> availableRooms = new ArrayList<>() ;
		
		for (Room room : rooms.values()) {
			if (room.isAvailable(startDate, endDate))
				availableRooms.add(room) ;
		}
				
		return availableRooms ;
	}

	@Override
	public void reserve(Room room, LocalDate startDate, LocalDate endDate) {
		room.block(startDate, endDate);
		rooms.put(room.getRoomDesignator(), room) ;
	}

}
