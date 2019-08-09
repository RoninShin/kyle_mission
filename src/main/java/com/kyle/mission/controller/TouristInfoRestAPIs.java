package com.kyle.mission.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TouristInfoRestAPIs {
	
	@PostMapping("/api/touristInfo/frequency")
	@PreAuthorize("hasRole('ADMIN')")
	public String frequency() {
		return ">>> Admin Contents";
	}

	@PostMapping("/api/touristInfo/retrieve")
	@PreAuthorize("hasRole('ADMIN')")
	public String retrieve() {
		return ">>> Admin Contents";
	}

	@PostMapping("/api/touristInfo/recommend")
	@PreAuthorize("hasRole('ADMIN')")
	public String recommend() {
		return ">>> Admin Contents";
	}
}