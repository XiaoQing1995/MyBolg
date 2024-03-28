package com.xiaoqing.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.authentication.AuthenticationRequest;
import com.xiaoqing.blog.model.authentication.AuthenticationResponse;
import com.xiaoqing.blog.model.authentication.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService service;
	
//	@PostMapping("/register")
//	public ResponseEntity<AuthenticationResponse> register(@RequestBody Account account) {
//		if (service.register(account)) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return new ResponseEntity<> (service.authenticate(request),HttpStatus.OK);
	}
}