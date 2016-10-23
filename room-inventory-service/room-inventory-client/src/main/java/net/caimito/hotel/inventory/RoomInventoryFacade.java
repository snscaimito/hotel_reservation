package net.caimito.hotel.inventory;

import java.time.LocalDate;
import java.util.List;

public interface RoomInventoryFacade {

	List<Room> findAvailable(LocalDate fromDate, LocalDate toDate);

}
