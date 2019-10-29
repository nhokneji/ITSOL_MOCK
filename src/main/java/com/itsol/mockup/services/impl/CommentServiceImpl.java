package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.CommentsEntity;
import com.itsol.mockup.entity.NewEntity;
import com.itsol.mockup.repository.CommentRepository;
import com.itsol.mockup.services.CommentService;
import com.itsol.mockup.utils.Constants;
import com.itsol.mockup.web.dto.comment.CommentDTO;
import com.itsol.mockup.web.dto.news.NewsDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl extends BaseService implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    public ArrayResultDTO<CommentsEntity> findAll() {
       ArrayResultDTO arrayResultDTO = new ArrayResultDTO<>();
        List<CommentDTO> lstResult = new ArrayList<>();
        List<CommentsEntity> rawDatas = commentRepository.findAll();
        if(rawDatas != null) {
            rawDatas.forEach(i -> {
                CommentDTO dto = modelMapper.map(i, CommentDTO.class);
                lstResult.add(dto);
            });
        }
       arrayResultDTO.setSuccess(lstResult,1L,2);
       return arrayResultDTO;
    }

    @Override
    public BaseResultDTO addComment(CommentDTO commentDTO) {
        logger.info("ADD COMMENT");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            CommentsEntity commentsEntity = modelMapper.map(commentDTO,CommentsEntity.class);
            commentsEntity = commentRepository.save(commentsEntity);
            singleResultDTO.setSuccess(commentsEntity);
        }catch (Exception e){
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO updateComment(CommentDTO commentDTO) {
        logger.info("UPDATE COMMENT");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            CommentsEntity commentsEntity = commentRepository.getCommentsEntityByCommentsId(commentDTO.getCommentsId());
            if(commentsEntity.getCommentsId() != null){
                commentsEntity.setContent(commentDTO.getContent());
                commentRepository.save(commentsEntity);
                singleResultDTO.setSuccess(commentsEntity);
            }

        }catch (Exception e){
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }

    @Override
    public BaseResultDTO deleteComment(Long id) {
        logger.info("DELETE COMMENT");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            if(id != null){
                commentRepository.deleteById(id);
                singleResultDTO.setSuccess();
            }
        }catch (Exception e){
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }
        return singleResultDTO;
    }
}
