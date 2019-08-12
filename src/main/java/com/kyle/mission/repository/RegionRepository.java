package com.kyle.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.kyle.mission.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Boolean existsByName(String name);
    Optional<Region> findByName(String name);
    List<Region> findByNameLike(String name);
}