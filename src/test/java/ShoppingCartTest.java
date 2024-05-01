import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ShoppingCartTest {
    private WebDriver driver;
    private ShoppingCartPage shoppingCartPage;

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        shoppingCartPage = new ShoppingCartPage(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://magento.softwaretestingboard.com/checkout/cart/");
    }

    @Test(description = "Verify adding a product to the shopping cart")
    public void testAddProductToCart() {
        shoppingCartPage.navigateToProductPage("https://magento.softwaretestingboard.com/catalogsearch/result/?q=Breathe-Easy+Tank");
        shoppingCartPage.selectProductOptionsAndAddToCart("S", "White");
        Assert.assertTrue(shoppingCartPage.isProductAddedToCart(), "Product was not added to the cart successfully.");
        Assert.assertEquals(shoppingCartPage.getCartItemCount(), 1, "Cart item count is incorrect.");
    }


    @AfterClass
    public void tearDown() {

        driver.quit();
    }
}
