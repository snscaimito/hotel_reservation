package net.caimito.reservations;

import java.util.HashMap;
import java.util.UUID;

public class ReservationController {
	private HashMap<UUID, Reservation> reservations = new HashMap<>() ;

	public Reservation create(ReservationRequest request) {
		Reservation reservation = new Reservation(request.getStartDate(), request.getEndDate()) ;
		reservation.setId(UUID.randomUUID()) ;
		
		reservations.put(reservation.getId(), reservation) ;
		
		return reservation ;
	}

	public int hasReservations() {
		return reservations.size() ;
	}

	public Reservation findByToken(UUID id) {
		return reservations.get(id);
	}

	public void cancel(UUID id) {
		reservations.remove(id) ;
	}

}
