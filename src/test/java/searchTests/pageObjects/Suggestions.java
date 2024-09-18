package searchTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class Suggestions {
    private final WebDriver driver;
    private final String suggestionUlElement = "//div[@id='gAC']//ul";
    private final String suggestionsUlElement = "//li/a";

    public Suggestions(WebDriver d) {
        driver = d;
    }

    private List<WebElement> getSuggestions() {
        WebElement ul = driver.findElement(By.xpath(suggestionUlElement));
        List<WebElement> lists = ul.findElements(By.tagName("a"));
        /*
        try{
            if(lists.isEmpty()) {
                fail("No suggestions found");
            }
        }
        catch(Exception e){
            fail(e.getMessage());
        }
        */
        return lists;
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

        List<String> suggestions = new ArrayList<String>();

        for(WebElement suggestion : suggestionList) {
            System.out.println(suggestion.getAttribute("aria-label"));
            suggestions.add(suggestion.getAttribute("aria-label"));
        }

        return suggestions;
    }

}
