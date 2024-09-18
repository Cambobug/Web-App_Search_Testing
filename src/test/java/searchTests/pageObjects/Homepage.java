package searchTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;

public class Homepage {
    private final WebDriver driver;

    public Homepage(WebDriver d) {
        driver = d;
    }

    private WebElement getSearchBar() {
       return driver.findElement(By.xpath("//input[@id='gh-ac']"));
    }

    public String getPageTitle() {
        return driver.getTitle().toLowerCase();
    }

    public void clearSearch()
    {
        getSearchBar().clear();
    }

    public void readySearch(String search) {
        getSearchBar().click();
        getSearchBar().sendKeys(search);
    }

    public void sendSearch(String search) {
        readySearch(search);
        getSearchBar().submit();
    }

    public void waitOnSearchBar(WebDriver d){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(5L)).until(ExpectedConditions.elementToBeClickable(getSearchBar()));
        }
        catch (TimeoutException e){
            fail("The system timed out while waiting for the search bar to load.");
        }
    }


}
