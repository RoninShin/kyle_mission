package com.kyle.mission.controller;

import java.util.List;

import com.kyle.mission.common.restapi.GenericController;
import com.kyle.mission.model.Program;
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
@Api(tags = {"5. Program 컨트롤러 추상화 API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/program")
public class ProgramController extends GenericController<Program, Long> {
    

}