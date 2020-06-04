package utilities;

//import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

public class hooks {
    WebDriver driver;
    @Before
    public void beforeScenario(){
        System.out.println("This will run before the Scenario");
        driver.manage().deleteAllCookies();
    }@After
    public void afterScenario(){
        System.out.println("This will run before the Scenario");
        driver.quit();
    }
}
