package com.sebbulon.challenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by sebastianweikart on 13/03/2016.
 */
public class ChallengeConfiguration extends Configuration {
    @JsonProperty
    private String template;

    @JsonProperty
    public String mongodb = "number27";

    @JsonProperty
    public String mongoConnectionString = "mongodb://localhost:27017";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getMongodb() {
        return mongodb;
    }

    @JsonProperty
    public void setMongodb(String mongodb) {
        this.mongodb = mongodb;
    }

}
