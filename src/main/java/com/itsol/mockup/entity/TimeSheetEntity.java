package com.itsol.mockup.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TIMESHEET")
@Getter
@Setter
public class TimeSheetEntity {

    @Id
    @Column(name="TIMESHEET_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "timesheet_seq")
    @SequenceGenerator(name = "timesheet_seq", sequenceName = "timesheet_seq",allocationSize = 1)
    private Long timesheetId;

    @Column(name = "TASK")
    private String task;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @Column(name = "RESULT")
    private String result;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "PROJECT_ID")
    private Long projectId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UsersEntity usersEntity;
}