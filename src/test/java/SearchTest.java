import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchTest {
    private WebDriver driver;
    private SearchPage searchPage;

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        searchPage = new SearchPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @Test(description = "Verify that entering a valid item in the search field successfully navigates to the search results page")
    public void testSearchResultsNavigation() {
        searchPage.navigateToSearchSection();
        boolean isSearchResultsPageLoaded = searchPage.searchForItemAndPressEnter("T-shirt");
        Assert.assertTrue(isSearchResultsPageLoaded, "The page did not navigate to the search results page successfully.");
    }


    @AfterClass
    public void tearDown() {

        driver.quit();
    }
}
