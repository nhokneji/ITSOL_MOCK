package com.itsol.mockup.web.rest.issue;


import com.itsol.mockup.services.IssueService;
import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.issue.IssueDTO;
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
public class IssueController {
    @Autowired
    private IssueService issueService;

    @RequestMapping(value = "/issue",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllIssue(@RequestParam("pageSize") Integer pageSize,
                                                      @RequestParam("page") Integer page) {
        BaseResultDTO result = issueService.findAllIssue(pageSize, page);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addIssue(@RequestBody IssueDTO requestDTO) {
        BaseResultDTO result = issueService.addIssue(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/issue", method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateIssue(@RequestBody IssueDTO requestDTO) {
        BaseResultDTO result = issueService.updateIssue(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/issue", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteIssue(@RequestParam("id") long id) {
        BaseResultDTO result = issueService.deleteIssue(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/issue/findById", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findIssueById(@RequestParam("id") Long id){
        BaseResultDTO result = issueService.getIssueById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/issue/findByUserId",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findIssueByUserId(@RequestParam("pageSize") Integer pageSize,
                                                           @RequestParam("page") Integer page,
                                                           @RequestParam("id") Long id){
        BaseResultDTO result = issueService.findIssueByUserId(pageSize,page,id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/issue/search", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> findIssueById(@RequestBody SearchUsersRequestDTO searchUsersRequestDTO){
        BaseResultDTO result = issueService.searchIssue(searchUsersRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
