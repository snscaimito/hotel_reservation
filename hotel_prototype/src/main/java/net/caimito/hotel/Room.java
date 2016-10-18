package net.caimito.hotel;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Room {
	private String designator ;

	public Room(String designator) {
		this.designator = designator ;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}

	public Object getDesignator() {
		return designator;
	}

}
