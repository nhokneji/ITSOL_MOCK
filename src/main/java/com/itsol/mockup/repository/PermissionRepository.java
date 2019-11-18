package com.itsol.mockup.repository;

import com.itsol.mockup.entity.PermissionEntity;
import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PermissionEntity,Long> {
    PermissionEntity getPermissionEntityByPermissionId(Long id);
    // List<PermissionEntity> getPermissionEntityByUser_UserName(String userName);
    List<PermissionEntity> getPermissionEntityByStatus(Integer status);
    Page<PermissionEntity> findPermissionEntitiesByStatus(Integer status, Pageable pageable);
    Page<PermissionEntity> getPermissionEntityByUser_UserName(String userName, Pageable pageable);

}