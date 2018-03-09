/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 */

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
