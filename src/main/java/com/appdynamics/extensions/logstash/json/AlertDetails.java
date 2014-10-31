package com.appdynamics.extensions.logstash.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AlertDetails {

    @JsonProperty("Application Id")
    private String applicationId;

    @JsonProperty("Application Name")
    private String applicationName;

    @JsonProperty("Severity")
    private String severity;

    @JsonProperty("Priority")
    private String priority;
    
    @JsonProperty("Tag")
    private String tag;
    
    @JsonProperty("Alert Type")
    public abstract String getAlertType();

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
