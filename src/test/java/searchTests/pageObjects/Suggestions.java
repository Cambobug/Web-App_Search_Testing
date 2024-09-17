package searchTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class Suggestions {
    private final WebDriver driver;

    private final String suggestionSet = "//div[@class='gAC']/ul[@id='ui-id-1']/li";

    public Suggestions(WebDriver d) {
        driver = d;
    }

    private List<WebElement> getSuggestions() {
        return driver.findElements(By.xpath(suggestionSet));
    }

    public boolean areSuggVisible() {
        List<WebElement> suggestionList = getSuggestions();

        for(WebElement suggestion : suggestionList) {
            if(!suggestion.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public List<String> parseSuggestions() {
        List<WebElement> suggestionList = getSuggestions();
        List<String> suggestions = new ArrayList<String>();

        for(WebElement suggestion : suggestionList) {
            suggestions.add(suggestion.getText());
        }

        return suggestions;
    }

}
