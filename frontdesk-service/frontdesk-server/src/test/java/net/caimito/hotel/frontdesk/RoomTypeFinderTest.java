package net.caimito.hotel.frontdesk;

import org.junit.Test;

import net.caimito.hotel.inventory.Room;
import net.caimito.hotel.inventory.RoomInventoryFacade;

import static org.junit.Assert.* ;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.* ;

public class RoomTypeFinderTest {

	@Test
	public void noRoomTypesAvailable() {
		RoomInventoryFacade inventory = new RoomInventoryFacade() {
			@Override
			public List<Room> findAvailable(LocalDate fromDate, LocalDate toDate) {
				return new ArrayList<>();
			}
		};
		
		RoomTypeFinder finder = new RoomTypeFinder(inventory) ;
		assertThat(finder.findAvailableRoomTypes(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")), 
				emptyCollectionOf(String.class)) ;
	}

	@Test
	public void emptyHotelEverythingAvailable() {
		RoomInventoryFacade inventory = new RoomInventoryFacade() {
			@Override
			public List<Room> findAvailable(LocalDate fromDate, LocalDate toDate) {
				List<Room> rooms = new ArrayList<>();
				Room room = new Room("Room 100", "Deluxe Room");
				rooms.add(room) ;
				return rooms ;
			}
		};
		
		RoomTypeFinder finder = new RoomTypeFinder(inventory) ;
		assertThat(finder.findAvailableRoomTypes(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")), 
				hasSize(1)) ;
	}
	
}
