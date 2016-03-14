package com.sebbulon.challenge.service;

import com.sebbulon.challenge.api.Transaction;
import io.dropwizard.lifecycle.Managed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastianweikart on 14/03/2016.
 * A service that is managed by the DropWizard Framework. In our case simply a List that remains in memory.
 * This can also represent a database connection, or a connection to another 3rd party system.
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
