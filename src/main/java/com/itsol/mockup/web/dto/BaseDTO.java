package com.itsol.mockup.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author anhvd_itsol
 */

@Getter
@Setter
public class BaseDTO implements Serializable {
    protected Integer pageSize;
    protected Integer page;
    protected String sort;
}
