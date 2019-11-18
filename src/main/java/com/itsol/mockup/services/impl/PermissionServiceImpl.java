package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.PermissionEntity;
import com.itsol.mockup.repository.PermissionRepository;
import com.itsol.mockup.repository.UsersRepository;
import com.itsol.mockup.services.PermissionService;
import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.permisson.PermissionDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl extends BaseService implements PermissionService {


    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UsersRepository usersRepository;



    @Override
    public BaseResultDTO findAllPermission(BaseDTO request) {
        logger.info("=== START FIND ALL PERMISSION::");
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO<>();
        List<PermissionDTO> listPermission = new ArrayList<>();
        try{
            Page<PermissionEntity> datas = permissionRepository.findAll(PageRequest.of(request.getPage(), request.getPageSize()));
            if (datas != null) {
                if(datas.getContent().size() > 0){
                    for (PermissionEntity data: datas){
                        PermissionDTO permissionDTO = modelMapper.map(data,PermissionDTO.class);
                        listPermission.add(permissionDTO);
                    }
                }
                arrayResultDTO.setSuccess(listPermission,datas.getTotalElements(),datas.getTotalPages());
                logger.info("FIND ALL PERMISSION WITH RESULT"+ arrayResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            arrayResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);

        }
        return arrayResultDTO;
    }

    @Override
    public BaseResultDTO addPermission(PermissionDTO permissionDTO) {
        logger.info("START ADD NEW PERMISSION");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            PermissionEntity permissionEntity = modelMapper.map(permissionDTO, PermissionEntity.class);
//            permissionDTO.setUser();
            permissionEntity = permissionRepository.save(permissionEntity);
            if(permissionEntity.getPermissionId() != null) {
                singleResultDTO.setSuccess(permissionEntity);
            }
            logger.info("ADD NEW PERMISSION RESPONSE:: "+singleResultDTO.getErrorCode());
        }catch (Exception ex) {
            singleResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO updatePermission(PermissionDTO permissionDTO) {
        logger.info("START UPDATE PERMISSION");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            PermissionEntity permissionEntity = permissionRepository.getPermissionEntityByPermissionId(permissionDTO.getPermissionId());
            if(permissionEntity.getPermissionId() != null){
                /*permissionEntity.setAbsenceDate(permissionDTO.getAbsenceDate());
                permissionEntity.setReason(permissionDTO.getReason());
                permissionEntity.setStatus(permissionDTO.getStatus());*/
                permissionEntity = modelMapper.map(permissionDTO, PermissionEntity.class);
                permissionEntity = permissionRepository.save(permissionEntity);
                singleResultDTO.setSuccess(permissionEntity);
            }
            logger.info("UPDATE PERMISSION RESPONSE:: "+singleResultDTO.getErrorCode());
        }catch (Exception ex) {
            singleResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO deletePermission(Long id) {
        logger.info("START DELTE PERMISSION");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            if (id != null){
                permissionRepository.deleteById(id);
                singleResultDTO.setSuccess();
            }
            logger.info("DELETE PERMISSION RESPONSE:: "+singleResultDTO.getErrorCode());
        }catch (Exception ex){
            singleResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage());
        }
        return singleResultDTO;
    }


    @Override
    public BaseResultDTO findPermissionByUserName(String userName,Integer page, Integer pageSize) {
        logger.info("START FIND PERMISSION BY USERNAME::");
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO();
        List<PermissionDTO> list = new ArrayList<>();
        try {
            Page<PermissionEntity> data = permissionRepository.getPermissionEntityByUser_UserName(userName, PageRequest.of(page -1, pageSize));
            if (data != null) {
                if (data.getContent().size() >0) {
                    for (PermissionEntity permissionEntity : data.getContent()) {
                        PermissionDTO permissionDTO = modelMapper.map(permissionEntity, PermissionDTO.class);
                        list.add(permissionDTO);
                    }
                }
                arrayResultDTO.setSuccess(list,data.getTotalElements(),data.getTotalPages());
                logger.info("FIND ALL PERMISSION BY STATUS WITH RESULT"+ arrayResultDTO.getErrorCode());
            }
        }catch (Exception e){
            arrayResultDTO.setFail("FAIL");
            logger.error(e.getMessage());
        }

        return arrayResultDTO;
    }


    @Override
    public BaseResultDTO findAllPermissionByStatus(Integer status,Integer page,Integer pageSize) {
        logger.info("START FIND ALL PERMISSION BY STATUS::");
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO();
        List<PermissionDTO> list = new ArrayList<>();
        try {
            Page<PermissionEntity> data = permissionRepository.findPermissionEntitiesByStatus(status, PageRequest.of(page -1, pageSize));
            if (data != null) {
                for (PermissionEntity permissionEntity : data.getContent()) {
                    PermissionDTO permissionDTO = modelMapper.map(permissionEntity, PermissionDTO.class);
                    list.add(permissionDTO);
                }
                arrayResultDTO.setSuccess(list, data.getTotalElements(), data.getTotalPages());
                logger.info("FIND ALL PERMISSION BY STATUS RESPONSE::" + arrayResultDTO.getErrorCode());
            }
        }catch (Exception e){
            arrayResultDTO.setFail("FAIL");
            logger.error(e.getMessage());
        }
        return arrayResultDTO;
    }

    @Override
    public BaseResultDTO searchPermission(SearchUsersRequestDTO searchUsersRequestDTO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO();
        List<PermissionEntity> list;
        if (searchUsersRequestDTO.getUserName() != null) {
            Page<PermissionEntity> data = permissionRepository.getPermissionEntityByUser_UserName(searchUsersRequestDTO.getUserName(), PageRequest.of(1 - 1, 20));
            list = data.getContent();
        } else {
            list = permissionRepository.findAll();
        }
        List result = new ArrayList();
        for (PermissionEntity permissionEntity: list) {
            PermissionDTO permissionDTO = modelMapper.map(permissionEntity, PermissionDTO.class);
            String fullName = searchUsersRequestDTO.getFullName().trim().toLowerCase()
                    .replace("\\", "\\\\")
                    .replaceAll("%", "\\%")
                    .replaceAll("_", "\\_");
            if (searchUsersRequestDTO.getFullName() != "" &&
                    searchUsersRequestDTO.getFromDate() == null &&
                    searchUsersRequestDTO.getToDate() == null){
                if (permissionDTO.getUser().getFullName().contains(fullName)) {
                    result.add(permissionDTO);
                }
            }else if (searchUsersRequestDTO.getFromDate() != null &&
                    searchUsersRequestDTO.getToDate() == null &&
                    searchUsersRequestDTO.getFullName() != ""
            ){
                String fDate = simpleDateFormat.format(permissionDTO.getAbsenceDate());
                String sDate = simpleDateFormat.format(searchUsersRequestDTO.getFromDate());
                if (fDate.equals(sDate) && permissionDTO.getUser().getFullName().contains(searchUsersRequestDTO.getFullName()))
                {
                    result.add(permissionDTO);
                }
            }else if (searchUsersRequestDTO.getFromDate() != null
                    && searchUsersRequestDTO.getToDate() != null
                    && searchUsersRequestDTO.getFullName() != null | searchUsersRequestDTO.getUserName() != ""){
                if ((permissionDTO.getUser().getFullName().contains(fullName)) &&
                        (permissionDTO.getAbsenceDate().after(searchUsersRequestDTO.getFromDate()) &&
                                permissionDTO.getAbsenceDate().before(searchUsersRequestDTO.getToDate()))
                ) {
                    result.add(permissionDTO);
                }
            }
            else if (searchUsersRequestDTO.getFromDate() != null
                    && searchUsersRequestDTO.getToDate() != null
                    && searchUsersRequestDTO.getUserName() == ""){
                if ((permissionDTO.getAbsenceDate().after(searchUsersRequestDTO.getFromDate()) &&
                        permissionDTO.getAbsenceDate().before(searchUsersRequestDTO.getToDate()))
                ) {
                    result.add(permissionDTO);
                }
            }

            else if (searchUsersRequestDTO.getFromDate() != null
                    && searchUsersRequestDTO.getToDate() == null
                    && searchUsersRequestDTO.getUserName() == null) {
                String fDate = simpleDateFormat.format(permissionDTO.getAbsenceDate());
                String sDate = simpleDateFormat.format(searchUsersRequestDTO.getFromDate());
                if (fDate.equals(sDate))
                {
                    result.add(permissionDTO);
                }
            }
        }

        arrayResultDTO.setSuccess(result);
        return arrayResultDTO;
    }


}
