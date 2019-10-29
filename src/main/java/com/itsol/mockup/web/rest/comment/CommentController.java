package com.itsol.mockup.web.rest.comment;

import com.itsol.mockup.services.CommentService;
import com.itsol.mockup.web.dto.comment.CommentDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Scope("request")
@RequestMapping("/api")
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("/comment/list")
    public ResponseEntity<BaseResultDTO> findAll(){
        BaseResultDTO result = commentService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addComment(@RequestBody CommentDTO commentDTO){
        BaseResultDTO result = commentService.addComment(commentDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/comment",method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateComment(@RequestBody CommentDTO commentDTO){
        BaseResultDTO result = commentService.updateComment(commentDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/comment",method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteComment(Long id){
        BaseResultDTO result = commentService.deleteComment(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
