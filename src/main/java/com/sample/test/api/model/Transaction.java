package com.sample.test.api.model;

import com.sample.test.api.request.TransactionAddRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : Transaction.java
 */
public class Transaction {

    private String transaction_id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long user_id;

    public Transaction(){}

    public Transaction(TransactionAddRequest transactionAddRequest){
        this.amount = transactionAddRequest.getAmount();
        this.description = transactionAddRequest.getDescription();
        this.date = transactionAddRequest.getDate();
        this.user_id = transactionAddRequest.getUser_id();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transaction_id, that.transaction_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction_id);
    }
}
