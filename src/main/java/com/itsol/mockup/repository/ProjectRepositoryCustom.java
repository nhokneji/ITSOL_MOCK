package com.itsol.mockup.repository;

import com.itsol.mockup.web.dto.request.ReportDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

import java.util.List;

public interface ProjectRepositoryCustom {
List<ReportDTO> findProjectNameAndTeamLead(Long projectId);
ReportDTO getTotalMember(Long projectId);
}