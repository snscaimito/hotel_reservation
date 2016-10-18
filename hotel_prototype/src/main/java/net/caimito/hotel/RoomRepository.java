package net.caimito.hotel;

import java.util.HashMap;
import java.util.Map;

public class RoomRepository {
	private Map<String, Room> rooms = new HashMap<>() ;

	public Map<String, Room> getRooms() {
		return rooms;
	}

}
