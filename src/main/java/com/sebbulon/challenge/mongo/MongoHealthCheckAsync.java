package com.sebbulon.challenge.mongo;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.async.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;


/**
 * Created by sebastianweikart on 13/03/2016.
 */
public class MongoHealthCheckAsync extends HealthCheck {

    private MongoDatabase db;
    final static Logger logger = LoggerFactory.getLogger(MongoHealthCheckAsync.class);
    CompletableFuture<Result> check = new CompletableFuture<>();

    public MongoHealthCheckAsync(MongoDatabase db) {
        this.db = db;
    }

    @Override
    protected Result check() throws Exception {

        try {
            db.runCommand(new Document("isMaster", 1), (result, t) -> {
                if (t != null) {
                    logger.error("Mongo Health Check failed", t.getMessage());
                    check.complete(Result.unhealthy("some error happened while trying to connect to Mongo", t));
                } else {
                    check.complete(Result.healthy());
                }
            });
        } catch (Throwable me) {
            check.complete(Result.unhealthy("cannot access database", me));
        }

        return check.get();
    }
}
