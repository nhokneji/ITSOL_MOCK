package com.itsol.mockup.web.rest.projects;

import com.itsol.mockup.services.ProjectService;
import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.project.ProjectDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.rest.BaseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Scope("request")
@CrossOrigin("*")
public class ProjectController extends BaseRest {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/projects",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAll(@RequestParam("pageSize") Integer PageSize,
                                                 @RequestParam("page") Integer page
                                                ) {
        BaseResultDTO result = projectService.findAll(PageSize, page);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/projects",method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addProject(@RequestBody ProjectDTO projectDTO,
                                                    @RequestHeader HttpHeaders header){
        BaseResultDTO result = projectService.addProject(retrieveToken(header), projectDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/projects",method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateProject(@RequestBody ProjectDTO projectDTO){
        BaseResultDTO result = projectService.updateProject(projectDTO);
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/projects",method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteProject(@RequestParam("id") long id) {
        BaseResultDTO result = projectService.deleteProject(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/searchProjectById",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findProjectById(@RequestParam("id") long id) {
        BaseResultDTO result = projectService.findProjectById(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @RequestMapping(value = "/projects/findProjectAndTeamLeadByProjectId",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findProjectAndTeamLeadByProjectId(@RequestParam("id") long id) {
        BaseResultDTO result = projectService.findProjectAndTeamLeadByProjectId(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/getTotalMember",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> getTotalMember(@RequestParam("id") long id) {
        BaseResultDTO result = projectService.getTotalMember(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/getTotalTimesheet",method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> getTotalMember(@RequestBody SearchUsersRequestDTO searchUsersRequestDTO) {
        BaseResultDTO result = projectService.getTotalTimesheet(searchUsersRequestDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}
