import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private By searchInput = By.cssSelector("input[name='q']");
    private By searchResults = By.cssSelector(".quicksearch-results-wrapper li");
    private String searchResultsUrl = "https://magento.softwaretestingboard.com/catalogsearch/result/?q=T-shirt";


    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToSearchSection() {
    }

    public boolean searchForItemAndPressEnter(String itemName) {
        WebElement searchField = driver.findElement(searchInput);
        searchField.sendKeys(itemName);
        searchField.sendKeys(Keys.ENTER);
        return waitForSearchResultsPage();
    }

    private boolean waitForSearchResultsPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(searchResultsUrl));
    }


}
