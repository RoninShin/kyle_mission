package com.kyle.mission.repository;

import java.util.List;

import com.kyle.mission.model.RegionEntity;

public interface RegionEntityCustomRepository {
	List<RegionEntity> selectByCategoryAndName(String category, String name);
}