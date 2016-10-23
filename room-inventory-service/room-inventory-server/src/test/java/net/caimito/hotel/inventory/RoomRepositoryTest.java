package net.caimito.hotel.inventory;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@Transactional
public class RoomRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private RoomRepository roomRepository ;
	
	@Test
	public void add() {
		Room room = roomRepository.add("Room 100", "deluxe");
		assertThat(countRowsInTable("rooms"), is(1)) ;
		assertThat(room.getDesignator(), is("Room 100")) ;
		assertThat(room.getRoomType(), is("deluxe")) ;
	}

	@Test(expected=RoomNotFoundException.class)
	public void doesNotFind() {
		roomRepository.find("Room 100") ;
	}
	
	@Test
	public void find() {
		roomRepository.add("Room 100", "deluxe");
		
		Room roomFound = roomRepository.find("Room 100") ;
		assertThat(roomFound.getDesignator(), is("Room 100")) ;
		assertThat(roomFound.getRoomType(), is("deluxe")) ;
	}
	
	@Test @Rollback
	public void findAvailableRooms() {
		Room room = roomRepository.add("Room 100", "deluxe");

		assertThat(countRowsInTable("rooms"), is(1)) ;
		
		assertThat(room.getDesignator(), is("Room 100")) ;
		assertThat(roomRepository.findAvailableRooms(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-15")), hasSize(1)) ;
	}

	@Test @Rollback
	public void blockRoom() {
		roomRepository.add("Room 100", "deluxe");
		
		roomRepository.block("Room 100", LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-15"));
		assertThat(roomRepository.findAvailableRooms(LocalDate.parse("2016-10-12"), LocalDate.parse("2016-10-15")), emptyCollectionOf(Room.class)) ; ;
	}
}
