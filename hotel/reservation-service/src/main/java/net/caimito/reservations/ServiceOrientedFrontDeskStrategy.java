package net.caimito.reservations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrientedFrontDeskStrategy implements FrontDeskStrategy {

	private RoomInventoryService roomInventoryService ;
	
	public ServiceOrientedFrontDeskStrategy(RoomInventoryService roomInventory) {
		this.roomInventoryService = roomInventory ;
	}

	@Override
	public List<Room> requestRoom(LocalDate startDate, LocalDate endDate) {
		return roomInventoryService.findAvailableRooms(startDate, endDate) ;
	}

	@Override
	public void reserve(Room room, LocalDate startDate, LocalDate endDate) {
		roomInventoryService.block(room.getRoomDesignator(), startDate, endDate);
	}

}
