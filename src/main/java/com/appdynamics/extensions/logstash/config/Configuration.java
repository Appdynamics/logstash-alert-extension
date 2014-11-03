package com.appdynamics.extensions.logstash.config;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Configuration {

	private String host;
	private int port;
	private boolean showEvaluationDetails;
	private SSL ssl;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isShowEvaluationDetails() {
		return showEvaluationDetails;
	}

	public void setShowEvaluationDetails(boolean showEvaluationDetails) {
		this.showEvaluationDetails = showEvaluationDetails;
	}

	public SSL getSsl() {
		return ssl;
	}

	public void setSsl(SSL ssl) {
		this.ssl = ssl;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
