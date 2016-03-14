package com.sebbulon.challenge.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**

 * Created by sebastianweikart on 13/03/2016.
 * this class represents a single transaction.
 * It is supposed to be immutable
 */
public class Transaction {
    private long id;
    private double amount;
    private String type;
    private Long parentId = null;

    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (getId() != that.getId()) return false;
        if (Double.compare(that.getAmount(), getAmount()) != 0) return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null) return false;
        return getParentId() != null ? getParentId().equals(that.getParentId()) : that.getParentId() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getId() ^ (getId() >>> 32));
        temp = Double.doubleToLongBits(getAmount());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getParentId() != null ? getParentId().hashCode() : 0);
        return result;
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
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Transaction() {

    }

    public Transaction(long id, double amount, String type, Long parentId) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
    }


    public Transaction(double amount, String type, Long parentId) {
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
    public Long getParentId() {
        return parentId;
    }

}
