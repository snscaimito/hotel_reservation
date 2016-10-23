package net.caimito.hotel.frontdesk;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.caimito.hotel.inventory.Room;
import net.caimito.hotel.inventory.RoomInventoryFacade;

@Component
public class RoomTypeFinder {
	private static Logger logger = LoggerFactory.getLogger(RoomTypeFinder.class) ;

	private RoomInventoryFacade roomInventory ;
	
	@Autowired
	public RoomTypeFinder(RoomInventoryFacade roomInventoryFacade) {
		this.roomInventory = roomInventoryFacade ;
	}
	
	public Set<String> findAvailableRoomTypes(LocalDate fromDate, LocalDate toDate) {
		List<Room> availableRooms = findAvailableRooms(fromDate, toDate) ;
		return filterRoomTypesBasedOnRooms(availableRooms) ;
	}

	private Set<String> filterRoomTypesBasedOnRooms(List<Room> availableRooms) {
		Set<String> types = new HashSet<>() ;
		
		for (Room room : availableRooms) {
			types.add(room.getRoomType()) ;
		}
		
		return types;
	}

	private List<Room> findAvailableRooms(LocalDate fromDate, LocalDate toDate) {
		List<Room> rooms = roomInventory.findAvailable(fromDate, toDate);
		logger.info(String.format("Found rooms %s", rooms));
		return rooms ;
	}

}
