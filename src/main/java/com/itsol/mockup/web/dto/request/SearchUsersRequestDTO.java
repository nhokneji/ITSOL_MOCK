package com.itsol.mockup.web.dto.request;

import com.itsol.mockup.web.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
public class SearchUsersRequestDTO extends BaseDTO {
    private String userName;
    private String fullName;
}
