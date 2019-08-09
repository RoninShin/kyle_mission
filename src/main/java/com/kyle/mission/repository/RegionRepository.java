package com.kyle.mission.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyle.mission.model.Region;
import com.kyle.mission.model.Role;
import com.kyle.mission.model.RoleName;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}