package com.itsol.mockup.web.dto.project;

import com.itsol.mockup.entity.IssueEntity;
import com.itsol.mockup.entity.TeamEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProjectDTO {

    private Long projectId;

    private String projectName;

    private Date createdDate;

    private String createdBy;

    private Date deadline;

    private Date startDate;

    private String description;

    private List<TeamEntity> teams;

    private List<IssueEntity> issues = new ArrayList<>();

}
