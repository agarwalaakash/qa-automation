package Runner;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources/Feature/Test.feature",
        glue = {"stepDefinitions"},
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json","html:target/cucumber-reports/cucumber.html"},
        monochrome = true,
        dryRun = false
)
public class TestRunner {
}