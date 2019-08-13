package com.kyle.mission.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.kyle.mission.message.request.KeywordForm;
import com.kyle.mission.message.request.KeywordRegionForm;
import com.kyle.mission.message.request.RegionForm;
import com.kyle.mission.message.response.KeywordResponse;
import com.kyle.mission.model.Region;
import com.kyle.mission.repository.ProgramRepository;
import com.kyle.mission.repository.RegionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = {"3. 생태 관광 정보 조회 API"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/touristInfo")
public class TouristInfoRestAPIs {

	@Autowired
    RegionRepository regionRepository;

    @Autowired
    ProgramRepository programRepository;

	@ApiOperation(value = "생태 관광정보 조회 By 서비스 지역", notes = "서비스 지역 컬럼에서 특정 지역에서 진행되는 프로그램명과 테마를 출력하는 API")
	@PostMapping("/v1/retrieveByRegion")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> retrieveByRegion(@Valid @RequestBody @ApiParam(value = "서비스 지역", required = true) RegionForm regionRequest) {
		List<Region> regionList = regionRepository.findByNameLike("%" + regionRequest.getRegion() + "%");

		return new ResponseEntity<List<Region>>(regionList, HttpStatus.OK);
	}

	@ApiOperation(value = "생태 관광정보 조회 By 프로그램 소개", notes = "프로그램 소개 컬럼에서 특정 문자열이 포함된 레코드에서 서비스 지역 개수를 세어 출력하는 API")
	@PostMapping("/v1/retrieveByKeyword")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> retrieveByKeyword(@Valid @RequestBody @ApiParam(value = "프로그램 소개", required = true) KeywordForm keywordRequest) {
		List<KeywordResponse> keywordResponseList = new ArrayList<KeywordResponse>();

		List<Object[]> regionList = programRepository.findRegionByProgramDesc(keywordRequest.getKeyword());
		for( Object[] row : regionList) {
			KeywordResponse item = new KeywordResponse();
			item.setRegion((String) row[0]);
			item.setCount((BigInteger) row[1]);
            keywordResponseList.add(item);
        }
		
		return new ResponseEntity<List<KeywordResponse>>(keywordResponseList, HttpStatus.OK);
	}

	@ApiOperation(value = "생태 관광정보 조회 By 프로그램 상세정보", notes = "프로그램 상세 정보를 읽어와서 입력 단어의 출현빈도수를 계산하여 출력 하는 API ")
	@PostMapping("/v1/frequency")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> frequency(@Valid @RequestBody @ApiParam(value = "입력 단어", required = true) KeywordForm keywordRequest) {

		KeywordResponse item = new KeywordResponse();
		item.setKeyword(keywordRequest.getKeyword());
		item.setCount(programRepository.countProgramByProgramDetail(keywordRequest.getKeyword()));

		return new ResponseEntity<KeywordResponse>(item, HttpStatus.OK);
	}

	@ApiOperation(value = "생태 관광정보 조회 By 추천", notes = "생태관광 정보를 기반으로 생태관광 프로그램을 추천해주려고 합니다. 지역명과 관광 키 워드를 입력받아 프로그램 코드를 출력하는 API")
	@PostMapping("/v1/recommend")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> recommend(@Valid @RequestBody @ApiParam(value = "지역명과 관광키워드", required = true) KeywordRegionForm keywordRegionRequest) {
		KeywordResponse item = new KeywordResponse();

		List<Object[]> programList = programRepository.findProgramByRegionAndKeyword(keywordRegionRequest.getRegion(), keywordRegionRequest.getKeyword());
		if (programList.size() > 0) {
			Object[] row = programList.get(0);
			item.setProgram((int) row[0]);
		}

		return new ResponseEntity<KeywordResponse>(item, HttpStatus.OK);
	}
}