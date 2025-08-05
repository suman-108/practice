package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = ".//Features/api_test.feature",
    glue = {"stepDefinition"},
    dryRun = false,
    monochrome = true,
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    stepNotifications = true
)

public class TestRunner {

}
