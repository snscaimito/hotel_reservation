package net.caimito.reservations;

import java.time.LocalDate;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationRequest extends ResourceSupport {

	private LocalDate startDate;
	private LocalDate endDate;

	@JsonCreator
	public ReservationRequest(@JsonProperty("requestStartDate") LocalDate startDate, 
			@JsonProperty("requestEndDate") LocalDate endDate) {
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
