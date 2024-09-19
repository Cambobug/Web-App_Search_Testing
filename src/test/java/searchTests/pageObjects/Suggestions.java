package searchTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class Suggestions {
    private final WebDriver driver;
    private final String suggestionDiv = "//div[@id='gAC']";
    private final String suggestionsUl = "//ul[@id='ui-id-1']";

    public Suggestions(WebDriver d) {
        driver = d;
    }

    // retrieves the list of suggestions after waiting for it to update
    private WebElement getSuggestionsList() {
        new WebDriverWait(driver, Duration.ofSeconds(10L)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(suggestionDiv + suggestionsUl)));
        WebElement ul = null;
        try{
            WebElement div = driver.findElement(By.xpath(suggestionDiv));
            ul = div.findElement(By.xpath(suggestionsUl));
        }
        catch(Exception e){
            fail(e.getMessage());
        }
        return ul;
    }

    //retrieves the individual suggestions out of the suggestion list
    private List<WebElement> getIndividualSuggestions(WebElement ul) {
        // gives eBay time to update suggestions
        new WebDriverWait(driver, Duration.ofSeconds(10L)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(suggestionsUl + "//li")));
        List<WebElement> liElements  = ul.findElements(By.tagName("li"));

        if(liElements.isEmpty()) {
            fail("No li elements found");
        }

        return liElements;
    }

    //checks that suggestions are visible or not
    public boolean areSuggVisible() {
        WebElement suggestionList = getSuggestionsList();

        try{
            //display is initially set to none when nothing has been types in search bar
            if(suggestionList.getAttribute("style").contains("display: none")) {
                return false; // suggestion list is not displayed
            }
            else {
                return true;
            }
        }
        catch(NullPointerException e){
            fail("ul is missing style attribute");
        }
        //default to false
        return false;
    }

    //returns a list of strings where each string is a search suggestion
    public List<String> parseSuggestions() {
        WebElement suggestionList = getSuggestionsList();
        List<WebElement> liElements = getIndividualSuggestions(suggestionList);
        List<String> textSuggestions = new ArrayList<String>();

        WebElement a;
        for(WebElement suggestion : liElements) {
            a = suggestion.findElement(By.tagName("a"));
            textSuggestions.add(a.getAttribute("aria-label"));
        }
        return textSuggestions;
    }

}
