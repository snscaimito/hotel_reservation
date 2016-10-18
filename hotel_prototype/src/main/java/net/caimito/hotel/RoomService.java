package net.caimito.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomService {
	private static Logger logger = LoggerFactory.getLogger(RoomService.class) ;
	
	public static final String BASE_URL = "/room";

	public ServiceResponse<Room> post(Room room) {
		logger.info("POST {}. Body = {}", BASE_URL, room);
		ServiceResponse<Room> response = new ServiceResponse<>();
		response.status(ServiceResponse.OK) ;
		response.body(new ServiceResponseBody<>()) ;
		response.body().uri(String.format("%s/%s", BASE_URL, room.getDesignator())) ;
		return response ;
	}

	public ServiceResponse get(String roomDesignator) {
		logger.info("GET {}/{}. Body = {}", BASE_URL, roomDesignator, null);
		ServiceResponse<Room> response = new ServiceResponse<>() ;
		response.status(ServiceResponse.NOT_FOUND);
		return response ;
	}

}
