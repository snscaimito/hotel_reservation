package net.caimito.reservations;

import java.time.LocalDate;

public class ReservationRequest {

	private LocalDate startDate;
	private LocalDate endDate;

	private ReservationRequest(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate ;
		this.endDate = endDate ;
	}

	public static ReservationRequest createRequest(LocalDate startDate, LocalDate endDate) {
		return new ReservationRequest(startDate, endDate);
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

}
