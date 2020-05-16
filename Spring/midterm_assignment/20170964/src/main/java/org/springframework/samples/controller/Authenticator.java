package org.springframework.samples.controller;

public interface Authenticator {

	void authenticate(String id, String password);

}
