package com.sample.test.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : TransactionReportResponse.java
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionReportResponse {

    Long user_id;
    String weekStartDate;
    String weekFinishDate;
    Integer quantity;
    Double amount;
    Double total_amount;

    public TransactionReportResponse(){}

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public String getWeekFinishDate() {
        return weekFinishDate;
    }

    public void setWeekFinishDate(String weekFinishDate) {
        this.weekFinishDate = weekFinishDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }
}
