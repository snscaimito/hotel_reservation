package net.caimito.hotel.inventory;

import java.time.LocalDate;
import java.util.List;

public interface RoomInventoryClient {

	List<Room> findAvailable(LocalDate fromDate, LocalDate toDate);

}