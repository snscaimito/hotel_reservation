package net.caimito.hotel.inventory;

public class RoomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4961374853840873578L;

	public RoomNotFoundException(String message) {
		super(message) ;
	}

	public RoomNotFoundException(String message, Throwable cause) {
		super(message, cause) ;
	}

}
