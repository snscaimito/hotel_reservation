package net.caimito.hotel.inventory;

import java.time.LocalDate;

import org.apache.commons.lang.builder.* ;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {

	private String designator ;
	private String roomType ;
	private LocalDate blockedFromDate ;
	private LocalDate blockedToDate ;
	
	public Room() {
	}
	
	public Room(String designator, String roomType) {
		this.designator = designator ;
		this.roomType = roomType ;
	}

	public String getDesignator() {
		return designator ;
	}

	public void setBlockedFromDate(LocalDate date) {
		this.blockedFromDate = date ;
	}
	
	public void setBlockedToDate(LocalDate blockedToDate) {
		this.blockedToDate = blockedToDate;
	}

	public boolean isAvailable(LocalDate startDate, LocalDate endDate) {
		if (blockedFromDate == null && blockedToDate == null)
			return true ;

		if (startDate.isAfter(blockedToDate))
			return true ;
		
		if (endDate.isBefore(blockedFromDate))
			return true ;
		
		return false ;
	}

	@JsonProperty("room_type")
	public String getRoomType() {
		return roomType;
	}

	@JsonProperty("room_type")
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj) ;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this) ;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}
	
}
