package net.caimito.hotel.inventory;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.caimito.hotel.HotelServiceUrls;
import net.caimito.hotel.Room;

@Controller
public class RoomInventoryController {
	@Autowired
	private RoomRepository roomRepository ;
	
	@RequestMapping(path = HotelServiceUrls.ROOM_INVENTORY_FIND_AVAILABLE_ROOMS, method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Rooms> findAvailableRooms(
			@RequestParam(value="requestStartDate", required=false, defaultValue="1900-01-01") String requestStartDate,
			@RequestParam(value="requestEndDate", required=false, defaultValue="1900-01-01") String requestEndDate
			) {

		final LocalDate startDate = LocalDate.parse(requestStartDate) ;
		final LocalDate endDate = LocalDate.parse(requestEndDate) ;
		
		Rooms rooms = new Rooms(new ArrayList<>()) ;
		rooms.setStartDateRequested(startDate) ;
		rooms.setEndDateRequested(endDate) ;
		
		List<Room> roomsFound = roomRepository.findAvailableRooms(startDate, endDate) ;
		rooms.getRooms().addAll(roomsFound) ;
		
		rooms.add(linkTo(methodOn(RoomInventoryController.class).findAvailableRooms(requestStartDate, requestEndDate)).withSelfRel());
		return new ResponseEntity<Rooms>(rooms, HttpStatus.OK) ;
	}
}
