package com.sap.hcp.fieldglass.jobposting;

import java.rmi.ServerException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

import javax.naming.ConfigurationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to submit job postings to Fieldglass service by making
 * properly configured SOAP requests.
 * 
 * @author i330092
 *
 */
public class JobPostingSOAPClient {

	private static final String DATA_SOAP_ELEMENT = "data";

	private static final String PASSWORD_SOAP_ELEMENT = "password";

	private static final String USERNAME_SOAP_ELEMENT = "username";

	private static final String UPLOAD_SOAP_ELEMENT = "upload";

	private static final String SOAP_NAMESPACE_NAME = "web";

	private static final String WEB_NAMESPACE_URL_DESTINATION_PROPERTY = "WebNamespaceURL";

	private static final String PASSWORD_DESTINATION_PROPERTY = "Password";

	private static final String USER_DESTINATION_PROPERTY = "User";

	private static final String INVALID_PROTOCOL_ERROR_MESSAGE = "Invalid argument as protocol for SSLContext!";

	private static final String SSL_CONTEXT_PROTOCOL = "SSL";

	private static final String DESTINATION_NOT_FOUND_ERROR_MESSAGE = "Destination [ %s ] not found. Hint: Make sure to have the destination configured.";

	private static final String GET_DESTINATION_PROPERTIES_DEBUG_MESSAGE = "Getting destination properties for destination [ {} ]";

	private static final String CLOSING_SOAP_CONNECTION_FAIL_MESSAGE = "Could not close SOAP connection.";

	private static final String SOAP_CONNECTION_FAIL_MESSAGE = "Could not establish SOAP connection.";

	private static final String FAILED_TO_GET_THE_DESTINATION_PROPERTIES_ERROR_MESSAGE = "Failed to get the destination properties! Make sure to have the destination configured.";

	private static final String COULD_NOT_LOOKUP_CONNECTIVITY_CONFIGURATION_ERROR_MESSAGE = "Could not lookup Connectivity Configuration [ {} ] ";

	private static final String NULL_CONENCTIVITY_CONFIGURATION_ERROR_MESSAGE = "Looking up Conenctivity Configuration returned null.";

	private static final String CONNECTIVITY_CONFIGURATION = "java:comp/env/connectivityConfiguration";

	private static final String DESTINATION_NAME = "fieldglass-jobposting-api";

	private static final Logger LOGGER = LoggerFactory.getLogger(JobPostingSOAPClient.class);

	private static ConnectivityConfiguration connectivityConfiguration;

	private static Map<String, String> destinationProperties;

	/**
	 * Looks up the connectivity configuration and fetches the destination
	 * properties.
	 */
	static {
		try {
			Context ctx = new InitialContext();
			connectivityConfiguration = (ConnectivityConfiguration) ctx.lookup(CONNECTIVITY_CONFIGURATION);
		} catch (NamingException e) {
			LOGGER.error(COULD_NOT_LOOKUP_CONNECTIVITY_CONFIGURATION_ERROR_MESSAGE, CONNECTIVITY_CONFIGURATION, e);
			throw new ExceptionInInitializerError(COULD_NOT_LOOKUP_CONNECTIVITY_CONFIGURATION_ERROR_MESSAGE);
		}

		if (connectivityConfiguration == null) {
			LOGGER.error(NULL_CONENCTIVITY_CONFIGURATION_ERROR_MESSAGE);
			throw new ExceptionInInitializerError(NULL_CONENCTIVITY_CONFIGURATION_ERROR_MESSAGE);
		}

		try {
			destinationProperties = getDestinationProperties(DESTINATION_NAME);
		} catch (ConfigurationException e) {
			LOGGER.error(FAILED_TO_GET_THE_DESTINATION_PROPERTIES_ERROR_MESSAGE, e);
			throw new ExceptionInInitializerError(FAILED_TO_GET_THE_DESTINATION_PROPERTIES_ERROR_MESSAGE);
		}

		if (Boolean.parseBoolean(destinationProperties.get("TrustAll"))) {
			// Setting HttpsURLConnection to trust all certificates. This is for
			// sample purposes only.
			setupSSL();
		}
	}

