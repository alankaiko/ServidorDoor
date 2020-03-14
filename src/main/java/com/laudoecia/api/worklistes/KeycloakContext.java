
package com.laudoecia.api.worklistes;

import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;

public class KeycloakContext {

	private final HttpServletRequest request;
	private final KeycloakSecurityContext ksc;

	public static KeycloakContext valueOf(HttpServletRequest request) {
		return new KeycloakContext(request);
	}

	private KeycloakContext(HttpServletRequest req) {
		request = req;
		ksc = (KeycloakSecurityContext) request.getAttribute("org.keycloak.KeycloakSecurityContext");
	}

	public String getUserName() {
		return ksc != null ? ksc.getToken().getPreferredUsername() : request.getRemoteAddr();
	}

	public String getToken() {
		return ksc != null ? ksc.getTokenString() : null;
	}

	public int getExpiration() {
		return ksc != null ? ksc.getToken().getExpiration() : 0;
	}

	public Set<String> getUserRoles() {
		return ksc != null ? ksc.getToken().getRealmAccess().getRoles() : Collections.emptySet();
	}
}
