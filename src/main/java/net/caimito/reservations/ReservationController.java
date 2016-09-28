package net.caimito.reservations;

import java.util.HashMap;
import java.util.UUID;

public class ReservationController {
	private HashMap<UUID, Reservation> reservations = new HashMap<>() ;

	public void create(ReservationRequest request) {
		Reservation reservation = new Reservation() ;
		reservation.setId(UUID.randomUUID()) ;
		
		reservations.put(reservation.getId(), reservation) ;
	}

	public int hasReservations() {
		return reservations.size() ;
	}

}
