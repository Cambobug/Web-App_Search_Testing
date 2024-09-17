package searchTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResults {
    private final WebDriver driver;

    private final String nullSearchHeaderClassname = "srp-save-null-search__heading";
    private final String errorSearchHeaderXPath = "//div[@class='s-error']";

    public SearchResults(WebDriver d) {
        driver = d;
    }

    public boolean verifySearchResults(String searchTitle, String actualTitle) {
        return actualTitle.startsWith(searchTitle);
    }

    public boolean verifyNullSearch() {
        List<WebElement> nullHeaders = driver.findElements(By.className(nullSearchHeaderClassname));
        return !nullHeaders.isEmpty(); // returns true if null header was found
    }

    public boolean verifyErrorSearch() {
        List<WebElement> errorHeaders = driver.findElements(By.xpath(errorSearchHeaderXPath));
        return !errorHeaders.isEmpty(); // returns true if the list is not empty (no
    }
}
