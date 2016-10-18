package net.caimito.hotel;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Room {
	private String designator ;
	private String blockedStartDate ;
	private String blockedEndDate ;

	public Room(String designator) {
		this.designator = designator ;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}

	public String getDesignator() {
		return designator;
	}

	public String getBlockedStartDate() {
		return blockedStartDate;
	}

	public void setBlockedStartDate(String blockedStartDate) {
		this.blockedStartDate = blockedStartDate;
	}

	public String getBlockedEndDate() {
		return blockedEndDate;
	}

	public void setBlockedEndDate(String blockedEndDate) {
		this.blockedEndDate = blockedEndDate;
	}

}
