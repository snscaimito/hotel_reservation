package net.caimito.hotel.inventory;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.ResourceSupport;

@JsonComponent
public class RoomResource extends ResourceSupport {

	private Room room ;
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
	
}
