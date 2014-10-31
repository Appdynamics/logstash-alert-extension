package com.appdynamics.extensions.logstash.config;

public class Configuration {

	private String host;
	private int port;
	private boolean showEvaluationDetails;

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

}
