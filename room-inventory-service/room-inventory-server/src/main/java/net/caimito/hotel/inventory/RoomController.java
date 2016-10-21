package net.caimito.hotel.inventory;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomRepository roomRepository ;
	
	@RequestMapping(path="/{designator}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<RoomResource> add(
			@PathVariable(value="designator", required=false) String designator
			) {
		Room room = roomRepository.add(designator);

		RoomResource roomResource = new RoomResource() ;
		roomResource.setRoom(room);
		roomResource.add(linkTo(methodOn(RoomController.class).add(designator)).withSelfRel());
		roomResource.add(linkTo(methodOn(RoomController.class).block(designator, "1900-01-01", "1900-01-01")).withRel("block"));
		
		return new ResponseEntity<RoomResource>(roomResource, HttpStatus.OK) ;
	}

	@RequestMapping(path="/{designator}/{fromDate}/{toDate}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<RoomResource> block(
			@PathVariable(value="designator", required=false) String designator,
			@PathVariable(value="fromDate", required=false) String fromDate,
			@PathVariable(value="toDate", required=false) String toDate
			) {
		roomRepository.block(designator, LocalDate.parse(fromDate), LocalDate.parse(toDate));

		// TODO fake!
		Room room = new Room(designator) ;
		room.setBlockedFromDate(LocalDate.parse(fromDate));
		room.setBlockedToDate(LocalDate.parse(toDate));
		RoomResource roomResource = new RoomResource() ;
		roomResource.setRoom(room);
		
		return new ResponseEntity<RoomResource>(roomResource, HttpStatus.OK) ;
	}
}
