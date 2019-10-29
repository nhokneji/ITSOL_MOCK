package com.itsol.mockup.services;

import com.itsol.mockup.entity.ImageEntity;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ImagesService {
    ArrayResultDTO<ImageEntity> findAll();
    BaseResultDTO addImage(MultipartFile file, HttpServletRequest httpServletRequest);
    BaseResultDTO deleteImages(Long id);
    BaseResultDTO findImageById(Long id);



    Resource loadFileAsResource(String fileName);
}
