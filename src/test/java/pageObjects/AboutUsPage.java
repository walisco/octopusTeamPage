package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class AboutUsPage extends BasePage {
    static final long DRIVER_WAIT_TIME = 30;
    final By aboutUs = cssSelector("#menu-item-3033 a");
    final By aboutUsText = cssSelector(".stat-body");
    final By aboutUsInfo = cssSelector(".stat-foot");

    public void aboutUsLink() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2500)");
        waitForExpectedElement(aboutUs, DRIVER_WAIT_TIME).click();
    }

    public String aboutUsLinkYr() {


        String year = waitForExpectedElements(aboutUsText, DRIVER_WAIT_TIME).get(0).getText();
        String yearInfo = waitForExpectedElements(aboutUsInfo, DRIVER_WAIT_TIME).get(0).getText();
        return year.concat(" ").concat(yearInfo);
    }
    public String aboutUsLinkFunds() {

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2500)");
        String fund = waitForExpectedElements(aboutUsText, DRIVER_WAIT_TIME).get(1).getText();
        String fundInfo = waitForExpectedElements(aboutUsInfo, DRIVER_WAIT_TIME).get(1).getText();
        return fund.concat(" ").concat(fundInfo);
    }
}
