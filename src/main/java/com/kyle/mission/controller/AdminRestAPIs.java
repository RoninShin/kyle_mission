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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@ApiOperation(value = "토큰 헬스체크", notes = "정상적으로 토큰이 생성되었는지 체크")
	@GetMapping("/v1/ping")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Ping Success";
	}

	//#region : 서비스 지역 관리
	@ApiOperation(value = "서비스 지역 조회", notes = "서비스 지역을 조회한다.")
	@GetMapping("/v1/region/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> region_findById(@ApiParam(value = "지역ID", required = true) @PathVariable long id) {
		return regionRepository.findById(id)
          .map(record -> ResponseEntity.ok().body(record))
          .orElse(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "서비스 지역 등록", notes = "서비스 지역을 등록한다.")
	@PostMapping("/v1/region/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> region_create(@Valid @RequestBody @ApiParam(value = "지역정보", required = true) RegionForm regionRequest) {
		return regionRepository.findByName(regionRequest.getRegion())
			.map(record -> {
				return new ResponseEntity<Region>(record, HttpStatus.FOUND);
			}).orElseGet(() -> {
				return ResponseEntity.ok().body(regionRepository.save(new Region(regionRequest.getRegion())));
			});
	}

	@ApiOperation(value = "서비스 지역 수정", notes = "서비스 지역을 수정한다.")
	@PutMapping("/v1/region/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> region_update(@ApiParam(value = "지역ID", required = true) @PathVariable long id,
											@Valid @RequestBody @ApiParam(value = "지역정보", required = true) RegionForm regionRequest) {
		return regionRepository.findById(id)
				.map(record -> {
					record.setId(id);
					record.setName(regionRequest.getRegion());
					Region updated = regionRepository.save(record);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "서비스 지역 삭제", notes = "서비스 지역을 삭제한다.")
	@DeleteMapping("/v1/region/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> region_delete(@ApiParam(value = "지역ID", required = true) @PathVariable long id) {
		return regionRepository.findById(id)
				.map(record -> {
					regionRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
	//#endregion

	//#region : 생태 관광 정보 관리
	@ApiOperation(value = "생태 관광 정보 조회", notes = "생태 관광 정보를 조회한다.")
	@GetMapping("/v1/program/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> program_findById(@ApiParam(value = "프로그램ID", required = true) @PathVariable long id) {
		return programRepository.findById(id)
          .map(record -> ResponseEntity.ok().body(record))
          .orElse(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "생태 관광 정보 등록", notes = "생태 관광 정보를 등록한다.")
	@PostMapping("/v1/program/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> program_create(@Valid @RequestBody @ApiParam(value = "프로그램 정보", required = true) ProgramForm programRequest) {
		Program program = programService.save(0L, programRequest);
		if (program == null || program.getId() == 0)
			return new ResponseEntity<String>("program regist failed!", HttpStatus.METHOD_FAILURE);

		return ResponseEntity.ok().body(program);
	}

	@ApiOperation(value = "생태 관광 정보 수정", notes = "생태 관광 정보를 수정한다.")
	@PutMapping("/v1/program/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> program_update(@ApiParam(value = "프로그램ID", required = true) @PathVariable long id,
											@Valid @RequestBody @ApiParam(value = "프로그램 정보", required = true) ProgramForm programRequest) {
		Program program = programService.save(id, programRequest);
		if (program == null || program.getId() == 0)
			return new ResponseEntity<String>("program update failed!", HttpStatus.METHOD_FAILURE);

		return ResponseEntity.ok().body(program);
	}

	@ApiOperation(value = "생태 관광 정보 삭제", notes = "생태 관광 정보를 삭제한다.")
	@DeleteMapping("/v1/program/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> program_delete(@ApiParam(value = "프로그램ID", required = true) @PathVariable long id) {
		return programRepository.findById(id)
				.map(record -> {
					programRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}	
	////#endregion
}