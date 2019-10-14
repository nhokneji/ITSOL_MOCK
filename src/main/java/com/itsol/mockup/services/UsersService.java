package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.ResultDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;

/**
 * @author anhvd_itsol
 */

public interface UsersService {
    ResultDTO findAllUsers(SearchUsersRequestDTO requestDTO);
    ResultDTO findUsersByFullNameAndUserName(SearchUsersRequestDTO requestDTO);
    ResultDTO addUser(UsersDTO usersDTO);
    ResultDTO updateUser(UsersDTO usersDTO);
}
