package com.sebbulon.challenge.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sebastianweikart on 13/03/2016.
 */
public class Transaction {
    private long id;
    private double amount;
    private String type;
    private long parentId;

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @JsonProperty
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public Transaction() {

    }

    public Transaction(long id, double amount, String type, long parentId) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
    }

    @JsonProperty
    public void setId(Long id) {
        this.id = id;

    }

    @JsonProperty
    public long getId() {
        return id;

    }

    @JsonProperty
    public double getAmount() {
        return amount;
    }


    @JsonProperty
    public String getType() {
        return type;
    }


    @JsonProperty
    public long getParentId() {
        return parentId;
    }

}
