package com.kyle.mission.controller;

import javax.validation.Valid;

import com.kyle.mission.message.request.RegionForm;
import com.kyle.mission.model.Region;
import com.kyle.mission.repository.RegionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestAPIs {
	
    @Autowired
    RegionRepository regionRepository;

	// @GetMapping("/api/test/user")
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	// public String userAccess() {
	// 	return ">>> User Contents!";
	// }

	// @GetMapping("/api/test/pm")
	// @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	// public String projectManagementAccess() {
	// 	return ">>> Board Management Project";
	// }
	
	@GetMapping("/api/admin/ping")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}

	@PostMapping("/api/admin/insertRegion")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> insertRegion(@Valid @RequestBody RegionForm regionRequest) {
		Region region = new Region(regionRequest.getName());
		
		regionRepository.save(region);

        return ResponseEntity.ok().body("region registered successfully!");
	}

	@PostMapping("/api/admin/updateRegion")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateRegion() {
		return ">>> Admin Contents";
	}

	@PostMapping("/api/admin/insertProgram")
	@PreAuthorize("hasRole('ADMIN')")
	public String insertProgram() {
		return ">>> Admin Contents";
	}

	@PostMapping("/api/admin/updateProgram")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateProgram() {
		return ">>> Admin Contents";
	}
}