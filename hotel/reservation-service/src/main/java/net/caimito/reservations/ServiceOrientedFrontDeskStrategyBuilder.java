package net.caimito.reservations;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrientedFrontDeskStrategyBuilder implements FrontDeskStrategyBuilder {

	@Autowired
	private RoomInventoryService roomInventory ;
	
	@Override
	public FrontDeskStrategy build() {
		return new ServiceOrientedFrontDeskStrategy(roomInventory) ;
	}

	@Override
	public FrontDeskStrategyBuilder addRoom(Room room) {
		roomInventory.add(room) ;
		
		return this;
	}

	@Override
	public FrontDeskStrategyBuilder makeReserved(String roomDesignator, LocalDate fromDate, LocalDate toDate) {
		roomInventory.block(roomDesignator, fromDate, toDate) ;
		
		return this ;
	}

}
