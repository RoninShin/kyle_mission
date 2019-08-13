package com.kyle.mission;

import com.kyle.mission.message.request.ProgramForm;
import com.kyle.mission.model.Program;
import com.kyle.mission.model.Region;
import com.kyle.mission.repository.ProgramRepository;
import com.kyle.mission.repository.RegionRepository;
import com.kyle.mission.service.ProgramService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminRestApiTest {

    @Autowired
    ProgramService programService;

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    RegionRepository regionRepository;

    @Test
    public void 생태_관광_정보_등록_및_수정() {
        //Given:
        String region_name = "성남시";
        String prg_name = "서울수영대회";

        //When:
        Program prg;
        ProgramForm program1 = new ProgramForm ("성남수영대회", "수영", "수영을 배우자", "수영에 관한 기초연습", region_name);
        prg = programService.save(0, program1);

        program1.setName(prg_name);
        prg = programService.save(prg.getId(), program1);

        Program program2 = programRepository.findByName(prg_name).orElse(new Program());
        programRepository.delete(program2);

        Region regionInfo = regionRepository.findByName(region_name).orElse(new Region());
        regionRepository.delete(regionInfo);
        
        //Then:
        assertThat(prg.getName(), is(equalTo("서울수영대회")));
    }
}