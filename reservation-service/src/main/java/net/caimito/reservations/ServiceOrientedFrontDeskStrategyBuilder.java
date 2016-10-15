package net.caimito.reservations;

import java.time.LocalDate;

public class ServiceOrientedFrontDeskStrategyBuilder implements FrontDeskStrategyBuilder {

	private RoomInventoryService roomInventory = new RoomInventoryService() ;
	
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
