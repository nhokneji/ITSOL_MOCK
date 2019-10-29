package com.itsol.mockup.web.dto.timesheet;

import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.web.dto.users.UsersDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TimesheetDTO {
    private Long timesheetId;
    private String task;
    private Timestamp createdDate;
    private String result;
    private String note;
    private Integer status;
    private Long projectId;
    private UsersDTO user;

}
