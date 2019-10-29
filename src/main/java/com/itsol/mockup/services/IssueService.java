package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.issue.IssueDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface IssueService {
    BaseResultDTO findAllIssue(BaseDTO request);
    BaseResultDTO addIssue(IssueDTO issueDTO);
    BaseResultDTO updateIssue(IssueDTO issueDTO);
    BaseResultDTO deleteIssue(Long id);

}
