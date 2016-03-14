package com.sebbulon.challenge.service;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by sebastianweikart on 14/03/2016.
 * DropWizard enables us to health check outside resources which would inform us if a system we depend on is down or producing errors- this should be implemented here
 */
public class StorageServiceHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
