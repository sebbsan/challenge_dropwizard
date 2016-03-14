package com.sebbulon.challenge.service;

import com.sebbulon.challenge.api.Transaction;
import io.dropwizard.lifecycle.Managed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastianweikart on 14/03/2016.
 */
public class StorageService implements Managed {

    List<Transaction> transactions  = new ArrayList<>();

    public List<Transaction> getService() {
        return transactions;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {

    }
}
