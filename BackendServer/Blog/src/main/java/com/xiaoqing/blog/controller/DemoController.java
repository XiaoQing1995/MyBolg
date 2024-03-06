package com.xiaoqing.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<String> sayHello() {
		System.out.println("Current user roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return ResponseEntity.ok("Hello from secured endpoint");
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/hi")
	public ResponseEntity<String> sayHi() {
		return ResponseEntity.ok("Hi");
	}
	
}
