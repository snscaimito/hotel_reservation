package net.caimito.hotel;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomCollectionService {
	private static final String BASE_URL = "/rooms" ;

	private static Logger logger = LoggerFactory.getLogger(RoomCollectionService.class) ;

	private RoomRepository roomRepository;

	public RoomCollectionService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository ;
	}

	public ServiceResponse<Collection<Room>> get(String uri) {
		if (!uri.startsWith(BASE_URL))
			throw new MalformedURIException(String.format("Invalid request %s", uri)) ;

		logger.info("GET {}", uri);
		ServiceResponse<Collection<Room>> response = getRooms() ;
		logger.info(response.toString());
		return response ;
	}

	private ServiceResponse<Collection<Room>> getRooms() {
		ServiceResponse<Collection<Room>> response = new ServiceResponse<>() ;
		response.status(ServiceResponse.OK);
		response.body().content(roomRepository.getRooms().values());
		return response;
	}

}
