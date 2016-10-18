package net.caimito.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomService {
	private static Logger logger = LoggerFactory.getLogger(RoomService.class) ;
	
	public static final String BASE_URL = "/room";
	
	private RoomRepository roomRepository;
	
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository ;
	}

	public ServiceResponse<Room> get(String uri) {
		checkBaseUrl(uri);
		logger.info("GET {}", uri);
		
		String[] entities = uri.split("/") ; 
		ServiceResponse<Room> response = getRoom(entities[2]) ;
		
		logger.info(response.toString());
		return response ;
	}

	public ServiceResponse<Room> put(String uri) {
		checkBaseUrl(uri);
		logger.info("PUT {}", uri);
		
		String[] entities = uri.split("/") ;
		ServiceResponse<Room> response = blockRoom(entities[2], entities[3], entities[4]) ;
		
		logger.info(response.toString());
		return response ;
	}

	public ServiceResponse<Room> post(String uri, Room room) {
		checkBaseUrl(uri);
		logger.info("POST {}. Body = {}", uri, room);
		ServiceResponse<Room> response = post(room) ;
		logger.info(response.toString());
		return response ;
	}
	
	private void checkBaseUrl(String uri) {
		if (!uri.startsWith(BASE_URL))
			throw new MalformedURIException(String.format("Invalid request %s", uri)) ;
	}
	
	private ServiceResponse<Room> post(Room room) {
		roomRepository.getRooms().put(room.getDesignator(), room) ;
		
		ServiceResponse<Room> response = new ServiceResponse<>();
		response.status(ServiceResponse.OK) ;
		response.body().uri(String.format("%s/%s", BASE_URL, room.getDesignator())) ;
		
		logger.info(response.toString());
		return response ;
	}

	private ServiceResponse<Room> getRoom(String roomDesignator) {
		ServiceResponse<Room> response = new ServiceResponse<>() ;

		Room room = roomRepository.getRooms().get(roomDesignator) ;
		if (room != null) {
			response.body().uri(String.format("%s/%s", BASE_URL, room.getDesignator())) ;
			response.body().content(room) ;
			response.status(ServiceResponse.OK) ;
		} else
			response.status(ServiceResponse.NOT_FOUND);
		
		return response ;
	}

	private ServiceResponse<Room> blockRoom(String roomDesignator, String fromDate, String toDate) {
		ServiceResponse<Room> response = new ServiceResponse<>() ;

		Room room = roomRepository.getRooms().get(roomDesignator) ;
		if (room != null) {
			room.setBlockedEndDate(toDate);
			room.setBlockedStartDate(fromDate);
			response.status(ServiceResponse.OK) ;
			response.body().uri(String.format("%s/%s", BASE_URL, room.getDesignator())) ;
			response.body().content(room) ;
		} else
			response.status(ServiceResponse.NOT_FOUND);
		
		return response;
	}


}
