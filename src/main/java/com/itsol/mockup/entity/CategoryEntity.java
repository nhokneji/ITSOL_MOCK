package com.itsol.mockup.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Setter
@Getter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq",allocationSize = 1)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_code")
    private String code;

}