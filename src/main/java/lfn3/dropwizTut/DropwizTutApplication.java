package lfn3.dropwizTut;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lfn3.dropwizTut.health.TemplateHealthCheck;
import lfn3.dropwizTut.resources.HelloWorldResource;

public class DropwizTutApplication extends Application<DropwizTutConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropwizTutApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void run(DropwizTutConfiguration dropwizTutConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                dropwizTutConfiguration.getTemplate(),
                dropwizTutConfiguration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(dropwizTutConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
