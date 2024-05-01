import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductBrowsingPage extends BasePage {

    private WebElement sortButtonElement;
    private By sortButtonLocator = By.id("sorter");
    public ProductBrowsingPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnSortButton(int index) {

        Select sortButtonElement = new Select( driver.findElement(sortButtonLocator));
        sortButtonElement.selectByIndex(index);
    }


}
