package com.kyle.mission.repository;

import java.util.List;
 
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.kyle.mission.model.QRegionEntity;
import com.kyle.mission.model.RegionEntity;

public class RegionEntityCustomRepositoryImpl extends QuerydslRepositorySupport implements RegionEntityCustomRepository{
 
	public RegionEntityCustomRepositoryImpl() {
        super(RegionEntity.class);
    }
 
    @Override
    public List<RegionEntity> selectByCategoryAndName(String category, String name) {
        
        QRegionEntity region = QRegionEntity.regionEntity;
        
        return from(region).where(region.regionName.contains(name)).fetch();
    }
 
}