package com.sample.test.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sample.test.api.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionAddResponse {
    private String transaction_id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long user_id;

    public TransactionAddResponse(){}

    public TransactionAddResponse(Transaction transaction){
        this.transaction_id = transaction.getTransaction_id();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.user_id = transaction.getUser_id();
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

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
}
