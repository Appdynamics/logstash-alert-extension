/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 */

package com.appdynamics.extensions.logstash.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AlertOtherEventDetails extends AlertDetails {
	
	private static final String OTHER_EVENT_ALERT_TYPE = "Other Event";

    @JsonProperty("Event Notification Name")
    private String eventNotificationName;

    @JsonProperty("Event Notification ID")
    private String eventNotificationId;

    @JsonProperty("Event Notification Interval In Mins")
    private String eventNotificationIntervalInMins;
    
    @JsonProperty("Event Notification Time")
    private String eventNotificationTime;
    
    @JsonProperty("Event Types")
    private List<AlertEventType> eventTypes = new ArrayList<AlertEventType>();

    @JsonProperty("Event Summaries")
    private List<AlertEventSummary> eventSummaries = new ArrayList<AlertEventSummary>();

    public String getEventNotificationName() {
        return eventNotificationName;
    }

    public void setEventNotificationName(String eventNotificationName) {
        this.eventNotificationName = eventNotificationName;
    }

    public String getEventNotificationId() {
        return eventNotificationId;
    }

    public void setEventNotificationId(String eventNotificationId) {
        this.eventNotificationId = eventNotificationId;
    }

    public String getEventNotificationIntervalInMins() {
        return eventNotificationIntervalInMins;
    }

    public void setEventNotificationIntervalInMins(String eventNotificationIntervalInMins) {
        this.eventNotificationIntervalInMins = eventNotificationIntervalInMins;
    }

    public List<AlertEventType> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<AlertEventType> eventTypes) {
        this.eventTypes = eventTypes;
    }

    public List<AlertEventSummary> getEventSummaries() {
        return eventSummaries;
    }

    public void setEventSummaries(List<AlertEventSummary> eventSummaries) {
        this.eventSummaries = eventSummaries;
    }
    
	public String getEventNotificationTime() {
		return eventNotificationTime;
	}

	public void setEventNotificationTime(String eventNotificationTime) {
		this.eventNotificationTime = eventNotificationTime;
	}

	@Override
	public String getAlertType() {
		return OTHER_EVENT_ALERT_TYPE;
	}
}
