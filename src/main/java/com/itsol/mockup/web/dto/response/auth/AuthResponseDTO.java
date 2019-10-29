package com.itsol.mockup.web.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private String errorCode;
    private String username;
    private String password;
    private String roles;
    private String token;

}
