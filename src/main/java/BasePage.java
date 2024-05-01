import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    public  BasePage(WebDriver driver) {
        this.driver = driver;
    }

public static void clicking(WebElement element) {
        element.click();
}

    public static void sendText(WebElement element , String text) {
        element.sendKeys(text);
    }


}
