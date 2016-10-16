package net.caimito.reservations;

import java.time.LocalDate;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Reservation {

	private UUID id ;
	private LocalDate startDate ;
	private LocalDate endDate ;
	
	protected Reservation(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate ;
		this.endDate = endDate ;
	}

	public void setId(UUID id) {
		this.id = id ;
	}
	
	public UUID getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this).toString() ;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}
