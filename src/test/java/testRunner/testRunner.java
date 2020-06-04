package testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/features"},
//        tags = {}, monochrome = true,
        plugin = {
                "html:reports/site/cucumber-pretty",
                "json:reports/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:reports/cucumber-reports/report.html"},
        glue = {"stepDefinitions"}
        )


public class testRunner {
}
