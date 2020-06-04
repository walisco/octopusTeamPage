package hooks;

import cucumber.api.java.Before;
import pageObjects.SharedDriver;

public class hooks {
    @Before
    public void beforeScenario(){
        System.out.println("This will run before the Scenario");
        SharedDriver.getWebDriver().manage().deleteAllCookies();
    }
}
