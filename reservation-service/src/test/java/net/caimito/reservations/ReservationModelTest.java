package net.caimito.reservations;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.* ;
import static org.hamcrest.Matchers.* ;

public class ReservationModelTest {

	@Test
	public void requestRoomWithoutRoomsAvailable() {
		FrontDesk frontDesk = new FrontDesk(null) ;

		ReservationRequest request = new ReservationRequest(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;
		List<Room> roomsAvailable = frontDesk.requestRoom(request) ;
		assertThat(roomsAvailable, is(emptyCollectionOf(Room.class))) ;
	}
	
	@Test
	public void requestRoomWithMatchingDates() {
		AvailabilityStrategy availabilityStrategy = new TestAvailabilityStrategyBuilder()
				.setAvailable(LocalDate.parse("2016-10-01"), LocalDate.parse("2016-11-01"))
				.build();
		FrontDesk frontDesk = new FrontDesk(availabilityStrategy) ;

		ReservationRequest request = new ReservationRequest(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;
		List<Room> roomsAvailable = frontDesk.requestRoom(request) ;
		assertThat(roomsAvailable, is(hasSize(1))) ;
	}
	
	@Test
	public void requestRoomOutsideAvailableDates() {
		AvailabilityStrategy availabilityStrategy = new TestAvailabilityStrategyBuilder()
				.setAvailable(LocalDate.parse("2016-09-01"), LocalDate.parse("2016-09-20"))
				.build();
		FrontDesk frontDesk = new FrontDesk(availabilityStrategy) ;

		ReservationRequest request = new ReservationRequest(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;
		List<Room> roomsAvailable = frontDesk.requestRoom(request) ;
		assertThat(roomsAvailable, is(emptyCollectionOf(Room.class))) ;
	}
	
	@Test
	public void reserveAvailableRoom() {
		AvailabilityStrategy availabilityStrategy = new TestAvailabilityStrategyBuilder()
				.setAvailable(LocalDate.parse("2016-10-01"), LocalDate.parse("2016-11-01"))
				.build();
		FrontDesk frontDesk = new FrontDesk(availabilityStrategy) ;

		ReservationRequest request = new ReservationRequest(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;
		List<Room> roomsAvailable = frontDesk.requestRoom(request) ;
		assertThat("Did not return at least one room", roomsAvailable, is(not(empty()))) ;
		
		Room roomToBeReserved = roomsAvailable.get(0) ;
		frontDesk.reserve(roomToBeReserved, LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")) ;
		assertThat("Room has not been reserved", frontDesk.isReserved(roomToBeReserved), is(true)) ;
	}
}
