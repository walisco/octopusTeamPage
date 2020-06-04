package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static pageObjects.SharedDriver.getWebDriver;

public class BasePage {
    protected WebDriver driver;
    private static final long DRIVER_WAIT_TIME=30;
    private static WebDriverWait wait;

    public BasePage() {
        this.driver = getWebDriver();
        this.wait = new WebDriverWait(driver,DRIVER_WAIT_TIME);
    }

    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException | TimeoutException e) {
            e.getMessage();
            return null;
        }
    }

    public List<WebElement> waitForExpectedElements(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), waitTimeInSeconds);
            return wait.until(visibilityOfAllElementsLocatedBy(by));
        } catch (NoSuchElementException | TimeoutException e) {
            e.getMessage();
            return null;
        }
    }

}
