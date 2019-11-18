package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.permisson.PermissionDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface PermissionService {
    BaseResultDTO findAllPermission(BaseDTO request);
    BaseResultDTO addPermission(PermissionDTO permissionDTO);
    BaseResultDTO updatePermission(PermissionDTO permissionDTO);
    BaseResultDTO deletePermission(Long id);
    BaseResultDTO findPermissionByUserName(String userName,Integer page, Integer pageSize);

//    @PreAuthorize("hasRole('ROLE_MANAGER')")
    BaseResultDTO findAllPermissionByStatus(Integer status,Integer page,Integer pageSize);

    BaseResultDTO searchPermission(SearchUsersRequestDTO searchUsersRequestDTO);

}
