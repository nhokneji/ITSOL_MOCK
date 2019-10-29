package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.LevelsEntity;
import com.itsol.mockup.entity.PermissionEntity;
import com.itsol.mockup.repository.LevelsRepository;
import com.itsol.mockup.services.LevelsService;
import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.level.LevelsDTO;
import com.itsol.mockup.web.dto.permisson.PermissionDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LevelsServiceImpl extends BaseService implements LevelsService {

    @Autowired
    private LevelsRepository levelsRepository;

    @Override
    public BaseResultDTO findAllLevel(BaseDTO baseDTO) {
        logger.info("=== START FIND ALL LEVEL::");
        ArrayResultDTO arrayResultDTO = new ArrayResultDTO<>();
        List<LevelsDTO> list = new ArrayList<>();
        try{
            Page<LevelsEntity> datas = levelsRepository.findAll(PageRequest.of(baseDTO.getPage(), baseDTO.getPageSize()));
            if (datas != null) {
                if(datas.getContent().size() > 0){
                    for (LevelsEntity data: datas){
                        LevelsDTO levelsDTO = modelMapper.map(data,LevelsDTO.class);
                        list.add(levelsDTO);
                    }
                }
                arrayResultDTO.setSuccess(list,datas.getTotalElements(),datas.getTotalPages());
                logger.info("FIND ALL LEVEL WITH RESULT"+ arrayResultDTO.getErrorCode());
            }
        }catch (Exception ex){
            arrayResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);

        }
        return arrayResultDTO;
    }

    @Override
    public BaseResultDTO addLevel(LevelsDTO levelsDTO) {
        logger.info("START ADD NEW LEVEL");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            LevelsEntity levelsEntity = modelMapper.map(levelsDTO, LevelsEntity.class);
            levelsEntity = levelsRepository.save(levelsEntity);
            if(levelsEntity.getLevelId() != null) {
                singleResultDTO.setSuccess(levelsEntity);
            }
            logger.info("ADD NEW LEVEL RESPONSE:: "+singleResultDTO.getErrorCode());
        }catch (Exception ex) {
            singleResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return singleResultDTO;
    }


    @Override
    public BaseResultDTO updateLevel(LevelsDTO levelsDTO) {
        logger.info("START UPDATE LEVEL");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            LevelsEntity levelsEntity = levelsRepository.findLevelsEntityByLevelId(levelsDTO.getLevelId());
            if(levelsEntity.getLevelId() != null){
                levelsEntity = modelMapper.map(levelsDTO, LevelsEntity.class);
                levelsEntity = levelsRepository.save(levelsEntity);
                singleResultDTO.setSuccess(levelsEntity);
            }
            logger.info("UPDATE LEVEL RESPONSE:: "+singleResultDTO.getErrorCode());
        }catch (Exception ex) {
            singleResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage(), ex);
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO deleteLevel(Long id) {
        logger.info("START DELTE LEVEL");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try{
            if (id != null){
                levelsRepository.deleteById(id);
                singleResultDTO.setSuccess();
            }
            logger.info("DELETE PERMISSION LEVEL:: "+singleResultDTO.getErrorCode());
        }catch (Exception ex){
            singleResultDTO.setFail(ex.getMessage());
            logger.error(ex.getMessage());
        }
        return singleResultDTO;
    }
}
