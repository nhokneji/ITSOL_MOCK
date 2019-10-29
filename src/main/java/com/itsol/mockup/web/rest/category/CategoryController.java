package com.itsol.mockup.web.rest.category;

import com.itsol.mockup.services.CategoryService;
import com.itsol.mockup.web.dto.category.CategoryDTO;
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
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/list",method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAll(){
        BaseResultDTO result = categoryService.findAll();
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/category",method = RequestMethod.POST)
    public ResponseEntity<BaseResultDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        BaseResultDTO result = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/category",method = RequestMethod.PUT)
    public ResponseEntity<BaseResultDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        BaseResultDTO result = categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/category",method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteCategory(Long id){
        BaseResultDTO result = categoryService.deleteCategory(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}
