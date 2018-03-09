/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 */

package com.appdynamics.extensions.logstash.ssl;

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * AuthSSLX509TrustManager can be used to extend the default {@link X509TrustManager}
 * with additional trust decisions.
 * </p>
 *
 * @author <a href="mailto:oleg@ural.ru">Oleg Kalnichevski</a>
 *
 * <p>
 * DISCLAIMER: HttpClient developers DO NOT actively support this component.
 * The component is provided as a reference material, which may be inappropriate
 * for use without additional customization.
 * </p>
 */

public class AuthSSLX509TrustManager implements X509TrustManager
{
	private X509TrustManager defaultTrustManager = null;

    private static final Logger logger = Logger.getLogger(AuthSSLX509TrustManager.class.getName());


	/**
	 * Constructor for AuthSSLX509TrustManager.
	 */
	public AuthSSLX509TrustManager(final X509TrustManager defaultTrustManager) {
		super();
		if (defaultTrustManager == null) {
			throw new IllegalArgumentException("Trust manager may not be null");
		}
		this.defaultTrustManager = defaultTrustManager;
	}

	/**
	 * @see javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[],String authType)
	 */
	public void checkClientTrusted(X509Certificate[] certificates,String authType) throws CertificateException {

        log(" Client certificate - ", certificates);
		defaultTrustManager.checkClientTrusted(certificates,authType);
	}

	/**
	 * @see javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[],String authType)
	 */
	public void checkServerTrusted(X509Certificate[] certificates,String authType) throws CertificateException {

        log(" Server certificate - ", certificates);
		defaultTrustManager.checkServerTrusted(certificates,authType);
	}

	/**
	 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
	 */
	public X509Certificate[] getAcceptedIssuers() {
		return this.defaultTrustManager.getAcceptedIssuers();
	}

    private void log(String type, X509Certificate[] certificates)
    {
        if (!logger.isLoggable(Level.FINE) || certificates == null)
        {
            return;
        }
        for (int c = 0; c < certificates.length; c++)
        {
            X509Certificate certificate = certificates[c];
            logger.log(Level.FINE, type + (c + 1) + ":");
            logger.log(Level.FINE, toString(certificate));
        }
    }

    private String toString(X509Certificate certificate)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("  Subject DN: ").append(certificate.getSubjectDN());
        sb.append("  Signature Algorithm: ").append(certificate.getSigAlgName());
        sb.append("  Valid from: " ).append(certificate.getNotBefore());
        sb.append("  Valid until: " ).append(certificate.getNotAfter());
        sb.append("  Issuer: " ).append(certificate.getIssuerDN());
        return sb.toString();
    }
}