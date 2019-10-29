package com.itsol.mockup.services;

import com.itsol.mockup.entity.NewEntity;
import com.itsol.mockup.web.dto.news.NewsDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface NewService {
    ArrayResultDTO<NewEntity> findAll();
    BaseResultDTO addNew(NewsDTO newsDTO);
    BaseResultDTO updateNew(NewsDTO newsDTO);
    BaseResultDTO deleteNew(Long id);

}
