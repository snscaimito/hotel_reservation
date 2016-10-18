package net.caimito.hotel;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class RoomTest {

	private RoomService service = new RoomService();

	@Test
	public void createNewRoom() {
		ServiceResponse<Room> response = service.post(new Room("100")) ;
		assertThat(response.status(), is(ServiceResponse.OK));
		assertThat(response.body().uri(), is(String.format("%s/100", RoomService.BASE_URL))) ;
	}

	@Test
	public void getNoData() {
		ServiceResponse response = service.get("100");
		assertThat(response.status(), is(ServiceResponse.NOT_FOUND));
	}
	
}
