package com.itsol.mockup.web.rest.level;


import com.itsol.mockup.services.LevelsService;
import com.itsol.mockup.services.PermissionService;
import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.level.LevelsDTO;
import com.itsol.mockup.web.dto.permisson.PermissionDTO;
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
public class LevelController {
    @Autowired
    private LevelsService levelsService;

    @RequestMapping(value = "/level",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAllLevel(@RequestBody BaseDTO requestDTO) {
        BaseResultDTO result = levelsService.findAllLevel(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/level", method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addLevel(@RequestBody LevelsDTO requestDTO) {
        BaseResultDTO result = levelsService.addLevel(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/level", method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateLevel(@RequestBody LevelsDTO requestDTO) {
        BaseResultDTO result = levelsService.updateLevel(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/level", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteLevel(@RequestParam("id") long id) {
        BaseResultDTO result = levelsService.deleteLevel(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
