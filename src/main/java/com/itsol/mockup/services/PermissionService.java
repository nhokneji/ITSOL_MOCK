package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.permisson.PermissionDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface PermissionService {
    BaseResultDTO findAllPermission(BaseDTO request);
    BaseResultDTO addPermission(PermissionDTO permissionDTO);
    BaseResultDTO updatePermission(PermissionDTO permissionDTO);
    BaseResultDTO deletePermission(Long id);


}
