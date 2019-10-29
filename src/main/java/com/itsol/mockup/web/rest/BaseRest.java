package com.itsol.mockup.web.rest;

import org.springframework.http.HttpHeaders;

public class BaseRest {

    protected String retrieveToken(HttpHeaders header){
        String token = header.get("Auth-token").toString();
        token = token.substring(1, token.length() - 1);
        return token;
    }
}
