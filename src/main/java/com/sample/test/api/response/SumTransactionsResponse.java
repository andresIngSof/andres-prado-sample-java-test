package com.sample.test.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : SumTransactionsResponse.java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SumTransactionsResponse {
    private Long user_id;
    private Double sum;

    public SumTransactionsResponse(){}

    public SumTransactionsResponse(Long user_id, Double sum) {
        this.user_id = user_id;
        this.sum = sum;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
