package com.kyle.mission.service;

import com.kyle.mission.message.request.ProgramForm;
import com.kyle.mission.model.Program;
import com.kyle.mission.model.Region;
import com.kyle.mission.repository.ProgramRepository;
import com.kyle.mission.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgramService {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    ProgramRepository programRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Program save(long id, ProgramForm programRequest) {
        Program program = new Program (programRequest.getName(), programRequest.getTheme(), programRequest.getPrgm_desc(), programRequest.getPrgm_detail());
        if (id>0)
            program.setId(id);

        try{		
            Region region = regionRepository.findByName(programRequest.getRegion()).orElseGet(() -> {
                return regionRepository.save(new Region(programRequest.getRegion()));
            });

            program.setRegion(region);
            programRepository.save(program);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return program;
    }
}