package net.caimito.reservations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.caimito.hotel.Room;

public class ReservationRequest extends ResourceSupport {

	private LocalDate startDate;
	private LocalDate endDate;
	private List<Room> roomsAvailable ;

	@JsonCreator
	public ReservationRequest(@JsonProperty("requestStartDate") LocalDate startDate, 
			@JsonProperty("requestEndDate") LocalDate endDate) {
		this.startDate = startDate ;
		this.endDate = endDate ;
	}

	public static ReservationRequest createRequest(LocalDate startDate, LocalDate endDate) {
		return new ReservationRequest(startDate, endDate);
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setRoomsAvailable(List<Room> roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}
	
	public List<Room> getRoomsAvailable() {
		return roomsAvailable;
	}

}
