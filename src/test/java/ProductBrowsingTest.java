import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProductBrowsingTest {

    WebDriver driver;
    private ProductBrowsingPage productBrowsingPage;
    private LogINPage logINPage;

    @BeforeTest
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        productBrowsingPage = new ProductBrowsingPage(driver);
        driver.get("https://magento.softwaretestingboard.com/collections/yoga-new.html");
        logINPage = new LogINPage(driver);
        logINPage.clickOnSignINButton();
        logINPage.enterEmail("MohamedAli@hotmail.com");
        logINPage.enterPassword("M@hmed123");
        logINPage.clickOnSignINSubmitButton();
        //driver.get("https://magento.softwaretestingboard.com/checkout/#shipping");
    }

    @Test
    public void validPositionSortButton() {
        productBrowsingPage.clickOnSortButton(0);
        String expectedUrl = "https://magento.softwaretestingboard.com/collections/yoga-new.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void validProductNameSortButton() {
        productBrowsingPage.clickOnSortButton(1);
        String expectedUrl = "https://magento.softwaretestingboard.com/collections/yoga-new.html?product_list_order=name";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void validPriceSortButton() {
        productBrowsingPage.clickOnSortButton(2);
        String expectedUrl = "https://magento.softwaretestingboard.com/collections/yoga-new.html?product_list_order=price";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @AfterTest
    public void  quitDriver(){

        driver.quit();
    }


}



