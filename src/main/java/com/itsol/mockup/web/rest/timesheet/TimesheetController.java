package com.itsol.mockup.web.rest.timesheet;

import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.services.TimesheetService;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.timesheet.TimesheetDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
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
@CrossOrigin
public class TimesheetController extends BaseRest {
    @Autowired
    TimesheetService timesheetService;
    @RequestMapping("/timesheet/list")
    public ResponseEntity<BaseResultDTO> findAll(@RequestParam("pageSize") Integer pageSize,
                                                 @RequestParam("page") Integer page){
        BaseResultDTO result = timesheetService.findAll(pageSize, page);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
     @RequestMapping("/timesheet/listByUser")
    public ResponseEntity<BaseResultDTO> findAllTimeSheetById(UsersDTO usersDTO, @RequestParam("pageSize") Integer pageSize,
                                                              @RequestParam("page") Integer page){
        BaseResultDTO result = timesheetService.searchTimesheetByuser(usersDTO, pageSize, page);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/timesheet",method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addTimesheet(@RequestBody TimesheetDTO timesheetDTO,
                                                      @RequestHeader HttpHeaders headers){
        BaseResultDTO result = timesheetService.addTimesheet(timesheetDTO, retrieveToken(headers));
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "/timesheet",method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateTimesheet(@RequestBody TimesheetDTO timesheetDTO){
        BaseResultDTO result = timesheetService.updateTimesheet(timesheetDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @RequestMapping(value = "/timesheet",method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteTimesheet(Long id){
        BaseResultDTO result = timesheetService.deleteTimesheet(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
