package com.itsol.mockup.web.dto.request;

import com.itsol.mockup.web.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IdRequestDTO extends BaseDTO {
    Long id;
    List<Long> ids;
}
