package searchTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    private List<WebElement> getSuggestions() {
        List<WebElement> liElements = null;
        try{
            WebElement div = driver.findElement(By.xpath(suggestionDiv));
            WebElement ul = div.findElement(By.xpath(suggestionsUl));
            liElements = ul.findElements(By.tagName("li"));
            if(liElements.isEmpty()) {
                fail("No suggestions found");
            }
        }
        catch(Exception e){
            fail(e.getMessage());
        }
        return liElements;
    }

    public boolean areSuggVisible() {
        List<WebElement> suggestionList = getSuggestions();

        for(WebElement suggestion : suggestionList) {
            if(!suggestion.isDisplayed()) { // if a suggestion is found that is not displayed
                return false; // suggestions are not displayed
            }
        }
        return true; //suggestions are displayed
    }

    public List<String> parseSuggestions() {
        List<WebElement> suggestionList = getSuggestions();
        System.out.println(suggestionList.size());

        List<String> textSuggestions = new ArrayList<String>();

        for(WebElement suggestion : suggestionList) {
            System.out.println(suggestion.getAttribute("aria-label"));
            textSuggestions.add(suggestion.getAttribute("aria-label"));
        }

        return textSuggestions;
    }

}
