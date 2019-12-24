package com.sample.test.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sample.test.api.exception.APIException;
import com.sample.test.api.utils.Constants;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionByIdRequest {

    private Long user_id;
    private String idTransaction;

    /**
     * Verifica que los campos requeridos se encuentren presentes en el request.
     * @throws APIException si falta alguno de los campos requeridos.
     */
    public void checkNotNull() throws APIException {
        if(Objects.isNull(user_id)) {
            throw new APIException(String.format(Constants.REQUIRED_PARAM, "user_id"));
        }
        if(Objects.isNull(idTransaction)) {
            throw new APIException(String.format(Constants.REQUIRED_PARAM, "idTransaction"));
        }
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }
}
