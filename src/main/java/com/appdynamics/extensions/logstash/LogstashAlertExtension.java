/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 */

package com.appdynamics.extensions.logstash;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.appdynamics.extensions.alerts.customevents.Event;
import com.appdynamics.extensions.alerts.customevents.EventBuilder;
import com.appdynamics.extensions.logstash.config.ConfigUtil;
import com.appdynamics.extensions.logstash.config.Configuration;
import com.appdynamics.extensions.logstash.json.Alert;
import com.appdynamics.extensions.logstash.json.AlertBuilder;
import com.appdynamics.extensions.logstash.ssl.SocketFactory;

/**
 * Sends Appdynamics Alert to Logstash using TCP connection
 * 
 * @author Florencio Sarmiento
 *
 */
public class LogstashAlertExtension {
	
	private static final Logger LOGGER = Logger.getLogger(LogstashAlertExtension.class);
	
    public static final String CONFIG_FILENAME =  "." + File.separator + "conf" + File.separator + "config.yaml";

    private final EventBuilder eventBuilder = new EventBuilder();
    private final AlertBuilder alertBuilder = new AlertBuilder();
    
    private final static ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();
    private Configuration config;
    
    public static void main(String[] args){
        if(args == null || args.length == 0){
        	LOGGER.error("No arguments passed to the extension, exiting the program.");
            return;
        }
        
        Configuration config = null;
        
        try {
            config = configUtil.readConfig(CONFIG_FILENAME, Configuration.class);
            
            if (LOGGER.isDebugEnabled()) {
            	LOGGER.debug("Config details: " + config);
            }
            
            LogstashAlertExtension alertExtension = new LogstashAlertExtension(config);
            alertExtension.processAnEvent(args);
            LOGGER.info("Logstash Extension completed successfully.");
            return;

        } catch (FileNotFoundException e) {
        	LOGGER.error("Config file not found.", e);
        	
        } catch(Exception e){
        	LOGGER.error("Error processing an event", e);
        }
        
        LOGGER.error("Logstash Extension completed with errors");
    }
    
    public void processAnEvent(String[] args) throws Exception {
    	Event event = eventBuilder.build(args);
		Alert alert = alertBuilder.buildAlert(event, config.isShowEvaluationDetails());
		String message = alertBuilder.convertIntoJsonString(alert);
		sendAlertMessage(message);
    }
    
    private boolean sendAlertMessage(String message) throws Exception {
    	LOGGER.info("Sending alert message...");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Message content: " + message);
		}
    	
    	boolean success = false;
    	
    	Socket clientSocket = null;
    	OutputStream outputStream = null;
    	DataOutputStream outToServer = null;
    	
    	try {
			clientSocket = SocketFactory.createSocket(config.getSsl(), config.getHost(), config.getPort());
			outputStream = clientSocket.getOutputStream();  
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes(message);
			success = true;
			
		} finally {
			closeOutputStream(outToServer);
			closeOutputStream(outputStream);
			closeSocket(clientSocket);
    	}
    	
    	return success;
    }
    
    private void closeOutputStream(OutputStream outputStream) {
    	if (outputStream != null) {
    		try {
    			outputStream.close();
    		} catch(Exception e) {}
    	}
    }
    
    private void closeSocket(Socket socket) {
    	if (socket != null) {
			try {
				socket.close();
			} catch(Exception e) {}
		}
    }

    public LogstashAlertExtension(Configuration config) {
		this.config = config;
		LOGGER.info("LogstashAlertExtension Version ["+getImplementationTitle()+"]");
	}
    
    private String getImplementationTitle(){
        return this.getClass().getPackage().getImplementationTitle();
    }

}
