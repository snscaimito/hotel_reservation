package net.caimito.hotel.frontdesk;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.ResourceSupport;

@JsonComponent
public class RoomTypeResource extends ResourceSupport {

	private Set<String> roomTypes = new HashSet<>() ;
	
	public Set<String> getRoomTypes() {
		return roomTypes;
	}
	
	public void setRoomTypes(Set<String> roomTypes) {
		this.roomTypes = roomTypes;
	}
	
}
