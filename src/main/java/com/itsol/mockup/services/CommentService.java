package com.itsol.mockup.services;

import com.itsol.mockup.entity.CommentsEntity;
import com.itsol.mockup.web.dto.comment.CommentDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface CommentService {
    ArrayResultDTO<CommentsEntity> findAll();
    BaseResultDTO addComment(CommentDTO commentDTO);
    BaseResultDTO updateComment(CommentDTO commentDTO);
    BaseResultDTO deleteComment(Long id);
}
