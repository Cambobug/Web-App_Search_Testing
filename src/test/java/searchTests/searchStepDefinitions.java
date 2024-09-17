package searchTests;

import searchTests.pageObjects.*;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.fail;

public class searchStepDefinitions {

    private final WebDriver driver = new ChromeDriver();
    private final Homepage homePage = new Homepage(driver);
    private final SearchResults searchResults = new SearchResults(driver);

    @Given("I am on the Ebay home page")
    public void iAmOnTheGoogleSearchPage() {
        driver.get("https://www.ebay.ca/");
    }

    @When("I search for {string}")
    public void iSearchFor(String search) {
        homePage.waitOnSearchBar(driver);
        homePage.sendSearch(search);
    }

    @Then("the page title should start with {string}")
    public void thePageTitleShouldStartWith(String search) {
        homePage.waitOnSearchBar(driver);
        if(!searchResults.verifySearchResults(search, homePage.getPageTitle()))
        {
            fail("The page title did not start with " + search + ".");
        }
    }

    @Then ("the page should have no matches")
    public void thePageShouldHaveNoMatches() {
        homePage.waitOnSearchBar(driver);
        if(!searchResults.verifyNullSearch())
        {
            fail("The page should have no matches but instead has matches.");
        }
    }

    @Then ("the page should reject your search")
    public void thePageShouldRejectYourSearch() {
        homePage.waitOnSearchBar(driver);
        if(!searchResults.verifyErrorSearch()) {
            fail("The page should show a search error but instead has matches.");
        }
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
