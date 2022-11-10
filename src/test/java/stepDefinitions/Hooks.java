package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class Hooks {

    private final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void TestSetup(Scenario scenario) {
        logger.info("Started running the scenario : " + scenario.getName());
    }
}