package com.itsol.mockup.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PROJECT")
@Getter
@Setter
public class ProjectEntity {
    @Id
    @Column(name = "PROJECT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
    private Long projectId;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "DEADLINE")
    private Date deadline;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy="projectEntity", fetch = FetchType.LAZY)
    private List<TeamEntity> teams;

}
