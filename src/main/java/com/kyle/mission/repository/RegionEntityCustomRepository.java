package com.kyle.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.kyle.mission.model.RegionEntity;

public interface RegionEntityCustomRepository {
	List<RegionEntity> selectByCategoryAndName(String category, String name);
}