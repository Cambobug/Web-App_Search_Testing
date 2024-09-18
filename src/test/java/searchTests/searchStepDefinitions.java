package searchTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import searchTests.pageObjects.*;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class searchStepDefinitions {

    private final WebDriver driver = new ChromeDriver();
    private final Homepage homePage = new Homepage(driver);
    private final SearchResults searchResults = new SearchResults(driver);
    private final Suggestions suggestions = new Suggestions(driver);
    private final Pagination pagination = new Pagination(driver);

    /*
    ------------------------------------------
    -------------Multi-Use Tests--------------
    ------------------------------------------
     */

    @Given("I am on the Ebay home page")
    public void iAmOnTheEBaySearchPage() {
        driver.get("https://www.ebay.ca/");
    }

    @After()
    public void returnHome() {
        driver.quit();
    }

    /*
    ------------------------------------------
    -------------Searching Tests--------------
    ------------------------------------------
     */

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

    /*
    ------------------------------------------
    ---------Search Suggestion Cases----------
    ------------------------------------------
     */

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
        //eBay takes time to load search suggestions so we need to wait for them to populate
        String suggestionsUl = "//ul[@id='ui-id-1']";
        new WebDriverWait(driver, Duration.ofSeconds(3L)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(suggestionsUl + "//li")));

        if(!suggestions.areSuggVisible()) // if suggestions are not displayed -> fail
        {
            fail("Search suggestions are not visible with something in the searchbar.");
        }
    }

    @Then("I expect the search suggestions to contain the word {string}")
    public void expectedSearchSuggestions(String suggestion) {
        List<String> suggList = suggestions.parseSuggestions();

        if(suggList.isEmpty())
        {
            fail("There were no suggestions found.");
        }

        int relevant = 0, irrelevant = 0;

        for(String currSuggestion : suggList) {
            if (currSuggestion.contains(suggestion)) {
                relevant++;
            }
            else
            {
                irrelevant++;
            }
        }

        if(irrelevant > relevant)
        {
            fail("Majority of suggestions did not contain information relevant to search.");
        }
    }

    /*
    ------------------------------------------
    -------------Pagination Tests-------------
    ------------------------------------------
     */

    @Given("I have just searched for {string}")
    public void just_searched_for(String search) {
        iAmOnTheEBaySearchPage();
        iSearchFor(search);
    }

    @Given("I have just searched for {string} on the second page of results")
    public void searched_on_the_second_page_results(String search) {
        just_searched_for(search);
        pagination.clickPageNumber(2);
    }

    @When("I click the pagination forwards arrow")
    public void click_pagination_forwards_arrow() {
        pagination.clickNextArrow();
    }

    @When("I click the pagination backwards arrow")
    public void click_pagination_backwards_arrow() {
        pagination.clickPrevArrow();
    }

    @When("I click the final page number")
    public void click_final_page_number() {
        pagination.clickPageNumber(pagination.getNumberPages());
    }

    @Then("The page should have result pagination")
    public void does_pagination_exist() {
        if(pagination.getNumberPages() == -1)
        {
            fail("Search results do not have any pagination.");
        }
    }

    @Then("The page should move forward to page {string}")
    public void check_page_switch_next(String page) {
        int originalPageNum = Integer.parseInt(page);
        int currentPageNum = pagination.getCurrentPageNumber();

        //System.out.println("Org: " + originalPageNum + " - Cur:" + currentPageNum);

        if (currentPageNum != originalPageNum) {
            fail("Page did not switch to the next page upon clicking next arrow.");
        }
    }

    @Then("The page should move back to page {string}")
    public void check_page_switch_prior(String page) {
        int originalPageNum = Integer.parseInt(page);
        int currentPageNum = pagination.getCurrentPageNumber();

        //System.out.println("Org: " + originalPageNum + " - Cur:" + currentPageNum);

        if (currentPageNum != originalPageNum) {
            fail("Page did not switch to the prior page upon clicking previous arrow.");
        }
    }

    @Then("I should be on the first page of result pagination")
    public void check_on_first_page() {
        if(pagination.getCurrentPageNumber() != 1)
        {
            fail("Not on first page on result pagination after a search.");
        }
    }

    @Then("I should end up on the final page of results")
    public void check_on_final_page() {
        if(pagination.getCurrentPageNumber() != pagination.getNumberPages())
        {
            fail("Not on final page on result pagination after a search.");
        }
    }

}
