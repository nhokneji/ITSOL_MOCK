package com.itsol.mockup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "comments_seq",allocationSize = 1)
    @Column(name = "comments_id")
    private Long commentsId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "issue_id")
    private Long issueId;

//    @Column(name = "users_id")
//    private Long usersId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private UsersEntity userEntity;

}