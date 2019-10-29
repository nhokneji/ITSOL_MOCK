package com.itsol.mockup.web.dto.team;

import com.itsol.mockup.entity.ProjectEntity;
import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.web.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TeamDTO {
    private Long teamId;
    private String teamName;
    private Integer quantity;
    private String createdBy;
    private Date createdDate;
    private Long projectId;
    private Date modifiedDate;
    private String modifiedBy;
    private ProjectEntity projectEntity;
    private List<UsersEntity> usersEntities;

}
