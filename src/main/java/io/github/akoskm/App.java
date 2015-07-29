package io.github.akoskm;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfig> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
    }

    @Override
    public void run(AppConfig configuration,
                    Environment environment) {
        final SignUpResource resource = new SignUpResource(configuration);
        environment.jersey().register(resource);
    }

}