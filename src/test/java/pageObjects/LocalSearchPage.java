package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static pageObjects.TeamPage.DRIVER_WAIT_TIME;

public class LocalSearchPage extends BasePage {

    final By postcodeInput = cssSelector("[name=\"postcode\"]");
    final By postcodeSubmit = cssSelector(".search-submit");
    final By searchResults = cssSelector(".bdm-results-meta");

    public void findLocalContact(String postcode) {
        waitForExpectedElement(postcodeInput, DRIVER_WAIT_TIME).sendKeys(postcode);
        waitForExpectedElement(postcodeSubmit, DRIVER_WAIT_TIME).click();
    }

    public String searchResults() {
        waitForExpectedElement(searchResults, DRIVER_WAIT_TIME).clear();
        return waitForExpectedElement(searchResults, DRIVER_WAIT_TIME).getText();
    }

    public  String searchPostCode(String postcode) {
        findLocalContact(postcode);
        return searchResults();
    }
}
