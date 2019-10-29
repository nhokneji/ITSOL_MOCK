package com.itsol.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "permission")
@Getter
@Setter
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "permission_seq")
    @SequenceGenerator(name = "permission_seq", sequenceName = "permission_seq",allocationSize = 1)
    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "absence_date")
    private Date absenceDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private Integer status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UsersEntity user;
}