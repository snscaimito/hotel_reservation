package net.caimito.reservations;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.* ;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

public abstract class ReservationTest {

	protected FrontDeskStrategyBuilder strategyBuilder ;
	
	@Test
	public void requestRoomWithoutRoomsAvailable() {
		FrontDesk frontDesk = new FrontDesk(strategyBuilder
				.addRoom(new Room("Room 100"))
				.makeReserved("Room 100", LocalDate.parse("2016-10-10"), LocalDate.parse("2016-10-20"))
				.build()) ;

		ReservationRequest request = new ReservationRequest(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;
		List<Room> roomsAvailable = frontDesk.requestRoom(request) ;
		assertThat(roomsAvailable, is(emptyCollectionOf(Room.class))) ;
	}
	
	@Test
	public void requestRoomNothingBlocked() {
		FrontDesk frontDesk = new FrontDesk(strategyBuilder
				.addRoom(new Room("Room 100"))
				.build()) ;

		ReservationRequest request = new ReservationRequest(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;
		List<Room> roomsAvailable = frontDesk.requestRoom(request) ;
		assertThat(roomsAvailable, is(hasSize(1))) ;
	}
	
	@Test
	public void reserveAvailableRoom() {
		FrontDesk frontDesk = new FrontDesk(strategyBuilder
				.addRoom(new Room("Room 100"))
				.build()) ;

		ReservationRequest request = new ReservationRequest(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;
		List<Room> roomsAvailable = frontDesk.requestRoom(request) ;
		assertThat("No list of rooms returned", roomsAvailable, is(notNullValue())) ;
		assertThat("Did not return at least one room", roomsAvailable, is(not(empty()))) ;
		
		Room roomToBeReserved = roomsAvailable.get(0) ;
		frontDesk.reserve(roomToBeReserved, LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;

		// only one room exists. if it is not available anymore, the reservation above has blocked it
		assertThat("Room has not been reserved", 
				frontDesk.requestRoom(new ReservationRequest(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18"))), 
				is(emptyCollectionOf(Room.class))) ;
	}
	
}
