package com.appdynamics.extensions.logstash.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Alert {

    @JsonProperty("AppDynamics Alert")
    private AlertDetails details;

    public AlertDetails getDetails() {
        return details;
    }

    public void setDetails(AlertDetails details) {
        this.details = details;
    }
    
}
