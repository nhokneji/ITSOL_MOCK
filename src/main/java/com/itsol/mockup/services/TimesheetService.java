package com.itsol.mockup.services;

import com.itsol.mockup.entity.TimeSheetEntity;
import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.timesheet.TimesheetDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;

public interface TimesheetService {
    BaseResultDTO findAll(Integer pageSize, Integer page);
    BaseResultDTO addTimesheet(TimesheetDTO timesheetDTO, String token);
    BaseResultDTO updateTimesheet(TimesheetDTO timesheetDTO);
    BaseResultDTO deleteTimesheet(Long id);
    BaseResultDTO searchTimesheetByuser(UsersDTO usersDTO, Integer pageSize, Integer page);
}
