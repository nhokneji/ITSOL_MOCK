package com.itsol.mockup.web.dto.comment;

import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.web.dto.users.UsersDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDTO {
    private Long commentsId;
    private String content;
    private Date createdDate;
    private Long issueId;
    private UsersDTO userEntity;
}
