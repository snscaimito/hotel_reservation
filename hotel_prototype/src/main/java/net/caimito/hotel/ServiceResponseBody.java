package net.caimito.hotel;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ServiceResponseBody<T> {

	private String _uri;

	public String uri() {
		return _uri ;
	}

	public void uri(String uri) {
		_uri = uri ;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}

}
