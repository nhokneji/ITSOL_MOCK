package com.itsol.mockup.web.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
public class ResultDTO {
    private String errorCode;
    private String description;
    private List<?> listData = new ArrayList();
    private Object data;
    private Long totalRow = 0L;
    private Integer totalPage = 0;
}
