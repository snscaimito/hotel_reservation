package net.caimito.hotel.frontdesk;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/frontdesk")
public class FrontDeskController {
	
	@Autowired
	private RoomTypeFinder roomTypeFinder ;

	@RequestMapping(path = "/find_available_rooms", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<RoomTypeResource> findAvailableRooms(
			@RequestParam(value="fromDate", required=false, defaultValue="1900-01-01") String fromDate,
			@RequestParam(value="toDate", required=false, defaultValue="1900-01-01") String toDate
			) {
		RoomTypeResource roomTypeResource = new RoomTypeResource() ;
		roomTypeResource.setRoomTypes(roomTypeFinder.findAvailableRoomTypes(
				LocalDate.parse(fromDate), LocalDate.parse(toDate)));
		
		return new ResponseEntity<RoomTypeResource>(roomTypeResource, HttpStatus.OK) ;
	}
	
}
