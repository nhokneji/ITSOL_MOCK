package com.itsol.mockup.web.dto.users;

import com.itsol.mockup.web.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
public class UsersDTO extends BaseDTO {
    private Long id;
    private String userName;
    private String passWord;
    private String fullName;
    private Short role;
}
