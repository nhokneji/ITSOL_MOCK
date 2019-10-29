package com.itsol.mockup.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

public class BaseService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ModelMapper modelMapper;

    protected Timestamp getCurTimestamp(){
        return new Timestamp(new Date().getTime());
    }

}
