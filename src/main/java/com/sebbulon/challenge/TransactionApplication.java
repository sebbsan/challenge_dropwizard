package com.sebbulon.challenge;

import com.sebbulon.challenge.resources.TransactionResource;
import com.sebbulon.challenge.service.StorageService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by sebastianweikart on 13/03/2016.
 */
public class TransactionApplication extends Application<ChallengeConfiguration> {

    public static void main(String[] args) throws Exception {
        new TransactionApplication().run(args);
    }

    @Override
    public String getName() {
        return "Transaction Application";
    }

    @Override
    public void initialize(Bootstrap<ChallengeConfiguration> bootstrap) {
        // nothing to do yet
    }


    @Override
    public void run(ChallengeConfiguration challengeConfiguration, Environment environment) throws Exception {
        StorageService transactions = new StorageService();
        environment.lifecycle().manage(transactions);
        environment.jersey().register(new TransactionResource(transactions));
    }
}

