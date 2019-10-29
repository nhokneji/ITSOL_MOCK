package com.itsol.mockup.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "levels")
@Getter
@Setter
public class LevelsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "levels_seq")
    @SequenceGenerator(name = "levels_seq", sequenceName = "levels_seq",allocationSize = 1)
    @Column(name = "level_id")
    private Long levelId;

    @Column(name = "level_name")
    private String name;
}