package com.kyle.mission.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kyle.mission.message.response.KeywordResponse;
import com.kyle.mission.model.Program;
import com.kyle.mission.model.Region;
import com.kyle.mission.model.Role;
import com.kyle.mission.model.RoleName;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    boolean existsByName(String name);
    Optional<Program> findByName(String name);
    
    // 프로그램 소개 컬럼에서 특정 문자열이 포함된 레코드에서 서비스 지역 개수를 세어 출력
    @Query(value="select r.name, count(r.name) from regions r inner join programs p on p.region_id = r.id and p.prgm_desc like %:keyword% group by r.name", nativeQuery=true)
    List<Object[]> findRegionByProgramDesc(@Param("keyword") String keyword);

    // 프로그램 상세 정보를 읽어와서 입력 단어의 출현빈도수를 계산하여 출력
    @Query(value="select count(p.name) from programs p where p.prgm_desc like %:keyword%", nativeQuery=true)
    BigInteger countProgramByProgramDetail(@Param("keyword") String keyword);

    // 생태관광 정보를 기반으로 생태관광 프로그램을 추천해주려고 합니다. 지역명과 관광 키 워드를 입력받아 프로그램 코드를 출력하는 API
    // 가중치 테마:20, 소개:10, 상세:5 부여
    @Query(value="select p.id, IF(p.theme LIKE %:keyword%, 20,  0) + IF(p.prgm_desc         LIKE %:keyword%, 10,  0) + IF(p.prgm_detail         LIKE %:keyword%, 5,  0) AS cnt "
                +"  from regions r inner join programs p on p.region_id = r.id and r.name like %:region% order by cnt desc limit 1;", nativeQuery=true)
    List<Object[]> findProgramByRegionAndKeyword(@Param("region") String region, @Param("keyword") String keyword);

}