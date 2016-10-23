package net.caimito.hotel.inventory;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RoomsControllerTest {

	private MockMvc mockMvc ;
	
	@Mock private RoomRepository roomRepository ;
	
	@InjectMocks
	private RoomsController roomsController ;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(roomsController).build();
	}
	
	@Test
	public void addRoom() throws Exception {
		when(roomRepository.add("100", "deluxe")).thenReturn(new Room("100", "deluxe")) ;
		
		mockMvc.perform(
				post("/rooms")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{ \"designator\" : \"100\", \"room_type\" : \"deluxe\" }")
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.room.designator", is("100")))
			.andExpect(jsonPath("$.links[0].rel", is("self")))
			.andExpect(jsonPath("$.links[0].href", is("http://localhost/rooms")))
			.andExpect(jsonPath("$.links[1].rel", is("block")))
			.andExpect(jsonPath("$.links[1].href", is("http://localhost/rooms/block/100/1900-01-01/1900-01-01"))) ;

		verify(roomRepository).add("100", "deluxe") ;
	}

	@Test
	@Ignore
	public void blockRoom() throws Exception {
		mockMvc.perform(put("/rooms/block/100/2016-10-12/2016-10-15"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.room.designator", is("100")));
	}

	@Test
	public void noAvailableRooms() throws Exception {
		mockMvc.perform(get("/rooms/findAvailable?fromDate=2016-10-12&toDate=2016-10-15"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.rooms", is(emptyCollectionOf(RoomResource.class))));
	}

	@Test
	public void findAvailableRooms() throws Exception {
		List<Room> rooms = new ArrayList<>() ;
		rooms.add(new Room("Room 100", "deluxe")) ;
		when(roomRepository.findAvailableRooms(any(), any())).thenReturn(rooms) ;
		
		mockMvc.perform(get("/rooms/findAvailable?fromDate=2016-10-12&toDate=2016-10-15"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.rooms", hasSize(1)));
	}
	
}
