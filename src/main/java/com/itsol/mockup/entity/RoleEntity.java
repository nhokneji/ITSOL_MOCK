package com.itsol.mockup.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
public class RoleEntity {
    @Id
    @Column(name="ROLE_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq",allocationSize = 1)
    private Long roleId;

    @Column(name = "ROLE_NAME")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<UsersEntity> usersEntities = new ArrayList<>();
}