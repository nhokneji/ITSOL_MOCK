package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.TimeSheetEntity;
import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.repository.TimesheetRepository;
import com.itsol.mockup.repository.UsersRepository;
import com.itsol.mockup.services.TimesheetService;
import com.itsol.mockup.utils.TokenUtils;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import com.itsol.mockup.web.dto.timesheet.TimesheetDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimesheetServiceImpl extends BaseService implements TimesheetService {
    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UsersRepository usersRepository;


    @Override
    public BaseResultDTO findAll(Integer pageSize, Integer page) {
        logger.info("=== START FIND ALL TEAMSHEET::");
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO<>();
        try {
            List<TimesheetDTO> lstResult = new ArrayList<>();
            Page<TimeSheetEntity> rawsDatas = timesheetRepository.findAll(PageRequest.of(page - 1, pageSize));
            if (rawsDatas != null) {
                if (rawsDatas.getContent().size() > 0) {
                    rawsDatas.getContent().forEach(i -> {
                        TimesheetDTO dto = modelMapper.map(i, TimesheetDTO.class);
                        lstResult.add(dto);
                    });
                }
                arrayResultDTO.setSuccess(lstResult,rawsDatas.getTotalElements(),rawsDatas.getTotalPages());
                logger.info("=== FIND ALL TEAMSHEET WITH RESPONSE: "+arrayResultDTO.getErrorCode());
            }

        }catch (Exception e){
            logger.error("TEAMSHEET Exception" + e.getMessage(),e);
            arrayResultDTO.setFail(e.getMessage());
        }
        return arrayResultDTO;
    }

    @Override
    public BaseResultDTO addTimesheet(TimesheetDTO timesheetDTO, String token) {
        logger.info("ADD NEW TIMESHEET");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        UsersEntity usersEntity = usersRepository.findUsersEntityByUserName(tokenUtils.getUsernameFromToken(token));
        try {
            timesheetDTO.setCreatedDate(getCurTimestamp());
            timesheetDTO.setStatus(0);
            TimeSheetEntity timeSheetEntity = modelMapper.map(timesheetDTO, TimeSheetEntity.class);
            timeSheetEntity.setUser(usersEntity);
            timeSheetEntity = timesheetRepository.save(timeSheetEntity);
            singleResultDTO.setSuccess(timeSheetEntity);

        } catch (Exception e) {
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO updateTimesheet(TimesheetDTO timesheetDTO) {
        logger.info("UPDATE TIMESHEET");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            TimeSheetEntity timeSheetEntity = timesheetRepository.getTimeSheetEntityByTimesheetId(timesheetDTO.getTimesheetId());
            if (timeSheetEntity.getTimesheetId() != null) {
                timeSheetEntity = modelMapper.map(timesheetDTO, TimeSheetEntity.class);
                timesheetRepository.save(timeSheetEntity);
                singleResultDTO.setSuccess(timeSheetEntity);
            }
            logger.info("UPDATE TIMESHEET RESPONSE: " + singleResultDTO.getErrorCode());
        } catch (Exception e) {
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO deleteTimesheet(Long id) {
        logger.info("DELETE TIMESHEET");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            if (id != null) {
                timesheetRepository.deleteById(id);
                singleResultDTO.setSuccess();
            }
        } catch (Exception e) {
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO searchTimesheetByuser(UsersDTO usersDTO, Integer pageSize, Integer page) {
        ArrayResultDTO<TimesheetDTO> arrayResultDTO = new ArrayResultDTO<>();
        /*List<TimesheetDTO> list = new ArrayList<>();
        try {
            TimeSheetEntity timeSheetEntity = modelMapper.map(usersDTO, TimeSheetEntity.class);
            Page<TimeSheetEntity> rawDatas = (Page<TimeSheetEntity>) timesheetRepository.findTimeSheetEntitiesByUser(timeSheetEntity, PageRequest.of(page, pageSize));
        if (rawDatas != null){
            rawDatas.forEach(timeSheet -> {
                TimesheetDTO timesheetDTO = modelMapper.map(timeSheet, TimesheetDTO.class);
                list.add(timesheetDTO);
            });
            arrayResultDTO.setSuccess(list, rawDatas.getTotalElements(), rawDatas.getTotalPages());
            logger.info("=== FIND TIMESHEET BY ID USER RESPONSE::" + arrayResultDTO.getErrorCode());
        }

        }catch (Exception e){
            logger.error("ERR searchTimesheetByuser" +e.getMessage(), e);
            arrayResultDTO.setFail(e.getMessage());
        }*/
        return arrayResultDTO;
    }
}
