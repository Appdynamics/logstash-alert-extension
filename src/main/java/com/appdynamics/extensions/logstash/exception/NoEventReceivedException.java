/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 */

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
