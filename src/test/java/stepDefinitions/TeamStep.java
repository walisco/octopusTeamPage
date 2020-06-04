package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import pageObjects.TeamPage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TeamStep {
    public final TeamPage teamPage;

    public TeamStep(TeamPage teamPage) {
        this.teamPage = teamPage;
    }


    @Given("^I am on the investment page$")
    public void i_am_on_the_investment_page() {
        teamPage.openTeamPage();
    }


    @When("^I click on the modal popup as an \"([^\"]*)\" and confirm selection$")
    public void iClickOnTheModalPopupAsAnAndConfirmSelection(String customerType) throws Throwable {
        teamPage.clickModalSelectorPopup(customerType);
    }


    @Given("^I click on the modal popup as an \"([^\"]*)\"$")
    public void iClickOnTheModalPopupAsAn(String customerType) throws Throwable {
        teamPage.clickModalSelectorPopup(customerType);
    }

    @Then("^I can see the following chief officers:$")
    public void iCanSeeTheFollowingChiefOfficers(DataTable profiles) {
        List<List<String>> data = profiles.raw();

        assertTrue(data.get(0).get(0) + " not matched to actual information", teamPage.checkTeamName().contains(data.get(0).get(0).concat("-").concat(data.get(0).get(1))));
        assertTrue(data.get(1).get(0) + " not matched to actual information", teamPage.checkTeamName().contains(data.get(1).get(0).concat("-").concat(data.get(1).get(1))));
        assertTrue(data.get(2).get(0) + " not matched to actual information", teamPage.checkTeamName().contains(data.get(2).get(0).concat("-").concat(data.get(2).get(1))));
    }

    @Then("^I can see text for Investor \"([^\"]*)\" on page$")
    public void iCanSeeTextForInvestorOnPage(String contactLink) throws Throwable {
        assertEquals(teamPage.viewCustomerInvestorType(), contactLink);
    }

    @When("^I can see text for Adviser \"([^\"]*)\" on page$")
    public void iCanSeeTextForAdviserOnPage(String contactLink) throws Throwable {
        assertEquals(teamPage.viewCustomerAdviserType(), contactLink);
    }

    @And("^I confirm \"([^\"]*)\" selection$")
    public void iConfirmSelection(String customerType) throws Throwable {
        teamPage.clickModalConfirm(customerType);
    }

}
