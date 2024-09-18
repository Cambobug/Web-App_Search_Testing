package searchTests;

import searchTests.pageObjects.*;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class searchStepDefinitions {

    private final WebDriver driver = new ChromeDriver();
    private final Homepage homePage = new Homepage(driver);
    private final SearchResults searchResults = new SearchResults(driver);
    private final Suggestions suggestions = new Suggestions(driver);

    //Given and After Cases

    @Given("I am on the Ebay home page")
    public void iAmOnTheGoogleSearchPage() {
        driver.get("https://www.ebay.ca/");
    }

    @After()
    public void returnHome() {
        driver.quit();
    }

    //Searching cases

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

    // Search Suggestion Cases

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
        if(suggestions.areSuggVisible()) // if suggestions are displayed -> fail
        {
            fail("Search suggestions are visible with nothing in the searchbar.");
        }
    }

    @Then("I expect there to be search suggestions")
    public void i_expect_there_to_be_search_suggestions() {
        if(!suggestions.areSuggVisible()) // if suggestions are not displayed -> fail
        {
            fail("Search suggestions are not visible with something in the searchbar.");
        }
    }

    @Then("I expect the search suggestions to contain the word {string}")
    public void expectedSearchSuggestions(String suggestion) {
        homePage.waitOnSearchBar(driver);
        List<String> suggList = suggestions.parseSuggestions();

        if(suggList.isEmpty())
        {
            fail("There were no suggestions found.");
        }

        for(String currSuggestion : suggList) {
            System.out.println(currSuggestion);
            if (!currSuggestion.contains(suggestion)) {
                fail("Suggestion did not contain information relevant to search: " + currSuggestion);
            }
        }
    }

    //Pagination Tests

}
