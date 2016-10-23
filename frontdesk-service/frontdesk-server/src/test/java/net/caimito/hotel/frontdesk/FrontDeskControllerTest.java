package net.caimito.hotel.frontdesk;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.* ;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.* ;

public class FrontDeskControllerTest {

	private MockMvc mockMvc ;
	
	@Mock private RoomTypeFinder roomTypeFinder ;

	@InjectMocks
	private FrontDeskController frontDeskController ;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(frontDeskController).build() ;
	}
	
	@Test
	public void findAvailableRooms() throws Exception {
		Set<String> roomTypesAvailable = new HashSet<>(Arrays.asList("Deluxe", "Regular")) ;
		when(roomTypeFinder.findAvailableRoomTypes(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-18")))
			.thenReturn(roomTypesAvailable) ;
		
		mockMvc.perform(get("/frontdesk/find_available_rooms?fromDate=2016-10-12&toDate=2016-10-18"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.roomTypes", hasItem("Regular")))
			.andExpect(jsonPath("$.roomTypes", hasItem("Deluxe")));
		// TODO links
	}
	
}
