package com.kyle.mission.controller;

import javax.validation.Valid;

import com.kyle.mission.message.request.ProgramForm;
import com.kyle.mission.message.request.RegionForm;
import com.kyle.mission.model.Program;
import com.kyle.mission.model.Region;
import com.kyle.mission.repository.ProgramRepository;
import com.kyle.mission.repository.RegionRepository;
import com.kyle.mission.security.services.ProgramService;

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

    @Autowired
    ProgramRepository programRepository;

	@Autowired
    ProgramService programService;

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
		return ">>> Admin Ping Success";
	}

	@PostMapping("/api/admin/insertRegion")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> insertRegion(@Valid @RequestBody RegionForm regionRequest) {
		if (regionRepository.existsByName(regionRequest.getName()))
			return ResponseEntity.ok().body("region already exists"); 

		Region region = new Region(regionRequest.getName());
		regionRepository.save(region);

        return ResponseEntity.ok().body("region registered successfully!");
	}

	@PostMapping("/api/admin/insertProgram")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> insertProgram(@Valid @RequestBody ProgramForm programRequest) {
		if (programRepository.existsByName(programRequest.getName()))
			return ResponseEntity.ok().body("program already exists"); 

		Program program = programService.save(programRequest);
		if (program == null || program.getId() == 0)
			return ResponseEntity.ok().body("program regist failed!"); 

		return ResponseEntity.ok().body("program registered successfully!");
	}

	@PostMapping("/api/admin/updateProgram")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateProgram(@Valid @RequestBody ProgramForm programRequest) {
		if (programRequest.getId()<=0)
			return ResponseEntity.ok().body("program dont update"); 

		Program program = programService.save(programRequest);
		if (program == null || program.getId() == 0)
			return ResponseEntity.ok().body("program update failed!"); 

		return ResponseEntity.ok().body("program updated successfully!");
	}
}