package com.sample.test.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : ErrorResponse.java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    private String msgError;

    public ErrorResponse(){}

    public ErrorResponse(String msgError){
        this.msgError = msgError;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}
