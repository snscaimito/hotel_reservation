package net.caimito.hotel.inventory;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rooms")
public class RoomsController {
	private static Logger logger = LoggerFactory.getLogger(RoomsController.class) ;
	
	@Autowired
	private RoomRepository roomRepository ;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<RoomResource> add(@RequestBody Room roomPosted) {
		logger.info(roomPosted.toString()) ;
		Room room = roomRepository.add(roomPosted.getDesignator(), roomPosted.getRoomType());

		RoomResource roomResource = new RoomResource() ;
		roomResource.setRoom(room);
		roomResource.add(linkTo(methodOn(RoomsController.class).add(roomPosted)).withSelfRel());
		roomResource.add(linkTo(methodOn(RoomsController.class).block(roomPosted.getDesignator(), "1900-01-01", "1900-01-01")).withRel("block"));
		
		return new ResponseEntity<RoomResource>(roomResource, HttpStatus.OK) ;
	}

	@RequestMapping(path="/block/{designator}/{fromDate}/{toDate}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<RoomResource> block(
			@PathVariable(value="designator", required=false) String designator,
			@PathVariable(value="fromDate", required=false) String fromDate,
			@PathVariable(value="toDate", required=false) String toDate
			) {
		Room room = roomRepository.block(designator, LocalDate.parse(fromDate), LocalDate.parse(toDate));
		RoomResource roomResource = new RoomResource() ;
		roomResource.setRoom(room);
		
		return new ResponseEntity<RoomResource>(roomResource, HttpStatus.OK) ;
	}

	@RequestMapping(path = "/findAvailable", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<RoomsResource> findAvailableRooms(
			@RequestParam(value="fromDate", required=false, defaultValue="1900-01-01") String fromDate,
			@RequestParam(value="toDate", required=false, defaultValue="1900-01-01") String toDate
			) {

		RoomsResource rooms = new RoomsResource(new ArrayList<>()) ;
		rooms.setStartDateRequested(fromDate) ;
		rooms.setEndDateRequested(toDate) ;
		
		List<Room> roomsFound = roomRepository.findAvailableRooms(LocalDate.parse(fromDate), LocalDate.parse(toDate)) ;
		logger.info("rooms found: " + roomsFound);
		rooms.getRooms().addAll(roomsFound) ;

		rooms.add(linkTo(methodOn(RoomsController.class).findAvailableRooms(fromDate, toDate)).withSelfRel());
		return new ResponseEntity<RoomsResource>(rooms, HttpStatus.OK) ;
	}
}
