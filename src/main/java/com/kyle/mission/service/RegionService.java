package com.kyle.mission.service;

import java.util.List;

import com.kyle.mission.model.RegionEntity;
import com.kyle.mission.repository.RegionEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegionService {
    
    @Autowired
    private RegionEntityRepository regionEntityRepository;
    
    public List<RegionEntity> selectByCategoryAndName(String name) {
        log.debug("RegionService.selectByCategoryAndName - {}",name);
        return regionEntityRepository.selectByCategoryAndName("test", name);
    }
    
    
}