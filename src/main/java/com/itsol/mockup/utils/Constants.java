package com.itsol.mockup.utils;

import com.itsol.mockup.web.dto.response.ResultDTO;
import org.springframework.data.domain.Page;

/**
 * @author anhvd_itsol
 */
public class Constants {
    public interface ApiErrorCode {
        String ERROR = "01";
        String SUCCESS = "00";
        String DELETE_ERROR = "02";
    }
    public interface ApiErrorDesc {
        String ERROR = "ERROR";
        String SUCCESS = "SUCCESS";
        String DELETE_ERROR = "DELETE_ERROR";
    }

//    public ResultDTO pageToObj (Page<Object> page) {
//        ResultDTO resultDTO = new ResultDTO();
//        if(page != null) {
//            resultDTO.set
//        }
//    }
}
