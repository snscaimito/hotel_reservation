package net.caimito.reservations;

import java.time.LocalDate;

public class TestAvailabilityStrategyBuilder {
	private LocalDate availableFromDate = null ;
	private LocalDate availableToDate = null ;

	public TestAvailabilityStrategyBuilder setAvailable(LocalDate fromDate, LocalDate toDate) {
		this.availableFromDate = fromDate ;
		this.availableToDate = toDate ;
		
		return this;
	}
	
	public AvailabilityStrategy build() {
		return new TestAvailabilityStrategy(availableFromDate, availableToDate) ;
	}

}
