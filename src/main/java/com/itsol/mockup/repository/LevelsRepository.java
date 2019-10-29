package com.itsol.mockup.repository;

import com.itsol.mockup.entity.LevelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelsRepository extends JpaRepository<LevelsEntity,Long> {
    LevelsEntity findLevelsEntityByLevelId(Long id);
}
