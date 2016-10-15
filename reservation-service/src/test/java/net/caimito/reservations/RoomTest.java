package net.caimito.reservations;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class RoomTest {

	@Test
	public void noBlockedDates() {
		Room room = new Room("Room 100") ;
		assertThat(room.isAvailable(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-15")), is(true)) ;
	}
	
	@Test
	public void availableAfterBlockedDates() {
		Room room = new Room("Room 100") ;
		room.block(LocalDate.parse("2016-09-10"), LocalDate.parse("2016-09-12"));
		
		assertThat(room.isAvailable(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-15")), is(true)) ;
	}

	@Test
	public void availableBeforeBlockedDates() {
		Room room = new Room("Room 100") ;
		room.block(LocalDate.parse("2016-11-10"), LocalDate.parse("2016-11-12"));
		
		assertThat(room.isAvailable(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-15")), is(true)) ;
	}
	
	@Test
	public void blockedPeriodInBetween() {
		Room room = new Room("Room 100") ;
		room.block(LocalDate.parse("2016-09-10"), LocalDate.parse("2016-09-12"));

		assertThat(room.isAvailable(LocalDate.parse("2016-09-05"), LocalDate.parse("2016-09-11")), is(false)) ;
	}
}
