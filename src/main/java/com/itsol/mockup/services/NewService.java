package com.itsol.mockup.services;

import com.itsol.mockup.entity.NewEntity;
import com.itsol.mockup.web.dto.news.NewsDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface NewService {
//    @PreAuthorize("hasRole('HR')")
    ArrayResultDTO<NewEntity> findAll(Integer pageSize, Integer page);
    ArrayResultDTO<NewEntity> findNewPublic(Integer pageSize, Integer page);
//    @PreAuthorize("hasRole('ROLE_HR')")
    BaseResultDTO addNew(NewsDTO newsDTO);
    BaseResultDTO updateNew(NewsDTO newsDTO);
    BaseResultDTO deleteNew(Long id);
    BaseResultDTO findOneById(Long id);

}

