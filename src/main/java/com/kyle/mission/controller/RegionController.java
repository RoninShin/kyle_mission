package com.kyle.mission.controller;

import java.util.List;

import com.kyle.mission.common.restapi.GenericController;
import com.kyle.mission.model.RegionEntity;
import com.kyle.mission.service.RegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = {"4. 컨트롤러 추상화 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController extends GenericController<RegionEntity, Long> {
    
    @Autowired
    private RegionService service;
    
    @PostMapping("/search")
    public List<RegionEntity> selectByName(@RequestBody String intentName) {
        log.debug("RegionController.selectByCategoryAndName - {}",intentName);
        return service.selectByCategoryAndName(intentName);
    }
}