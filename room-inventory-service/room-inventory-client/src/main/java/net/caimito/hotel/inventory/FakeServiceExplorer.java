package net.caimito.hotel.inventory;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class FakeServiceExplorer implements ServiceExplorer {

	@Override
	public URL findService(String serviceName) {
		try {
			return new URL("http://localhost:50001");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e) ;
		}
	}

}
