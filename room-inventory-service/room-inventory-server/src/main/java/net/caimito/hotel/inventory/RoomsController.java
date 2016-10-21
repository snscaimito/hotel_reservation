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

@Controller
@RequestMapping("/rooms")
public class RoomsController {
	@Autowired
	private RoomRepository roomRepository ;
	
	@RequestMapping(path = "/findAvailable", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<RoomsResource> findAvailableRooms(
			@RequestParam(value="fromDate", required=false, defaultValue="1900-01-01") String fromDate,
			@RequestParam(value="toDate", required=false, defaultValue="1900-01-01") String toDate
			) {

		final LocalDate startDate = LocalDate.parse(fromDate) ;
		final LocalDate endDate = LocalDate.parse(toDate) ;
		
		RoomsResource rooms = new RoomsResource(new ArrayList<>()) ;
		rooms.setStartDateRequested(startDate) ;
		rooms.setEndDateRequested(endDate) ;
		
		List<Room> roomsFound = roomRepository.findAvailableRooms(startDate, endDate) ;
		rooms.getRooms().addAll(roomsFound) ;

		rooms.add(linkTo(methodOn(RoomsController.class).findAvailableRooms(fromDate, toDate)).withSelfRel());
		return new ResponseEntity<RoomsResource>(rooms, HttpStatus.OK) ;
	}
}
