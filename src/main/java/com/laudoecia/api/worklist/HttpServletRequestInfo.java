
package com.laudoecia.api.worklist;

import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestInfo {
	public final String requesterHost;
	public final String requestURI;
	public final String queryString;
	public final String localHost;

	private HttpServletRequestInfo(HttpServletRequest request) {

		requesterHost = request.getRemoteHost();
		requestURI = request.getRequestURI();
		queryString = request.getQueryString();
		localHost = hostOfURI(requestURI);
	}

	private HttpServletRequestInfo(String requesterUserID, String requesterHost, String requestURI) {
		this.requesterHost = requesterHost;
		this.requestURI = requestURI;
		this.queryString = null;
		this.localHost = hostOfURI(requestURI);
	}

	public static HttpServletRequestInfo valueOf(HttpServletRequest request) {
		return new HttpServletRequestInfo(request);
	}

	public static HttpServletRequestInfo valueOf(Message msg) {
		try {
			return msg.propertyExists("RequestURI")
					? new HttpServletRequestInfo(msg.getStringProperty("RequesterUserID"),
							msg.getStringProperty("RequesterHostName"), msg.getStringProperty("RequestURI"))
					: null;
		} catch (JMSException e) {
			throw new JMSRuntimeException(e.getMessage(), e.getErrorCode(), e.getCause());
		}
	}

	public void copyTo(Message msg) {
		try {
			msg.setStringProperty("RequesterHostName", requesterHost);
			msg.setStringProperty("RequestURI", requestURI);
		} catch (JMSException e) {
			throw new JMSRuntimeException(e.getMessage(), e.getErrorCode(), e.getCause());
		}
	}

	public static void copyTo(HttpServletRequestInfo requestInfo, Message msg) {
		if (requestInfo != null)
			requestInfo.copyTo(msg);
	}

	private static String hostOfURI(String requestURI) {
		try {
			int beginIndex = requestURI.indexOf(':') + 1;
			while (requestURI.charAt(++beginIndex) == '/')
				;
			int endIndex = beginIndex;
			char ch;
			while ((ch = requestURI.charAt(endIndex)) != '/' && ch != ':')
				endIndex++;
			return requestURI.substring(beginIndex, endIndex);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
}