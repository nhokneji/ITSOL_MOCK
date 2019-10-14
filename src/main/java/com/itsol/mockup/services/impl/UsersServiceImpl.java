package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.Users;
import com.itsol.mockup.repository.UsersRepository;
import com.itsol.mockup.repository.UsersRepositoryCustom;
import com.itsol.mockup.services.UsersService;
import com.itsol.mockup.utils.Constants;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.ResultDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anhvd_itsol
 */

@Service
public class UsersServiceImpl implements UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersRepositoryCustom usersRepositoryCustom;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResultDTO findAllUsers(SearchUsersRequestDTO request) {
        logger.info("=== START FIND ALL USERS::");
        ResultDTO resultDTO = new ResultDTO();
        List<UsersDTO> lstUsers = new ArrayList<>();
        try {
            Page<Users> rawDatas = usersRepository.findAll(PageRequest.of(request.getPage(), request.getPageSize()));
            if (rawDatas != null) {
                if (rawDatas.getContent().size() > 0) {
                    rawDatas.getContent().forEach(d -> {
                        UsersDTO usersDTO = modelMapper.map(d, UsersDTO.class);
                        lstUsers.add(usersDTO);
                    });
                }
                resultDTO.setTotalRow(rawDatas.getTotalElements());
                resultDTO.setTotalPage(rawDatas.getTotalPages());
                resultDTO.setData(lstUsers);
                resultDTO.setErrorCode(Constants.ApiErrorCode.SUCCESS);
                resultDTO.setDescription(Constants.ApiErrorDesc.SUCCESS);
                logger.info("=== FIND ALL USERS RESPONSE::" + resultDTO.getErrorCode());
            }
        } catch (Exception ex) {
            resultDTO.setErrorCode(Constants.ApiErrorCode.ERROR);
            resultDTO.setDescription(Constants.ApiErrorDesc.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO findUsersByFullNameAndUserName(SearchUsersRequestDTO requestDTO) {
        logger.info("=== START FIND ALL USERS BY FULL_NAME AND USER_NAME::");
        ResultDTO resultDTO = new ResultDTO();
        try {
            Page<UsersDTO> rawDatas = usersRepositoryCustom.findUsersByFullNameAndUserName(requestDTO);
            resultDTO.setData(rawDatas.getContent());
            resultDTO.setTotalRow(rawDatas.getTotalElements());
            resultDTO.setTotalPage(rawDatas.getTotalPages());
            resultDTO.setErrorCode(Constants.ApiErrorCode.SUCCESS);
            resultDTO.setDescription(Constants.ApiErrorDesc.SUCCESS);
            logger.info("=== FIND ALL USERS BY FULL_NAME AND USER_NAME RESPONSE::" + resultDTO.getErrorCode());
        } catch (Exception ex) {
            resultDTO.setErrorCode(Constants.ApiErrorCode.ERROR);
            resultDTO.setDescription(Constants.ApiErrorDesc.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO addUser(UsersDTO requestDTO) {
        logger.info("=== START ADD NEW USER::");
        ResultDTO resultDTO = new ResultDTO();
        try {
            Users user = modelMapper.map(requestDTO, Users.class);
            user = usersRepository.save(user);
            if (user.getId() != null) {
                resultDTO.setData(user);
                resultDTO.setErrorCode(Constants.ApiErrorCode.SUCCESS);
                resultDTO.setDescription(Constants.ApiErrorDesc.SUCCESS);
            } else {
                resultDTO.setErrorCode(Constants.ApiErrorCode.ERROR);
                resultDTO.setDescription(Constants.ApiErrorDesc.ERROR);
            }
            logger.info("=== ADD NEW USER RESPONSE::" + resultDTO.getErrorCode());
        } catch (Exception ex) {
            resultDTO.setErrorCode(Constants.ApiErrorCode.ERROR);
            resultDTO.setDescription(Constants.ApiErrorDesc.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }

    @Override
    public ResultDTO updateUser(UsersDTO usersDTO) {
        logger.info("=== START UPDATE USER::" + usersDTO.getId());
        ResultDTO resultDTO = new ResultDTO();
        try {
            Users user = usersRepository.getUsersById(usersDTO.getId());
            if (user.getId() != null) {
                user.setUserName(usersDTO.getUserName());
                user.setFullName(usersDTO.getFullName());
                user.setPassWord(usersDTO.getPassWord());
                user.setRole(usersDTO.getRole());
                user = usersRepository.save(user);
                resultDTO.setData(user);
                resultDTO.setErrorCode(Constants.ApiErrorCode.SUCCESS);
                resultDTO.setDescription(Constants.ApiErrorDesc.SUCCESS);
            } else {
                resultDTO.setErrorCode(Constants.ApiErrorCode.ERROR);
                resultDTO.setDescription(Constants.ApiErrorDesc.ERROR);
            }
            logger.info("=== UPDATE USER RESPONSE::" + resultDTO.getErrorCode());
        } catch (Exception ex) {
            resultDTO.setErrorCode(Constants.ApiErrorCode.ERROR);
            resultDTO.setDescription(Constants.ApiErrorDesc.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return resultDTO;
    }


}
