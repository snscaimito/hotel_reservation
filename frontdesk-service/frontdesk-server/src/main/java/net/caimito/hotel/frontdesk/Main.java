package net.caimito.hotel.frontdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "net.caimito.hotel.frontdesk", "net.caimito.hotel.inventory" } )
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args) ;
	}

}
