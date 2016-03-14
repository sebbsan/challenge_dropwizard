package com.sebbulon.challenge.mongo;

import com.mongodb.async.client.MongoClient;
import io.dropwizard.lifecycle.Managed;

/**
 * Created by sebastianweikart on 13/03/2016.
 */
public class MongoManagedAsync implements Managed {

    private MongoClient mongo;

    public MongoManagedAsync(MongoClient mongo) {
        this.mongo = mongo;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        mongo.close();
    }
}
