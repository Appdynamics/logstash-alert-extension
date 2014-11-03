package com.appdynamics.extensions.logstash.ssl;

import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLSocket;

import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;

import com.appdynamics.extensions.logstash.config.SSL;

public class SocketFactory {

	public static Socket createSocket(SSL ssl, String host, int port) throws Exception {
		
		Socket socket = null;
		
		if (ssl != null && ssl.isEnable()) {
			
			if (ssl.isAllowSelfSignedCert()) {
				EasySSLProtocolSocketFactory sslProtocolSocketFactory = new EasySSLProtocolSocketFactory();
				socket = sslProtocolSocketFactory.createSocket(host, port);
				
			} else {
				AuthSSLProtocolSocketFactory sslProtocolSocketFactory = new AuthSSLProtocolSocketFactory(
						new URL("file://" + ssl.getKeystorePath()), ssl.getKeystorePassword(), 
						new URL("file://" +ssl.getTruststorePath()), ssl.getTruststorePassword());
				
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

}
