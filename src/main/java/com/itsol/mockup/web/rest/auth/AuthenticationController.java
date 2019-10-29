package com.itsol.mockup.web.rest.auth;

import com.itsol.mockup.services.UsersService;
import com.itsol.mockup.web.dto.request.auth.AuthRequestDTO;
import com.itsol.mockup.web.dto.response.auth.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anhvd_itsol
 */

@RestController
@RequestMapping("auth")
@Scope("request")
public class AuthenticationController {
    @Autowired
    UsersService usersService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthResponseDTO> getToken(@RequestBody AuthRequestDTO userForAuthentication) {
        AuthResponseDTO authenticationResponse = usersService.generateToken(userForAuthentication);
        return new ResponseEntity(authenticationResponse, HttpStatus.OK);
    }
}
