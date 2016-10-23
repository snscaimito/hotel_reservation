package net.caimito.hotel.inventory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	public Room add(String designator, String roomType) {
		jdbcTemplate.execute(String.format("INSERT INTO rooms (designator, room_type) VALUES('%s', '%s')", designator, roomType));
		return new Room(designator, roomType) ;
	}

	public Room block(String roomDesignator, LocalDate fromDate, LocalDate toDate) {
		jdbcTemplate.execute(String.format("UPDATE rooms SET blockedFromDate = '%s', blockedToDate = '%s' WHERE designator = '%s'", fromDate.toString(), toDate.toString(), roomDesignator)) ;
		return null ;
	}

	public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
		List<Room> rooms = jdbcTemplate.query("SELECT * FROM rooms", new RoomRowMapper()) ;
		
		// TODO write a SQL query for this
		List<Room> availableRooms = new ArrayList<>() ;
		
		for (Room room : rooms) {
			if (room.isAvailable(startDate, endDate))
				availableRooms.add(room) ;
		}
				
		return availableRooms ;
	}

	public Room find(String designator) {
		try {
			return jdbcTemplate.queryForObject(String.format("SELECT * FROM rooms WHERE designator='%s'", designator), new RoomRowMapper()) ;
		} catch (EmptyResultDataAccessException e) {
			throw new RoomNotFoundException("Room not found.", e) ;
		}
	}

}
