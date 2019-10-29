package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.TeamEntity;
import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.repository.TeamRepository;
import com.itsol.mockup.repository.UsersRepository;
import com.itsol.mockup.services.TeamServices;
import com.itsol.mockup.utils.TokenUtils;
import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import com.itsol.mockup.web.dto.team.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamServiceImpl extends BaseService implements TeamServices {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UsersRepository usersRepository;


//    @Override
//    public BaseResultDTO addTeam(TeamDTO teamDTO) {
//        logger.info("=== START ADD NEW TEAM");
//        BaseResultDTO baseResultDTO = new BaseResultDTO();
//        try {
//            TeamEntity team = modelMapper.map(teamDTO, TeamEntity.class);
//            List<UsersEntity> lstUser = team.getUsersEntities();
//            team = teamRepository.save(team);
//            if (team.getTeamId() !=null){
//                baseResultDTO.setSuccess();
//                logger.info("=== ADD NEW TEAM RESPONSE TEAM_ID= "+ team.getTeamId());
//            }
//        }catch (Exception ex){
//            baseResultDTO.setFail(ex.getMessage());
//            logger.error(ex.getMessage(), ex);
//        }
//        return baseResultDTO;
//    }

    @Override
    public BaseResultDTO addTeam(String token, TeamDTO teamDTO) {
        logger.info("=== START ADD NEW TEAM");
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        try {
            List<UsersEntity> lstUser = teamDTO.getUsersEntities();
            UsersEntity usersEntity = usersRepository.findUsersEntityByUserName(tokenUtils.getUsernameFromToken(token));
            if (usersEntity != null) {
                teamDTO.setCreatedBy(usersEntity.getFullName());
                if (lstUser.size() > 0) {
                    teamDTO.setQuantity(lstUser.size());
                    teamDTO.setCreatedDate(getCurTimestamp());
                }
                TeamEntity team = modelMapper.map(teamDTO, TeamEntity.class);

                team = teamRepository.save(team);
                if (team.getTeamId() != null) {
                    baseResultDTO.setSuccess();
                    logger.info("=== ADD NEW TEAM RESPONSE TEAM_ID= " + team.getTeamId());
                }
            }
        } catch (Exception ex) {
            baseResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return baseResultDTO;
    }

    @Override
    public BaseResultDTO updateTeam(String token, TeamDTO teamDTO) {
        logger.info("==== StART UPDATE TEAM");
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        try {
            TeamEntity teamEntity = teamRepository.findTeamEntityByTeamId(teamDTO.getTeamId());
            List<UsersEntity> lstUser = teamDTO.getUsersEntities();
            if (teamEntity != null) {
                UsersEntity usersEntity = usersRepository.findUsersEntityByUserName(tokenUtils.getUsernameFromToken(token));
                teamDTO.setModifiedBy(usersEntity.getFullName());
                teamDTO.setModifiedDate(getCurTimestamp());
                teamDTO.setQuantity(lstUser.size());
                teamDTO.setModifiedDate(getCurTimestamp());
                teamEntity = modelMapper.map(teamDTO, TeamEntity.class);
                teamRepository.save(teamEntity);
                baseResultDTO.setSuccess();
            }
            logger.info("=== UPDATE TEAM RESPONSE: " + baseResultDTO.getErrorCode());
        } catch (Exception e) {
            baseResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return baseResultDTO;
    }

    @Override
    public BaseResultDTO findById(Long id) {
        logger.info("=== search team by team_id start");
        SingleResultDTO<TeamEntity> singleResultDTO = new SingleResultDTO<>();
        try {
            TeamEntity team = teamRepository.findTeamEntityByTeamId(id);

            if (team.getTeamId() != null) {
                singleResultDTO.setSuccess(team);
            } else {
                singleResultDTO.setItemNotfound();
            }
            logger.info("=== search team:" + singleResultDTO.getErrorCode());
        } catch (Exception e) {
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO searchAllTeam(Integer pageSize, Integer page) {
        logger.info("START SEARCH ALL TEAM");
        ArrayResultDTO<TeamDTO> arrayResultDTO = new ArrayResultDTO<>();
        List<TeamDTO> teamDTOList = new ArrayList<>();
        try {
            Page<TeamEntity> rawDatas = teamRepository.findAll(PageRequest.of(page - 1, pageSize));
            if (rawDatas != null) {
                if (rawDatas.getContent().size() > 0) {
                    rawDatas.getContent().forEach(team -> {
                        TeamDTO teamDTO = modelMapper.map(team, TeamDTO.class);
                        teamDTOList.add(teamDTO);
                    });
                }
                arrayResultDTO.setSuccess(teamDTOList, rawDatas.getTotalElements(), rawDatas.getTotalPages());
                logger.info("===== SEARCH ALL TEAM RESPONE:" + arrayResultDTO.getErrorCode());
            }
        } catch (Exception e) {
            arrayResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return arrayResultDTO;
    }

    @Override
    public BaseResultDTO deleteTeam(Long id) {
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        try {
            TeamEntity teamEntity = teamRepository.findTeamEntityByTeamId(id);
            if (teamEntity != null){
                teamRepository.delete(teamEntity);
                baseResultDTO.setSuccess();
            }
        }catch (Exception e){
            logger.info("delete Team ERR" + e.getMessage(), e);
            baseResultDTO.setFail(e.getMessage());
        }
        return baseResultDTO;
    }

}
