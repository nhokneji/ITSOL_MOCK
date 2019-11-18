package com.itsol.mockup.web.rest.permission;


import com.itsol.mockup.services.PermissionService;
import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.permisson.PermissionDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Scope("request")
@CrossOrigin
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/permission",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllPermission(@RequestBody BaseDTO requestDTO) {
        BaseResultDTO result = permissionService.findAllPermission(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addPermission(@RequestBody PermissionDTO requestDTO) {
        BaseResultDTO result = permissionService.addPermission(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/permission", method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updatePermission(@RequestBody PermissionDTO requestDTO) {
        BaseResultDTO result = permissionService.updatePermission(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/permission", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deletePermission(@RequestParam("id") long id) {
        BaseResultDTO result = permissionService.deletePermission(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/permission/findByStatus", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllPermissionByStatus(@RequestParam("page") Integer page,
                                                                   @RequestParam("pageSize") Integer pageSize){
        BaseResultDTO result = permissionService.findAllPermissionByStatus(0,page,pageSize);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/permission/findByUserName", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findPermissionByStatus(@RequestParam("username") String username,
                                                                @RequestParam("page") Integer page,
                                                                @RequestParam("pageSize") Integer pageSize){
        BaseResultDTO result = permissionService.findPermissionByUserName(username, page, pageSize);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/permission/search", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> searchPermission(@RequestBody SearchUsersRequestDTO searchUsersRequestDTO){
        BaseResultDTO result = permissionService.searchPermission(searchUsersRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
