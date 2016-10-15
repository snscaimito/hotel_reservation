package net.caimito.reservations;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class ReservationServicesTest extends ReservationTest {

	public ReservationServicesTest() {
		this.strategyBuilder = new ServiceOrientedFrontDeskStrategyBuilder() ;
	}
	
//	private MockMvc mockMvc;
//	
//	@Autowired
//    private WebApplicationContext webApplicationContext;
//	
//	@Before
//    public void setup() throws Exception {
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();
//    }
//	
//	@Test
//	public void requestReservation() throws Exception {
//		mockMvc.perform(get("/reservation"))
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.startDate", is("1900-01-01")))
//			.andExpect(jsonPath("$.endDate", is("1900-01-01"))) ;
//	}

}
