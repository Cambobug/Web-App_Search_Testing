package searchTests;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class searchStepDefinitions {

    private final WebDriver driver = new ChromeDriver();

    @Given("I am on the Ebay search page")
    public void iAmOnTheGoogleSearchPage() {
        driver.get("https://www.ebay.ca/");
    }

    @When("I search for {string}")
    public void iSearchFor(String search) {
        WebElement element = driver.findElement(By.xpath("//input[@id='gh-ac']")); // find search bar using input id
        element.sendKeys(search);
        element.submit();
    }

    @Then("the page title should start with {string}")
    public void thePageTitleShouldStartWith(String title) {
        new WebDriverWait(driver, Duration.ofSeconds(10L)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {

                //System.out.println(d.getTitle()); REMOVE THIS

                return d.getTitle().toLowerCase().startsWith(title);
            }
        });
    }

    @Then ("the page should have no matches")
    public void thePageShouldHaveNoMatches() {
        new WebDriverWait(driver, Duration.ofSeconds(10L)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return !d.findElements(By.className("srp-save-null-search__heading")).isEmpty(); // check that the null search header is found
            }
        });
    }

    @Then ("the page should reject your search")
    public void thePageShouldRejectYourSearch() {
        new WebDriverWait(driver, Duration.ofSeconds(10L)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return !d.findElements(By.xpath("//div[@class='s-error']")).isEmpty(); // check that the error search header is found
            }
        });
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }
}
