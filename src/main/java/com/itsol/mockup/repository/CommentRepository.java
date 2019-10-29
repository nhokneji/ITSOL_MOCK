package com.itsol.mockup.repository;

import com.itsol.mockup.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentsEntity,Long> {
    CommentsEntity getCommentsEntityByCommentsId(Long id);
}
