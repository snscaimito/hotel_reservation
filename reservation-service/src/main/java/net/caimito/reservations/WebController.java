package net.caimito.reservations;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reservation")
public class WebController {

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Reservation reserve() {
		return new Reservation(LocalDate.of(2016, 10, 15), LocalDate.of(2016, 10, 17)) ;
	}
}
