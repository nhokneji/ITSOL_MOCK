package com.itsol.mockup.repository;

import com.itsol.mockup.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
    ProjectEntity getProjectEntityByProjectId(Long id);
}