	/**
	 * Submits the JobPosting to the Fieldglass service
	 * 
	 * @param jobPosting
	 *            The job posting to be published
	 * @return The response returned from the Fieldglass service
	 * @throws ServerException
	 */
	public static SOAPMessage submitToSOAPServer(JobPosting jobPosting) throws ServerException {
		logDebug("Invoked submitToSOAPServer");

		SOAPConnection connection = null;
		SOAPMessage result;
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			connection = soapConnectionFactory.createConnection();

			// Create request and send it
			SOAPMessage soapRequest = createRequest(jobPosting);
			SOAPMessage soapResponse = connection.call(soapRequest, destinationProperties.get("URL"));

			result = soapResponse;
		} catch (SOAPException e) {
			LOGGER.error(SOAP_CONNECTION_FAIL_MESSAGE, e);
			throw new ServerException(SOAP_CONNECTION_FAIL_MESSAGE);
		} finally {
			try {
				connection.close();
			} catch (SOAPException e) {
				LOGGER.error(CLOSING_SOAP_CONNECTION_FAIL_MESSAGE, e);
			}
		}

		return result;
	}

	/**
	 * Creates a properly configured SOAPMessage, ready to be send to the
	 * Fieldglass service
	 * 
	 * @param jobPosting
	 *            The job posting to be published
	 * @return The created SOAPMessage
	 * @throws SOAPException
	 * @throws ServerException
	 */
	private static SOAPMessage createRequest(JobPosting jobPosting) throws SOAPException, ServerException {

		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage request = factory.createMessage();
		SOAPPart soapPart = request.getSOAPPart();

		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(SOAP_NAMESPACE_NAME,
				destinationProperties.get(WEB_NAMESPACE_URL_DESTINATION_PROPERTY));

		SOAPBody soapBody = envelope.getBody();
		SOAPElement upload = soapBody.addChildElement(UPLOAD_SOAP_ELEMENT, SOAP_NAMESPACE_NAME);
		SOAPElement username = upload.addChildElement(USERNAME_SOAP_ELEMENT, SOAP_NAMESPACE_NAME);
		SOAPElement password = upload.addChildElement(PASSWORD_SOAP_ELEMENT, SOAP_NAMESPACE_NAME);
		SOAPElement data = upload.addChildElement(DATA_SOAP_ELEMENT, SOAP_NAMESPACE_NAME);

		username.addTextNode(destinationProperties.get(USER_DESTINATION_PROPERTY));
		password.addTextNode(destinationProperties.get(PASSWORD_DESTINATION_PROPERTY));

		data.addTextNode(jobPosting.toString());

		request.saveChanges();
		return request;
	}

	/**
	 * Sets the default SSLSocketFactory for HttpsURLConnection class to trust
	 * all certificates
	 */
	private static void setupSSL() {
		try {
			TrustManager[] trustAllCerts = createTrustAllCertsManager();
			SSLContext sslContext = SSLContext.getInstance(SSL_CONTEXT_PROTOCOL);
			SecureRandom random = new java.security.SecureRandom();
			sslContext.init(null, trustAllCerts, random);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(INVALID_PROTOCOL_ERROR_MESSAGE, e);
			throw new ExceptionInInitializerError(e);
		} catch (GeneralSecurityException e) {
			throw new ExceptionInInitializerError(e);
		}

	}

	/**
	 * Creates a TrustManager that trusts all certificates
	 * 
	 * @return The created TrustManager
	 */
	private static TrustManager[] createTrustAllCertsManager() {
		TrustManager[] result = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };
		return result;
	}

	/**
	 * Creates a map containing the destination properties for the given
	 * destination
	 * 
	 * @param destinationName
	 *            Name of the destination
	 * @return The map containing the destination properties
	 * @throws ConfigurationException
	 *             If the destination is not found
	 */
	private static Map<String, String> getDestinationProperties(String destinationName) throws ConfigurationException {
		DestinationConfiguration destConfiguration = connectivityConfiguration.getConfiguration(destinationName);
		if (destConfiguration == null) {
			throw new ConfigurationException(String.format(DESTINATION_NOT_FOUND_ERROR_MESSAGE, destinationName));
		}
		logDebug(GET_DESTINATION_PROPERTIES_DEBUG_MESSAGE, destinationName);
		return destConfiguration.getAllProperties();
	}

	/**
	 * Logs the message with the specified arguments if debugging is enabled for
	 * the logger
	 */
	private static void logDebug(String message, Object... arguments) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(message, arguments);
		}
	}
}
