package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.PageFactory.initElements;

public class TeamPage extends BasePage {
    static final long DRIVER_WAIT_TIME = 30;
    final By modalSelectorAdviser = cssSelector("[value='adviser']");
    final By modalSelectorInvestor = cssSelector("[value='investor']");
    final By confirmAdviser = cssSelector(".adviser-confirm-button");
    final By confirmInvester = cssSelector(".investor-confirm-button");
    final By adviserContactInformation = cssSelector(".wp-block-deepsea-personalisationnested.adviser h2");
    final By investorContactInformation = cssSelector(".wp-block-deepsea-personalisationnested.investor h2");
    final By contactSection = cssSelector(".section-wrapper");
    final By profileNames = cssSelector(".profileCard-name");
    final By profileRoles = cssSelector(".profileCard-title");
    final By findLocalContact = cssSelector(".wp-block-button__link");
    final By aboutUs = cssSelector("#menu-item-3033 a");
    final By aboutUsText = cssSelector(".stat-body");
    final By aboutUsInfo = cssSelector(".stat-foot");


    public void openTeamPage() {
        driver.navigate().to("https://octopusinvestments.com/about-us/leadership-team/");
    }

    public void clickModalSelectorPopup(String customerType) {
        switch (customerType.toLowerCase()) {
            case "adviser": {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(modalSelectorAdviser, DRIVER_WAIT_TIME));
                waitForExpectedElement(modalSelectorAdviser, DRIVER_WAIT_TIME).click();
//                waitForExpectedElement(confirmAdviser, DRIVER_WAIT_TIME).click();
                break;
            }
            case "investor":
                waitForExpectedElement(modalSelectorInvestor, DRIVER_WAIT_TIME).click();
//                waitForExpectedElement(confirmInvester, DRIVER_WAIT_TIME).click();
                break;
            default:
                System.out.println("unknown");
        }
    }

    public void clickModalConfirm(String customerType) throws InterruptedException {
        if (customerType.equalsIgnoreCase("investor")) {
            waitForExpectedElement(confirmInvester, DRIVER_WAIT_TIME).click();
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(modalSelectorAdviser, DRIVER_WAIT_TIME));
            waitForExpectedElement(confirmAdviser, DRIVER_WAIT_TIME).click();
        }
    }

    public String viewCustomerAdviserType() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2000);");
        return waitForExpectedElement(adviserContactInformation, DRIVER_WAIT_TIME).getText();

    }

    public String viewCustomerInvestorType() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2500);");
        return waitForExpectedElement(investorContactInformation, DRIVER_WAIT_TIME).getText();
    }

    public List<String> checkTeamName() {

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");
        List<WebElement> profiles = waitForExpectedElements(profileNames, DRIVER_WAIT_TIME);
        List<WebElement> profilesRoles = waitForExpectedElements(profileRoles, DRIVER_WAIT_TIME);
        List<String> profileName = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            profileName.add(profiles.get(i).getText().concat("-").concat(profilesRoles.get(i).getText()));
        }
        return profileName;
    }

    public void clickLocalSearch() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,0)");
        Thread.sleep(7000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2500)");
        waitForExpectedElement(findLocalContact, DRIVER_WAIT_TIME).click();
    }

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
