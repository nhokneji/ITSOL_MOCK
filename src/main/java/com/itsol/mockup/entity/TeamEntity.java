package com.itsol.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TEAM")
@Getter
@Setter
public class TeamEntity {
    @Id
    @Column(name = "TEAM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
    @SequenceGenerator(name = "team_seq", sequenceName = "team_seq", allocationSize = 1)
    private Long teamId;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @Column(name = "QUANTITY")
    private Integer quantity;


    @Column(name = "CREATED_BY")
    private String createdBy;


    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;


    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="PROJECT_ID")
    private ProjectEntity projectEntity;

//    @Column(name = "PROJECT_ID")
//    private Long projectId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TEAM_USERS",
            joinColumns = @JoinColumn(name = "TEAM_ID"),
            inverseJoinColumns = @JoinColumn(name = "USERS_ID")
    )
    private List<UsersEntity> usersEntities = new ArrayList<>();




}
