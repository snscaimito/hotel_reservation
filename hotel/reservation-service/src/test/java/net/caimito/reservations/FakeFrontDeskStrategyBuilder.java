package net.caimito.reservations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FakeFrontDeskStrategyBuilder implements FrontDeskStrategyBuilder {
	private Map<String, Room> rooms = new HashMap<>();

	@Override
	public FrontDeskStrategy build() {
		return new FakeFrontDeskStrategy(rooms) ;
	}

	@Override
	public FrontDeskStrategyBuilder addRoom(Room room) {
		this.rooms.put(room.getRoomDesignator(), room) ;
		
		return this;
	}

	@Override
	public FrontDeskStrategyBuilder makeReserved(String roomDesignator, LocalDate fromDate, LocalDate toDate) {
		this.rooms.get(roomDesignator).block(fromDate, toDate) ;
		
		return this;
	}

}
