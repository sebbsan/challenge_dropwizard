package com.sebbulon.challenge.service;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by sebastianweikart on 14/03/2016.
 */
public class StorageServiceHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
