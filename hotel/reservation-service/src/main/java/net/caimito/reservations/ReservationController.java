package net.caimito.reservations;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.caimito.hotel.HotelServiceUrls;
import net.caimito.hotel.inventory.Rooms;

@RestController
public class ReservationController {
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder ;

	@RequestMapping(HotelServiceUrls.RESERVATION_REQUEST)
	public HttpEntity<ReservationRequest> request(
			@RequestParam(value="requestStartDate", required=false, defaultValue="1900-01-01") String requestStartDate,
			@RequestParam(value="requestEndDate", required=false, defaultValue="1900-01-01") String requestEndDate
			) {
		ReservationRequest reservationRequest = new ReservationRequest(LocalDate.parse(requestStartDate), LocalDate.parse(requestEndDate)) ;
		reservationRequest.add(linkTo(methodOn(ReservationController.class).request(requestStartDate, requestEndDate)).withSelfRel());
		
		// TODO call service /rooms/findAvailableRooms
		RestTemplate restTemplate = restTemplateBuilder.build() ;
		
		Rooms availableRooms = restTemplate.getForObject(String.format("http://localhost:50001/rooms/findAvailableRooms?requestStartDate=%s&requestEndDate=%s", requestStartDate, requestEndDate), Rooms.class) ;
		reservationRequest.setRoomsAvailable(availableRooms.getRooms());
		
		// TODO turn actual rooms into a list of room classes. people cannot rent 'room 100' but the 'deluxe room' instead
		
		return new ResponseEntity<ReservationRequest>(reservationRequest, HttpStatus.OK) ;
	}

	@RequestMapping(HotelServiceUrls.RESERVATION_RESERVE)
	public HttpEntity<ReservationRequest> reserve(
			@RequestParam(value="room", required=false, defaultValue="room") String roomDesignator,
			@RequestParam(value="requestStartDate", required=false, defaultValue="1900-01-01") String requestStartDate,
			@RequestParam(value="requestEndDate", required=false, defaultValue="1900-01-01") String requestEndDate
			) {
		// TODO find a suitable class for this
		ReservationRequest reservationRequest = new ReservationRequest(LocalDate.parse(requestStartDate), LocalDate.parse(requestEndDate)) ;
		reservationRequest.add(linkTo(methodOn(ReservationController.class).request(requestStartDate, requestEndDate)).withSelfRel());
		
		return new ResponseEntity<ReservationRequest>(reservationRequest, HttpStatus.OK) ;
	}

}
