package com.sample.test.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sample.test.api.exception.APIException;
import com.sample.test.api.utils.Constants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : TransactionAddRequest.java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionAddRequest {
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long user_id;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    /**
     * Verifica que los campos requeridos se encuentren presentes en el request.
     * @throws	APIException si falta alguno de los campos requeridos.
     */
    public void checkNotNull() throws APIException {
        if(Objects.isNull(user_id)) {
            throw new APIException(String.format(Constants.REQUIRED_PARAM, "user_id"));
        }
        if(Objects.isNull(date)) {
            throw new APIException(String.format(Constants.REQUIRED_PARAM, "date"));
        }
        if(Objects.isNull(amount)) {
            throw new APIException(String.format(Constants.REQUIRED_PARAM, "amount"));
        }
        if(Objects.isNull(description)) {
            throw new APIException(String.format(Constants.REQUIRED_PARAM, "description"));
        }
    }

}
