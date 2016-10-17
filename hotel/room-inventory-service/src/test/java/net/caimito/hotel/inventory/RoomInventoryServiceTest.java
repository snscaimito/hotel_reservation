package net.caimito.hotel.inventory;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.caimito.hotel.Room;

public class RoomInventoryServiceTest {

	private MockMvc mockMvc ;
	
	@Mock private RoomRepository roomRepository ;
	
	@InjectMocks
	private RoomInventoryController roomInventoryController ;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(roomInventoryController).build();
	}
	
	@Test
	public void noAvailableRooms() throws Exception {
		mockMvc.perform(get("/rooms/findAvailableRooms?requestStartDate=2016-10-12&requestEndDate=2016-10-15"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.rooms", is(emptyCollectionOf(Room.class))));
	}

	@Test
	public void findAvailableRooms() throws Exception {
		List<Room> rooms = new ArrayList<>() ;
		rooms.add(new Room("Room 100")) ;
		when(roomRepository.findAvailableRooms(any(), any())).thenReturn(rooms) ;
		
		mockMvc.perform(get("/rooms/findAvailableRooms?requestStartDate=2016-10-12&requestEndDate=2016-10-15"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.rooms", hasSize(1)));
	}

}
