package com.itsol.mockup.web.dto.request;

import com.itsol.mockup.web.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
public class SearchUsersRequestDTO extends BaseDTO {
    private Long userId;
    private String userName;
    private String email;
    private String fullName;
    private Integer role;
    private Date fromDate;
    private Date toDate;
    private Date createdDate;
    private String issueName;
    private Long projectId;
}
