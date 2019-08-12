package com.kyle.mission;

import com.kyle.mission.message.request.ProgramForm;
import com.kyle.mission.model.Program;
import com.kyle.mission.model.Region;
import com.kyle.mission.repository.ProgramRepository;
import com.kyle.mission.repository.RegionRepository;
import com.kyle.mission.security.services.ProgramService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

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
        GIVEN: {}
        String region_name = "성남시";
        String prg_name = "서울수영대회";

        Program prg;
        WHEN: {
            ProgramForm program1 = new ProgramForm ("성남수영대회", "수영", "수영을 배우자", "수영에 관한 기초연습", region_name);
            prg = programService.save(0, program1);

            program1.setName(prg_name);
            prg = programService.save(prg.getId(), program1);

            Program program2 = programRepository.findByName(prg_name).orElse(new Program());
            programRepository.delete(program2);
    
            Region regionInfo = regionRepository.findByName(region_name).orElse(new Region());
            regionRepository.delete(regionInfo);
        }

        THEN: {
            assertThat(prg.getName(), is(equalTo("서울수영대회")));
        }
    }
}