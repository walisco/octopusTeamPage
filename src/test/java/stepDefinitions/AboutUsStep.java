package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import pageObjects.AboutUsPage;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AboutUsStep {
    public final AboutUsPage aboutUsPage;

    public AboutUsStep(AboutUsPage aboutUsPage) {
        this.aboutUsPage = aboutUsPage;
    }

    @And("^I click About us I can see$")
    public void iClickAboutUsICanSee(DataTable table) {
        List<List<String>> data = table.raw();
        String aboutUsYr = data.get(0).get(0);
        String aboutUsFunds = data.get(0).get(1);

        aboutUsPage.aboutUsLink();

        assertEquals(aboutUsYr, aboutUsPage.aboutUsLinkYr());
        assertEquals(aboutUsFunds, aboutUsPage.aboutUsLinkFunds());
    }
}
