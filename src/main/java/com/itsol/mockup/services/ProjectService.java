package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.project.ProjectDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface ProjectService {
//    @PreAuthorize("hasAnyRole('TEAMLEAD,MANAGER,HR')")
    BaseResultDTO findAll(Integer pageSize, Integer page);
    BaseResultDTO addProject(String token, ProjectDTO projectDTO);
    BaseResultDTO updateProject(ProjectDTO projectDTO);
    BaseResultDTO deleteProject(Long id);
    BaseResultDTO findProjectById(Long id);

//    @PreAuthorize("hasAnyRole('HR,MANAGER')")
    BaseResultDTO findProjectAndTeamLeadByProjectId(Long projectId);

//    @PreAuthorize("hasAnyRole('HR,MANAGER')")
    BaseResultDTO getTotalMember(Long projectId);
    BaseResultDTO getTotalTimesheet(SearchUsersRequestDTO searchUsersRequestDTO);
}
