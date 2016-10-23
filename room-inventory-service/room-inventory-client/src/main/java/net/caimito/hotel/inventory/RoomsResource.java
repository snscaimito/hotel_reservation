package net.caimito.hotel.inventory;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomsResource extends ResourceSupport {

	private List<Room> rooms ;
	private @JsonProperty String startDateRequested ;
	private @JsonProperty String endDateRequested ;
	
	public RoomsResource() {
	}
	
	@JsonCreator
	public RoomsResource(@JsonProperty List<Room> rooms) {
		this.rooms = rooms ;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String getStartDateRequested() {
		return startDateRequested;
	}

	public void setStartDateRequested(String startDateRequested) {
		this.startDateRequested = startDateRequested;
	}

	public String getEndDateRequested() {
		return endDateRequested;
	}

	public void setEndDateRequested(String endDateRequested) {
		this.endDateRequested = endDateRequested;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}
	
}
