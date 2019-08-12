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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = {"2. 생태 관광 정보 관리 API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestAPIs {
	
    @Autowired
    RegionRepository regionRepository;

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
	
	@ApiOperation(value = "토큰 헬스체크", notes = "정상적으로 토큰이 생성되었는지 체크")
	@GetMapping("/v1/ping")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Ping Success";
	}

	@ApiOperation(value = "서비스 지역 등록", notes = "서비스 지역을 등록한다.")
	@PostMapping("/v1/insertRegion")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> insertRegion(@Valid @RequestBody @ApiParam(value = "지역정보", required = true) RegionForm regionRequest) {
		if (regionRepository.existsByName(regionRequest.getRegion()))
			return ResponseEntity.ok().body("region already exists"); 

		Region region = new Region(regionRequest.getRegion());
		regionRepository.save(region);

        return ResponseEntity.ok().body("region registered successfully!");
	}

	@ApiOperation(value = "프로그램 등록", notes = "프로그램을 등록한다.")
	@PostMapping("/v1/insertProgram")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> insertProgram(@Valid @RequestBody @ApiParam(value = "프로그램 정보", required = true) ProgramForm programRequest) {
		// if (programRepository.existsByName(programRequest.getName()))
		// 	return ResponseEntity.ok().body("program already exists"); 

		Program program = programService.save(programRequest);
		if (program == null || program.getId() == 0)
			return ResponseEntity.ok().body("program regist failed!"); 

		return ResponseEntity.ok().body("program registered successfully!");
	}

	@ApiOperation(value = "프로그램 수정", notes = "프로그램을 수정한다.")
	@PostMapping("/v1/updateProgram")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> updateProgram(@Valid @RequestBody @ApiParam(value = "프로그램 정보", required = true) ProgramForm programRequest) {
		if (programRequest.getId()<=0)
			return ResponseEntity.ok().body("program dont update"); 

		Program program = programService.save(programRequest);
		if (program == null || program.getId() == 0)
			return ResponseEntity.ok().body("program update failed!"); 

		return ResponseEntity.ok().body("program updated successfully!");
	}
}