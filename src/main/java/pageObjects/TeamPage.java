package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.By.cssSelector;

public class TeamPage extends BasePage {
    static final long DRIVER_WAIT_TIME = 30;
    final By modalSelectorAdviser = cssSelector("[value='adviser']");
    final By modalSelectorInvestor = cssSelector("[value='investor']");
    final By confirmAdviser = cssSelector(".adviser-confirm-button");
    final By confirmInvester = cssSelector(".investor-confirm-button");
    final By adviserContactInformation = cssSelector(".wp-block-deepsea-personalisationnested.adviser h2");
    final By investorContactInformation = cssSelector(".wp-block-deepsea-personalisationnested.investor h2");
    final By profileNames = cssSelector(".profileCard-name");
    final By profileRoles = cssSelector(".profileCard-title");

    public void openTeamPage() {
        SharedDriver.getWebDriver().get(Props.getProp("url"));
    }

    public void clickModalSelectorPopup(String customerType) {
        switch (customerType.toLowerCase()) {
            case "adviser": {
                ((JavascriptExecutor) SharedDriver.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(modalSelectorAdviser, DRIVER_WAIT_TIME));
                waitForExpectedElement(modalSelectorAdviser, DRIVER_WAIT_TIME).click();
                break;
            }
            case "investor":
                waitForExpectedElement(modalSelectorInvestor, DRIVER_WAIT_TIME).click();
                break;
            default:
                System.out.println("unknown");
        }
    }

    public void clickModalConfirm(String customerType)  {
        if (customerType.equalsIgnoreCase("investor")) {
            waitForExpectedElement(confirmInvester, DRIVER_WAIT_TIME).click();
        } else {
            ((JavascriptExecutor) SharedDriver.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(modalSelectorAdviser, DRIVER_WAIT_TIME));
            waitForExpectedElement(confirmAdviser, DRIVER_WAIT_TIME).click();
        }
    }

    public String viewCustomerAdviserType() {
        ((JavascriptExecutor) SharedDriver.getWebDriver()).executeScript("window.scrollTo(0, 2000);");
        return waitForExpectedElement(adviserContactInformation, DRIVER_WAIT_TIME).getText();

    }

    public String viewCustomerInvestorType() {
        ((JavascriptExecutor) SharedDriver.getWebDriver()).executeScript("window.scrollTo(0, 2500);");
        return waitForExpectedElement(investorContactInformation, DRIVER_WAIT_TIME).getText();
    }

    public void checkTeamName(List<Map<String, String>> data) {

        ((JavascriptExecutor) SharedDriver.getWebDriver()).executeScript("window.scrollBy(0,700)");
        List<WebElement> profiles = waitForExpectedElements(profileNames, DRIVER_WAIT_TIME);
        List<WebElement> profilesRoles = waitForExpectedElements(profileRoles, DRIVER_WAIT_TIME);
        for (int i = 0; i < 3; i++) {
            String name= data.get(i).get("Name");
            String role= data.get(i).get("Title");
            String profileName = profiles.get(i).getText();
            String profileRole = profilesRoles.get(i).getText();
            assertEquals(name,profileName);
            assertEquals(role,profileRole);
        }
    }
}
