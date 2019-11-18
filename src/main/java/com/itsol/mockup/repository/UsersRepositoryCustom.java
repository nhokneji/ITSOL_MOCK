package com.itsol.mockup.repository;

import com.itsol.mockup.web.dto.request.IdRequestDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.springframework.data.domain.Page;

/**
 * @author anhvd_itsol
 */
public interface UsersRepositoryCustom {
    Page<UsersDTO> findUsersByUsernameAndEmailAndRoles(SearchUsersRequestDTO requestDTO);
    Page<UsersDTO> findUserNotRequest(IdRequestDTO request);

}
