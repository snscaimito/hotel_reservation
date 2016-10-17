package net.caimito.reservations;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// TODO find a way to do this
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationServicesTest extends ReservationTest {

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
