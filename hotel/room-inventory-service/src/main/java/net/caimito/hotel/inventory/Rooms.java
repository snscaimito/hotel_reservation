package net.caimito.hotel.inventory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.caimito.hotel.Room;

public class Rooms extends ResourceSupport {

	private List<Room> rooms ;
	private @JsonProperty LocalDate startDateRequested ;
	private @JsonProperty LocalDate endDateRequested ;
	
	@JsonCreator
	public Rooms(@JsonProperty List<Room> rooms) {
		this.rooms = rooms ;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}

	public LocalDate getStartDateRequested() {
		return startDateRequested;
	}

	public void setStartDateRequested(LocalDate startDateRequested) {
		this.startDateRequested = startDateRequested;
	}

	public LocalDate getEndDateRequested() {
		return endDateRequested;
	}

	public void setEndDateRequested(LocalDate endDateRequested) {
		this.endDateRequested = endDateRequested;
	}
	
}
