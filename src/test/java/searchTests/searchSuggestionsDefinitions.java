package searchTests;

import searchTests.pageObjects.*;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

public class searchSuggestionsDefinitions {

    private final WebDriver driver = new ChromeDriver();
    private final Homepage homePage = new Homepage(driver);
    private final SearchResults searchResults = new SearchResults(driver);
    private final Suggestions suggestions = new Suggestions(driver);

    @Given("I am on the Ebay home page")
    public void iAmOnTheGoogleSearchPage() {
        driver.get("https://www.ebay.ca/");
    }

    @When("The search bar is empty")
    public void theSearchBarIsEmpty() {
        homePage.waitOnSearchBar(driver);
        homePage.clearSearch();
    }

    @When("I am about to search for {string}")
    public void i_am_about_to_search_for(String searchTerm) {
        homePage.waitOnSearchBar(driver);
        homePage.readySearch(searchTerm);
    }

    @Then("I expect there to be no search suggestions")
    public void i_expect_there_to_be_no_search_suggestions() {
        if(suggestions.areSuggVisible())
        {
            fail("Search suggestions are visible with nothing in the searchbar.");
        }
    }

    @Then("I expect there to be search suggestions")
    public void i_expect_there_to_be_search_suggestions() {
        if(!suggestions.areSuggVisible())
        {
            fail("Search suggestions are not visible with something in the searchbar.");
        };
    }




}
