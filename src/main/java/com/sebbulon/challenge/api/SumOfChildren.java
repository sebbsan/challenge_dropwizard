package com.sebbulon.challenge.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sebastianweikart on 13/03/2016.
 */
public class SumOfChildren {
    private double sum;

    public SumOfChildren() {

    }

    public SumOfChildren(double sum) {
        this.sum = sum;
    }
    @JsonProperty
    public void setSum(double summ) {
        this.sum = sum;

    }

    @JsonProperty
    public double getSum() {
        return sum;

    }
}
