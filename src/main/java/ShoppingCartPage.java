import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPage {
    private WebDriver driver;
    private By sizeDropdownLocator = By.id("option-label-size-143-item-167");
    private By colorButtonLocator = By.id("option-label-color-93-item-59");
    private By addToCartButtonLocator = By.xpath("//button[@title='Add to Cart']");
    private By cartItemsLocator = By.cssSelector(".minicart-items li");
    private By cartItemCountLocator = By.cssSelector(".counter-number");
    private By productNameLocator = By.cssSelector(".product-name");
    private By productPriceLocator = By.cssSelector(".product-price");




    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToProductPage(String productUrl) {
        driver.get(productUrl);
    }

    public void selectProductOptionsAndAddToCart(String size, String color) {
        selectSize(size);
        selectColor(color);
        clickAddToCartButton();
    }

    private void selectSize(String size) {
        WebElement sizeDropdown = driver.findElement(sizeDropdownLocator);
        sizeDropdown.click();
    }

    private void selectColor(String color) {
        WebElement colorButton = driver.findElement(colorButtonLocator);
        colorButton.click();
    }

    private void clickAddToCartButton() {
        WebElement addToCartButton = driver.findElement(addToCartButtonLocator);
        addToCartButton.click();
    }
    public boolean isProductAddedToCart() {
        List<WebElement> cartItems = driver.findElements(cartItemsLocator);
        return !cartItems.isEmpty();
    }

    public int getCartItemCount() {
        WebElement cartItemCountElement = driver.findElement(cartItemCountLocator);
        return Integer.parseInt(cartItemCountElement.getText());
    }






}
