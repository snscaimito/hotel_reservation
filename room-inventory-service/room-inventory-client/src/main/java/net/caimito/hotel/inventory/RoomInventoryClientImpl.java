package net.caimito.hotel.inventory;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RoomInventoryClientImpl implements RoomInventoryClient {
	private static Logger logger = LoggerFactory.getLogger(RoomInventoryClientImpl.class);

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Override
	public List<Room> findAvailable(LocalDate fromDate, LocalDate toDate) {
		String url = String.format("http://localhost:50001/rooms/findAvailable?fromDate=%s&toDate=%s",
				fromDate.toString(), toDate.toString());

		try {
			RestTemplate restTemplate = restTemplateBuilder.build();
			RoomsResource roomsResource = restTemplate.getForObject(url, RoomsResource.class);

			logger.info(roomsResource.toString());
			return roomsResource.getRooms();
		} catch (HttpClientErrorException e) {
			logger.info(e.getResponseHeaders().toString()) ;
			throw new RuntimeException(e) ;
		}
	}

}
