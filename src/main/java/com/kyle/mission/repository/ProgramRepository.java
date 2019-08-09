package com.kyle.mission.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kyle.mission.model.Program;
import com.kyle.mission.model.Role;
import com.kyle.mission.model.RoleName;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    Optional<Role> findByName(RoleName roleName);
}