package net.caimito.hotel.inventory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.* ;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.* ;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RoomControllerTest {

	private MockMvc mockMvc ;
	
	@Mock private RoomRepository roomRepository ;
	
	@InjectMocks
	private RoomController roomController ;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
	}
	
	@Test
	public void addRoom() throws Exception {
		when(roomRepository.add("100")).thenReturn(new Room("100")) ;
		
		mockMvc.perform(put("/room/100"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//			.andDo(new ResultHandler() {
//				
//				@Override
//				public void handle(MvcResult result) throws Exception {
//					System.out.println(result.getResponse().getContentAsString()) ;
//				}
//			})
			.andExpect(jsonPath("$.room.designator", is("100")))
			.andExpect(jsonPath("$.links[0].rel", is("self")))
			.andExpect(jsonPath("$.links[0].href", is("http://localhost/room/100")))
			.andExpect(jsonPath("$.links[1].rel", is("block")))
			.andExpect(jsonPath("$.links[1].href", is("http://localhost/room/100/1900-01-01/1900-01-01"))) ;

		verify(roomRepository).add("100") ;
	}

	@Test
	public void blockRoom() throws Exception {
		mockMvc.perform(put("/room/100/2016-10-12/2016-10-15"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.room.designator", is("100")));
	}

}
