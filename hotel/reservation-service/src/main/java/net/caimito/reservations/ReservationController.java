package net.caimito.reservations;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

	@RequestMapping("/reservation")
	public HttpEntity<ReservationRequest> request(
			@RequestParam(value="requestStartDate", required=false, defaultValue="1900-01-01") String requestStartDate,
			@RequestParam(value="requestEndDate", required=false, defaultValue="1900-01-01") String requestEndDate
			) {
		ReservationRequest reservationRequest = new ReservationRequest(LocalDate.parse(requestStartDate), LocalDate.parse(requestEndDate)) ;
		reservationRequest.add(linkTo(methodOn(ReservationController.class).request(requestStartDate, requestEndDate)).withSelfRel());
		
		return new ResponseEntity<ReservationRequest>(reservationRequest, HttpStatus.OK) ;
	}
}
