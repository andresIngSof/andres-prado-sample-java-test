package com.sample.test.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sample.test.api.exception.APIException;
import com.sample.test.api.utils.Constants;

import java.util.Objects;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : TransactionsByUserIdRequest.java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionsByUserIdRequest {

    private Long user_id;

    /**
     * Verifica que los campos requeridos se encuentren presentes en el request.
     * @throws APIException si falta alguno de los campos requeridos.
     */
    public void checkNotNull() throws APIException {
        if(Objects.isNull(user_id)) {
            throw new APIException(String.format(Constants.REQUIRED_PARAM, "user_id"));
        }
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
