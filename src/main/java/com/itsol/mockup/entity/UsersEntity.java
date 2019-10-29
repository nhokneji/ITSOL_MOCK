package com.itsol.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UsersEntity {
    @Id
    @Column(name = "USERS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    private Long userId;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String passWord;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "SKYPE")
    private String skypeName;

    @Column(name = "FACEBOOK")
    private String facebookUrl;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EDUCATION")
    private String education;

    @Column(name = "UNIVERSITY")
    private String university;

    @Column(name = "FACULTY")
    private String faculty;

    @Column(name = "GRADUATION_DATE")
    private Date graduationDate;

    @Column(name = "LAST_LOGIN")
    private Date lastLogin;

    @Column(name = "ACTIVE")
    private Integer active;

    @Column(name = "REASON")
    private String reason;

    @Column(name = "IMAGE_ID")
    private Long imageId;

    @Column(name = "LEVEL_ID")
    private Long levelId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
                joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles = new ArrayList<>();


    @JsonIgnore
    @ManyToMany(mappedBy = "usersEntities")
    private List<TeamEntity> teamEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity")
    private List<CommentsEntity> commentsEntities = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<NewEntity> news = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TimeSheetEntity> timeSheets = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PermissionEntity> permissions = new ArrayList<>();






}
