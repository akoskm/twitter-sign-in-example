package io.github.akoskm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class AppConfig extends Configuration {

    @JsonProperty
    private String consumerKey;

    @JsonProperty
    private String consumerSecret;

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }
}
