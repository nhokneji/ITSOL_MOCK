package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.NewEntity;
import com.itsol.mockup.repository.NewRepository;
import com.itsol.mockup.services.NewService;
import com.itsol.mockup.utils.Constants;
import com.itsol.mockup.web.dto.news.NewsDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewServiceImpl extends BaseService implements NewService {

    @Autowired
    private NewRepository newRepository;

    @Override
    public ArrayResultDTO<NewEntity> findAll() {
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO();
        List<NewsDTO> lstResult = new ArrayList<>();
        List<NewEntity> rawDatas = newRepository.findAll();
        if (rawDatas != null) {
            rawDatas.forEach(i -> {
                NewsDTO dto = modelMapper.map(i, NewsDTO.class);
                lstResult.add(dto);
            });
        }
        arrayResultDTO.setSuccess(lstResult, 1L, 2);
        return arrayResultDTO;
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
}
