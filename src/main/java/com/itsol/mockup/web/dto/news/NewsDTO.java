package com.itsol.mockup.web.dto.news;

import com.itsol.mockup.web.dto.image.ImagesDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class NewsDTO {
    private Long newId;
    private String title;
    private Date createdDate;
    private Date modifiedDate;
    private String description;
    private String content;
    private String createdBy;
    private String modifiedBy;
    private UsersDTO user;
    private ImagesDTO image;
}
