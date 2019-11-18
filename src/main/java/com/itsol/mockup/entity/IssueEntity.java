package com.itsol.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "issue")
@Getter
@Setter
public class IssueEntity {
    @Id
    @Column(name = "issue_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_seq")
    @SequenceGenerator(name = "issue_seq", sequenceName = "issue_seq", allocationSize = 1)
    private Long issueId;

    @Column(name = "issue_name")
    private String issueName;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name="CONTENT")
    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "PROJECT_ID")
    private ProjectEntity project;
}