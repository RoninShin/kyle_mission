package com.kyle.mission;

import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.kyle.mission.message.response.KeywordResponse;
import com.kyle.mission.model.Program;
import com.kyle.mission.model.Region;
import com.kyle.mission.repository.ProgramRepository;
import com.kyle.mission.repository.RegionRepository;

import org.hamcrest.core.IsNot;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest {
    
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Test
    public void 서비스_지역_컬럼에서_특정_지역에서_진행되는_프로그램명과_테마를_출력() {
        //Given:

        //When:
        String region_name = "%평창군%";
        List<Region> regionList = regionRepository.findByNameLike(region_name);

        //Then:
        assertThat(regionList.size(), greaterThan(0));
        assertThat(regionList.get(0).getPrograms().size(), greaterThan(0));
    }

    @Test
    public void 프로그램_소개_컬럼에서_특정_문자열이_포함된_레코드에서_서비스_지역_개수를_세어_출력() {
        //Given:

        //When:
        String keyword = "세계문화유산";
        List<Object[]> regionList = programRepository.findRegionByProgramDesc(keyword);

        List<KeywordResponse> keywordResponseList = new ArrayList();
		for( Object[] row : regionList) {
			KeywordResponse item = new KeywordResponse();
			item.setRegion((String) row[0]);
			item.setCount((BigInteger) row[1]);
            keywordResponseList.add(item);
        }

        //Then:
        assertThat(keywordResponseList.size(), greaterThan(0));
    }

    @Test
    public void 프로그램_상세_정보를_읽어와서_입력_단어의_출현빈도수를_계산하여_출력() {
        //Given:

        //When:
        String keyword = "세계문화유산";
        BigInteger cnt = programRepository.countProgramByProgramDetail(keyword);

        //Then:
        assertThat(cnt.intValue(), greaterThan(0));
    }

    @Test
    public void 지역명과_관광키워드를_입력받아_프로그램_코드를_출력() {
        //Given:

        //When:
        String region = "남해";
        String keyword = "생태체험";
        List<Object[]> program = programRepository.findProgramByRegionAndKeyword(region, keyword);

        //Then:
        assertThat(program, is(notNullValue()));
    }
}