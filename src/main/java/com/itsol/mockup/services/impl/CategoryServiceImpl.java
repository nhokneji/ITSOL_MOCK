package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.CategoryEntity;
import com.itsol.mockup.repository.CategoryRepository;
import com.itsol.mockup.services.CategoryService;
import com.itsol.mockup.utils.Constants;
import com.itsol.mockup.web.dto.category.CategoryDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public ArrayResultDTO<CategoryEntity> findAll() {
       ArrayResultDTO<CategoryEntity> arrayResultDTO = new ArrayResultDTO<>();
       arrayResultDTO.setSuccess(categoryRepository.findAll(),1L,2);
       return arrayResultDTO;
    }

    @Override
    public BaseResultDTO addCategory(CategoryDTO categoryDTO) {
        logger.info("ADD CATEGORY");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            CategoryEntity categoryEntity = modelMapper.map(categoryDTO,CategoryEntity.class);
            categoryEntity = categoryRepository.save(categoryEntity);
            singleResultDTO.setSuccess(categoryEntity);
        }catch (Exception e){
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO updateCategory(CategoryDTO categoryDTO) {
        logger.info("UPDATE CATEGORY");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            CategoryEntity categoryEntity = categoryRepository.getCategoryEntityByCategoryId(categoryDTO.getCategoryId());
            if(categoryEntity.getCategoryId()!=null){
                categoryEntity.setName(categoryDTO.getName());
                categoryRepository.save(categoryEntity);
                singleResultDTO.setSuccess(categoryDTO);
            }
        }catch (Exception e){
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO deleteCategory(Long id) {
        logger.info("DELETE CATEGORY");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            if(id!=null){
                categoryRepository.deleteById(id);
                singleResultDTO.setSuccess();
            }
            logger.info("DELETE CATEGORY FROM:"+singleResultDTO.getErrorCode());
        }catch (Exception e){
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }

}
