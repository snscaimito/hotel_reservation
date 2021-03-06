package net.caimito.hotel;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Collection;

public class RoomTest {
	
	private RoomRepository roomRepository ;
	
	@Before
	public void setup() {
		roomRepository = new RoomRepository() ;
	}

	@Test
	public void createNewRoom() {
		RoomService roomService = new RoomService(roomRepository);
		ServiceResponse<Room> response = roomService.post("/room", new Room("100")) ;
		assertThat(response.status(), is(ServiceResponse.OK));
		assertThat(response.body().uri(), is("/room/100")) ;
	}

	@Test
	public void getNoData() {
		RoomService roomService = new RoomService(roomRepository);
		ServiceResponse<Room> response = roomService.get("/room/100");
		assertThat(response.status(), is(ServiceResponse.NOT_FOUND));
	}
	
	@Test
	public void getExistingRoom() {
		RoomService roomService = new RoomService(roomRepository);
		roomService.post("/room", new Room("100")) ;

		ServiceResponse<Room> response = roomService.get("/room/100");
		assertThat(response.status(), is(ServiceResponse.OK));
		assertThat(response.body().uri(), is("/room/100")) ;
		assertThat(response.body().content(), isA(Room.class)) ;
		assertThat(response.body().content().getDesignator(), is("100")) ;
		
		// TODO check for links to actions
	}
	
	@Test
	public void blockRoom() {
		RoomService roomService = new RoomService(roomRepository);
		roomService.post("/room", new Room("100")) ;

		ServiceResponse<Room> response = roomService.put("/room/100/2016-10-12/2016-10-15") ;
		assertThat(response.status(), is(ServiceResponse.OK));
		assertThat(response.body().uri(), is("/room/100")) ;
		assertThat(response.body().content().getBlockedStartDate(), is("2016-10-12")) ;
		assertThat(response.body().content().getBlockedEndDate(), is("2016-10-15")) ;
		
		// TODO check for links to actions
	}
	
	@Test
	public void getAllRooms() {
		RoomService roomService = new RoomService(roomRepository);
		roomService.post("/room", new Room("100")) ;
		roomService.post("/room", new Room("200")) ;
		
		RoomCollectionService roomCollectionService = new RoomCollectionService(roomRepository) ;
		ServiceResponse<Collection<Room>> responseAllRooms = roomCollectionService.get("/rooms") ;
		assertThat(responseAllRooms.status(), is(ServiceResponse.OK));
		assertThat(responseAllRooms.body().content(), hasSize(2)) ;
		
		// TODO check for links to actions
	}
}
