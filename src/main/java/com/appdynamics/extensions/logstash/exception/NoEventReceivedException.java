package com.appdynamics.extensions.logstash.exception;

public class NoEventReceivedException extends RuntimeException {

	private static final long serialVersionUID = -7102951061076582886L;

	public NoEventReceivedException() {}

	public NoEventReceivedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoEventReceivedException(String message) {
		super(message);
	}

	public NoEventReceivedException(Throwable cause) {
		super(cause);
	}

}
