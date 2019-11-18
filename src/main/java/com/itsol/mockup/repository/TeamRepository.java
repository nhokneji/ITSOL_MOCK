package com.itsol.mockup.repository;

import com.itsol.mockup.entity.TeamEntity;
import com.itsol.mockup.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    TeamEntity findTeamEntityByTeamId(Long id);
}
