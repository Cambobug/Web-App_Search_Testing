package searchTests.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    private final WebDriver driver;

    private final String paginationDiv = "//nav[@class='pagination']";
    private final String paginationPrevArrow = "//a[@type='previous']";
    private final String paginationNextArrow = "//a[@type='next']";
    private final String paginationNumList = "//ol[@class='pagination__items']";

    public Pagination(WebDriver d) {
        driver = d;
    }

    private WebElement getPaginationDiv() {
        return driver.findElement(By.xpath(paginationDiv));
    }

    private WebElement getPaginationPrevArrow() {
        return getPaginationDiv().findElement(By.xpath(paginationPrevArrow));
    }

    private WebElement getPaginationNextArrow() {
        return getPaginationDiv().findElement(By.xpath(paginationNextArrow));
    }

    //gets the list of individual pages
    private List<WebElement> getPaginationNumList() {
        WebElement ol = getPaginationDiv().findElement(By.xpath(paginationNumList));
        List<WebElement> liElements =  ol.findElements(By.tagName("li"));

        // there is a hidden element in each list that is non-interactable
        liElements.removeIf(liElement -> !liElement.isDisplayed());

        return liElements;
    }

    public void clickPrevArrow()
    {
        getPaginationPrevArrow().click();
    }

    public void clickNextArrow()
    {
        getPaginationNextArrow().click();
    }

    //returns the number of pagination pages
    public int getNumberPages() {
        if(getPaginationNumList().isEmpty())
        {
            return -1;
        }
        return getPaginationNumList().size();
    }

    //gets the current page number the user is on
    public int getCurrentPageNumber() {
        List<WebElement> list = getPaginationNumList();
        WebElement a;
        int currentPageNumber = 1;

        for(WebElement listElement : list)
        {
            a = listElement.findElement(By.tagName("a"));
            try{
                if(a.getAttribute("aria-current").equals("page"))
                {
                    return Integer.parseInt(a.getText());
                }
            }
            catch(NullPointerException e)
            {
                currentPageNumber++;
            }
        }
        return -1;
    }

    //clicks on a specific page in the search pagination
    public void clickPageNumber(int pageNumber)
    {
        List<WebElement> list = getPaginationNumList();
        list.get(pageNumber - 1).click();
    }
}
