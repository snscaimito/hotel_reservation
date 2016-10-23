package net.caimito.hotel.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoomRowMapper implements RowMapper<Room> {

	@Override
	public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
		Room room = new Room(rs.getString("designator"), rs.getString("room_type"));
		
		if (rs.getDate("blockedFromDate") != null)
			room.setBlockedFromDate(rs.getDate("blockedFromDate").toLocalDate()) ;
		
		if (rs.getDate("blockedToDate") != null)
			room.setBlockedToDate(rs.getDate("blockedToDate").toLocalDate()) ;
		
		return room;
	}

}
