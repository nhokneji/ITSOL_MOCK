package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.BaseDTO;
import com.itsol.mockup.web.dto.level.LevelsDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;

public interface LevelsService {
    BaseResultDTO findAllLevel(BaseDTO baseDTO);
    BaseResultDTO addLevel(LevelsDTO levelsDTO);
    BaseResultDTO updateLevel(LevelsDTO levelsDTO);
    BaseResultDTO deleteLevel(Long id);

}
