package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.request.IdRequestDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.request.auth.AuthRequestDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.auth.AuthResponseDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;

/**
 * @author anhvd_itsol
 */

public interface UsersService {

    BaseResultDTO findAllUsers(Integer pageSize, Integer page);
    BaseResultDTO findUsersByUsernameAndEmailAndRoles(SearchUsersRequestDTO requestDTO);
    BaseResultDTO addUser(UsersDTO usersDTO);
    BaseResultDTO updateUser(UsersDTO usersDTO);
    BaseResultDTO findUserEntityByUserName(String token);

    BaseResultDTO findAllUsersNotListId(IdRequestDTO requestDTO);
    BaseResultDTO updateActiceUser(String userName);
    BaseResultDTO updateImageID(String userName);
    BaseResultDTO deleteUser(Long id);

    BaseResultDTO findUserbyId(Long id);
//    @PreAuthorize("hasAuthority('MANAGER')")
    BaseResultDTO findAll();
    BaseResultDTO updateRoleUser(Long id, Long roleId);
//    BaseResultDTO findUserByUserName(String userName);


    // ====== START SERVICES FOR AUTHENTICATION ======
    AuthResponseDTO generateToken(AuthRequestDTO userForAuthentication);

    // ====== END ======
}
