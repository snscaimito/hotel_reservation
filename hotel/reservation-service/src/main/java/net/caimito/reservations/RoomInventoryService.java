package net.caimito.reservations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

// TODO turn it into a RESTful service

@Component
public class RoomInventoryService {

	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	public void add(Room room) {
		jdbcTemplate.execute(String.format("INSERT INTO rooms (designator) VALUES('%s')", room.getRoomDesignator())); 
	}

	public void block(String roomDesignator, LocalDate fromDate, LocalDate toDate) {
		jdbcTemplate.execute(String.format("UPDATE rooms SET blockedFromDate = '%s', blockedToDate = '%s' WHERE designator = '%s'", fromDate.toString(), toDate.toString(), roomDesignator)) ;
	}

	public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
		List<Room> rooms = jdbcTemplate.query("SELECT * FROM rooms",
				new RowMapper<Room>() {

					@Override
					public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
						Room room = new Room(rs.getString("designator")) ;
						
						if (rs.getDate("blockedFromDate") != null)
							room.setBlockedFromDate(rs.getDate("blockedFromDate").toLocalDate()) ;
						
						if (rs.getDate("blockedToDate") != null)
							room.setBlockedToDate(rs.getDate("blockedToDate").toLocalDate()) ;
						
						return room;
					}
					
		}) ;
		
		// TODO write a SQL query for this
		List<Room> availableRooms = new ArrayList<>() ;
		
		for (Room room : rooms) {
			if (room.isAvailable(startDate, endDate))
				availableRooms.add(room) ;
		}
				
		return availableRooms ;
	}

}
