package net.caimito.reservations;

import static org.junit.Assert.* ;
import static org.hamcrest.Matchers.* ;

import org.junit.Test;

import net.caimito.reservations.ReservationController;

public class ReservationTest {

	@Test
	public void createNewReservation() {
		ReservationController controller = new ReservationController() ;
		
		controller.create(new ReservationRequest()) ;
		
		assertThat(controller.hasReservations(), is(1)) ;
	}

	@Test
	public void cancelReservation() {
		throw new NotYetImplementedException() ;
	}

	@Test
	public void changeReservation() {
		throw new NotYetImplementedException() ;
	}

	@Test
	public void findReservationByToken() {
		throw new NotYetImplementedException() ;
	}

}
