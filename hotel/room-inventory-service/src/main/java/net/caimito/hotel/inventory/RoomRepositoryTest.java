package net.caimito.hotel.inventory;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import net.caimito.hotel.Room;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class RoomRepositoryTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	@Autowired
	private RoomRepository roomRepository ;
	
	@Test @Rollback
	public void findAvailableRooms() {
		roomRepository.add(new Room("Room 100"));
		
		assertThat(roomRepository.findAvailableRooms(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-15")), hasSize(1)) ;
	}

}
