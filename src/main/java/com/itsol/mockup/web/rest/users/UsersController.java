package com.itsol.mockup.web.rest.users;

import com.itsol.mockup.services.UsersService;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.ResultDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author anhvd_itsol
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsersController {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> users(@RequestBody SearchUsersRequestDTO requestDTO) {
        ResultDTO result = usersService.findAllUsers(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<ResultDTO> addUser(@RequestBody UsersDTO requestDTO) {
        ResultDTO result = usersService.addUser(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<ResultDTO> updateUser(@RequestBody UsersDTO requestDTO) {
        ResultDTO result = usersService.updateUser(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-fullname-username", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> findByFullNameAndUserName (@RequestBody SearchUsersRequestDTO requestDTO){
        ResultDTO result = usersService.findUsersByFullNameAndUserName(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
