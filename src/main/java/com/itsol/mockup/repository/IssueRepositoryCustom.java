package com.itsol.mockup.repository;

import com.itsol.mockup.entity.IssueEntity;
import com.itsol.mockup.web.dto.issue.IssueDTO;
import com.itsol.mockup.web.dto.request.IdRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IssueRepositoryCustom {
    Page<IssueDTO> findIssueByUserId(Integer pageSize, Integer page, Long id);
    List<IssueDTO> findByUserId(Long id);
}