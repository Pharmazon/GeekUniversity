package com.flamexander.spring.jwt.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@GetMapping("/demo")
	public String demoPage() {
		return "demo";
	}

	@GetMapping("/admin")
	public String securedPage() {
		return "admin";
	}
}