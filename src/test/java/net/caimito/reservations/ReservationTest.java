package net.caimito.reservations;

import static org.junit.Assert.* ;

import java.time.LocalDate;

import static org.hamcrest.Matchers.* ;

import org.junit.Before;
import org.junit.Test;

import net.caimito.reservations.ReservationController;

public class ReservationTest {

	protected ReservationController controller = null ;
	
	@Before
	public void setup() {
		controller = new ReservationController() ;
	}
	
	@Test
	public void createNewReservation() {
		controller.create(createRequest("2016-10-01", "2016-10-05")) ;
		assertThat(controller.hasReservations(), is(1)) ;
	}

	@Test
	public void cancelReservation() {
		final Reservation reservationCreated = controller.create(createRequest("2016-10-01", "2016-10-05")) ;
		
		controller.cancel(reservationCreated.getId()) ;
		assertThat(controller.hasReservations(), is(0)) ;
	}

	@Test
	public void findReservationByToken() {
		final Reservation reservationCreated = controller.create(createRequest("2016-10-01", "2016-10-05")) ;

		Reservation reservationActual = controller.findByToken(reservationCreated.getId()) ;
		assertThat(reservationActual, is(reservationCreated)) ;
	}

	protected ReservationRequest createRequest(String startDate, String endDate) {
		return ReservationRequest.createRequest(LocalDate.parse(startDate), LocalDate.parse(endDate));
	}

}
