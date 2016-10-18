package net.caimito.hotel;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ServiceResponseBody<T> {

	private String _uri = "" ;
	private T _content = null ;

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

	public void content(T content) {
		_content = content ;
	}
	
	public T content() {
		return _content ;
	}

}
