package com.itsol.mockup.services;


import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.team.TeamDTO;

public interface TeamServices {
    BaseResultDTO addTeam(String token, TeamDTO teamDTO);
    BaseResultDTO updateTeam(String token, TeamDTO teamDTO);
    BaseResultDTO findById(Long id);
    BaseResultDTO searchAllTeam(Integer pageSize, Integer page);
    BaseResultDTO deleteTeam(Long id);

//    BaseResultDTO searchUserInTeam(Long id);
}
