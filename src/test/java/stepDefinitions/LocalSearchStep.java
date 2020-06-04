package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pageObjects.LocalSearchPage;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LocalSearchStep {

    public final LocalSearchPage localSearchPage;

    public LocalSearchStep(LocalSearchPage localSearchPage) {
        this.localSearchPage = localSearchPage;
    }


    @And("^I search postcode \"([^\"]*)\"$")
    public void iSearchPostcode(String postcode) throws Throwable {
      localSearchPage.findLocalContact(postcode);
    }

    @Then("^I search postcode and see results shown$")
    public void iSearchPostcodeAndSeeResultsShown(DataTable table) throws InterruptedException {
        List<List<String>> data = table.raw();
        String postcode = data.get(0).get(0);
        String postcodeSearchResult = data.get(0).get(1);

        System.out.println(postcode);
        System.out.println(postcodeSearchResult);
//        String postcode2 = data.get(1).get(0);
//        String postcodeSearchResult2 = data.get(0).get(1);

//        assertEquals("invalid input search",localSearchPage.searchPostCode(postcode),postcodeSearchResult );
        assertEquals("valid input search",localSearchPage.searchPostCode(postcode),postcodeSearchResult );
    }

    @Then("^I see search result as 'Displaying results for \"([^\"]*)\"'$")
    public void iSeeSearchResultAsDisplayingResultsFor(String results) throws Throwable {
        assertEquals("text not found", localSearchPage.searchResults(), results);
        throw new PendingException();
    }
}
