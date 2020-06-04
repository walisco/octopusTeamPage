package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
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


    public void openTeamPage() {
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://octopusinvestments.com/about-us/leadership-team/");
//        return initElements(driver, TeamPage.class);
    }
    public void clickModalSelectorPopup(String customerType) {
        switch (customerType.toLowerCase()) {
            case "adviser":{ waitForExpectedElement(modalSelectorAdviser,DRIVER_WAIT_TIME).click();
                waitForExpectedElement(confirmAdviser, DRIVER_WAIT_TIME).click();
                break;}
            case "investor" :waitForExpectedElement(modalSelectorInvestor,DRIVER_WAIT_TIME).click();
                waitForExpectedElement(confirmInvester, DRIVER_WAIT_TIME).click();
                break;
            default: System.out.println("unknown");
        }
//        if(customerType == "Adviser") {
//            waitForExpectedElement(modalSelectorAdviser,DRIVER_WAIT_TIME).click();
//            waitForExpectedElement(confirmAdviser, DRIVER_WAIT_TIME).click();
//        } else {
//            waitForExpectedElement(modalSelectorInvestor,DRIVER_WAIT_TIME).click();
//            waitForExpectedElement(confirmInvester, DRIVER_WAIT_TIME).click();
//        }
    }

    public String viewCustomerAdviserType() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 2500);");
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
        for(int i = 0; i<4; i++ ){
            profileName.add(profiles.get(i).getText().concat("-").concat(profilesRoles.get(i).getText()));
        }
        return profileName;
    }

    public void clickLocalSearch() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2500)");
        waitForExpectedElement(findLocalContact, DRIVER_WAIT_TIME).click();
    }

}
