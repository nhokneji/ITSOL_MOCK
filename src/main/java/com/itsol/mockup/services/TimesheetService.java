package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.timesheet.TimesheetDTO;

public interface TimesheetService {
    BaseResultDTO findAll(Integer pageSize, Integer page);
    BaseResultDTO addTimesheet(TimesheetDTO timesheetDTO, String token);
    BaseResultDTO updateTimesheet(TimesheetDTO timesheetDTO);
    BaseResultDTO deleteTimesheet(Long id);
    BaseResultDTO searchTimesheetByuser(String token, Integer pageSize, Integer page);
    BaseResultDTO updateStatusTimeSheet(Long id, Integer status);
}
