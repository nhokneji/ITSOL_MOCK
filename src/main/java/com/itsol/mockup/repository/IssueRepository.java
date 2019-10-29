package com.itsol.mockup.repository;

import com.itsol.mockup.entity.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<IssueEntity,Long> {
    IssueEntity getIssueEntityByIssueId(Long id);
}
