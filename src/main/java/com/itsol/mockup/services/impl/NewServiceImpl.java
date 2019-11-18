package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.ImageEntity;
import com.itsol.mockup.entity.NewEntity;
import com.itsol.mockup.repository.NewRepository;
import com.itsol.mockup.services.ImagesService;
import com.itsol.mockup.services.NewService;
import com.itsol.mockup.utils.Constants;
import com.itsol.mockup.web.dto.image.ImagesDTO;
import com.itsol.mockup.web.dto.news.NewsDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewServiceImpl extends BaseService implements NewService {

    @Autowired
    private NewRepository newRepository;
    @Autowired
    private ImagesService imagesService;

    @Override
    public ArrayResultDTO<NewEntity> findAll(Integer pageSize, Integer page) {
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO();
        List<NewsDTO> lstResult = new ArrayList<>();
        try {
            Page<NewEntity> rawDatas = newRepository.findAll(PageRequest.of(page - 1,pageSize));
            if (rawDatas != null) {
                if (rawDatas.getContent().size() > 0) {
                    for(NewEntity newEntity:  rawDatas.getContent()){
                        ImagesDTO imagesDTO =  imagesService.findOneById(newEntity.getImageId());
                        NewsDTO newsDTO = modelMapper.map(newEntity, NewsDTO.class);
                        newsDTO.setImage(imagesDTO);
                        lstResult.add(newsDTO);
                    }
                }
                arrayResultDTO.setSuccess(lstResult,rawDatas.getTotalElements(),rawDatas.getTotalPages());
            }
        }catch (Exception e){

        }
        return arrayResultDTO;
    }

    @Override
    public BaseResultDTO findOneById(Long id) {
        logger.info("=== FIND ONE NEW");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            if (id != null){
                NewEntity newEntity = newRepository.getNewEntityByNewId(id);
                NewsDTO newsDTO = modelMapper.map(newEntity, NewsDTO.class);
                newsDTO.setImage(imagesService.findOneById(newEntity.getImageId()));
                singleResultDTO.setSuccess(newsDTO);
            }
            logger.info("=== FIND ONE NEW RESPONSE:: "+ singleResultDTO.getErrorCode());
        }catch (Exception e){
            singleResultDTO.setFail("FAIL");
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }


    @Override
    public BaseResultDTO addNew(NewsDTO newsDTO) {
        logger.info("START ADD NEW");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            NewEntity newEntity = modelMapper.map(newsDTO, NewEntity.class);
            newEntity = newRepository.save(newEntity);
            singleResultDTO.setSuccess(newEntity);
        } catch (Exception e) {
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO updateNew(NewsDTO newsDTO) {
        logger.info("UPDATE NEW");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            NewEntity newEntity = newRepository.getNewEntityByNewId(newsDTO.getNewId());
            if (newEntity.getNewId() != null) {
                newEntity = modelMapper.map(newsDTO, NewEntity.class);
                newRepository.save(newEntity);
                singleResultDTO.setSuccess(newEntity);
            }
        } catch (Exception e) {

            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO deleteNew(Long id) {
        logger.info("DELETE NEW");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            if (id != null) {
                newRepository.deleteById(id);
                singleResultDTO.setSuccess();
            }
            logger.info("DELETE NEW RESPONSE:" + singleResultDTO.getErrorCode());
        } catch (Exception e) {
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }

    @Override
    public ArrayResultDTO<NewEntity> findNewPublic(Integer pageSize, Integer page) {
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO();
        List<NewsDTO> lstResult = new ArrayList<>();
        try {
            Page<NewEntity> rawDatas = newRepository.findAll(PageRequest.of(page - 1,pageSize));
            if (rawDatas != null) {
                if (rawDatas.getContent().size() > 0) {
                    rawDatas.forEach(i -> {
                        NewsDTO dto = modelMapper.map(i, NewsDTO.class);
                        if (i.getImageId() != null) {
                            dto.setImage(imagesService.findOneById(i.getImageId()));
                        }
                        lstResult.add(dto);
                    });
                }
                arrayResultDTO.setSuccess(lstResult,rawDatas.getTotalElements(),rawDatas.getTotalPages());
            }
        }catch (Exception e){

        }
        return arrayResultDTO;
    }

}
