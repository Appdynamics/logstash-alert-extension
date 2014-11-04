package com.appdynamics.extensions.logstash.ssl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLSocket;

import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.appdynamics.extensions.PathResolver;
import com.appdynamics.extensions.logstash.LogstashAlertExtension;
import com.appdynamics.extensions.logstash.config.SSL;

public class SocketFactory {
	
	private static final Logger LOGGER = Logger.getLogger(SocketFactory.class);

	public static Socket createSocket(SSL ssl, String host, int port) throws Exception {
		
		Socket socket = null;
		
		if (ssl != null && ssl.isEnable()) {
			
			if (ssl.isAllowSelfSignedCert()) {
				EasySSLProtocolSocketFactory sslProtocolSocketFactory = new EasySSLProtocolSocketFactory();
				socket = sslProtocolSocketFactory.createSocket(host, port);
				
			} else {
				AuthSSLProtocolSocketFactory sslProtocolSocketFactory = new AuthSSLProtocolSocketFactory(
						getUrl(ssl.getKeystorePath()), ssl.getKeystorePassword(), 
						getUrl(ssl.getTruststorePath()), ssl.getTruststorePassword());
				
				socket = sslProtocolSocketFactory.createSocket(host, port);
			}
			
			// fix for SSL Error: SSLv2Hello is disabled, when jdk6 initiates SSL connection with jdk7
			SSLSocket sslSocket = (SSLSocket) socket;
			
			List<String> enabledProtocols = new ArrayList<String>();
			for (String protocol : sslSocket.getEnabledProtocols()) {
				if (!protocol.equalsIgnoreCase("SSLv2Hello")) {
					enabledProtocols.add(protocol);
				}
			}
			
			sslSocket.setEnabledProtocols(enabledProtocols.toArray(new String[enabledProtocols.size()]));
			
		} else {
			socket =  new Socket(host, port);
		}

		return socket;
	}
	
    private static URL getUrl(String filename) throws MalformedURLException {
    	
    	URL url = null;
    	
        if(StringUtils.isBlank(filename)){
        	url = new URL("");
        	
        } else {
        	
        	File file = new File(filename);
        	
        	if(file.exists()){
        		//for absolute path
        		url = file.toURI().toURL();
        		
        	} else {
        		//for relative paths
        		File jarPath = PathResolver.resolveDirectory(LogstashAlertExtension.class);
        		url = new URL(String.format("%s%s%s", jarPath.toURI().toURL(), File.separator, filename));
        	}
        }
        
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("Filepath url: " + url.toString());
        }
        
        return url;
    }

}
