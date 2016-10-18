package net.caimito.hotel;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ServiceResponse<T> {

	public static final int OK = 200;
	public static final int NOT_FOUND = 404;
	
	private int _status;
	private ServiceResponseBody<T> _body = new ServiceResponseBody<>();

	public int status() {
		return _status ;
	}

	public ServiceResponseBody<T> body() {
		return _body ;
	}

	public void status(int status) {
		_status = status ;
	}

	public void body(ServiceResponseBody<T> body) {
		_body = body ;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}
}
