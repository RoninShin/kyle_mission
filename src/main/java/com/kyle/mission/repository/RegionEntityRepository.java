package com.kyle.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyle.mission.model.RegionEntity;

@Repository
public interface RegionEntityRepository extends JpaRepository<RegionEntity, Long>, RegionEntityCustomRepository {
 
	
}