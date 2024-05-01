import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogINPage extends BasePage {

    private WebElement signINButtonElement;
    private By signINButton = By.cssSelector("li.authorization-link > a");
    private WebElement emailFieldElement;
    private By emailFieldLocator = By.id("email");
    private WebElement passwordFieldElement;
    private By passwordFieldLocator = By.id("pass");
    private WebElement signINSubmitButtonElement;
    private By signINSubmitButtonLocator = By.id("send2");
    private WebElement forgetPasswordButtonElement;
    private By forgetPasswordButtonLocator = By.linkText("Forgot Your Password?");
    private WebElement resetEmailFieldElement;
    private By resetEmailFieldLocator = By.id("email_address");
    private WebElement CreateAnAccountButtonElement;
    private By CreateAnAccountButtonLocator = By.linkText("Create an Account");


    public LogINPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSignINButton() {
        signINButtonElement = driver.findElement(signINButton);
        clicking(signINButtonElement);
    }

    public void enterEmail(String email) {
        emailFieldElement = driver.findElement(emailFieldLocator);
        sendText(emailFieldElement, email);
    }

    public void enterPassword(String password) {
        passwordFieldElement = driver.findElement(passwordFieldLocator);
        sendText(passwordFieldElement, password);
    }

    public void clickOnSignINSubmitButton() {
        signINSubmitButtonElement = driver.findElement(signINSubmitButtonLocator);
        clicking(signINSubmitButtonElement);
    }

    public void clearAllFields() {
        driver.findElement(passwordFieldLocator).clear();
        driver.findElement(emailFieldLocator).clear();

    }

    public void clickOnForgetPasswordButton() {
        forgetPasswordButtonElement = driver.findElement(forgetPasswordButtonLocator);
        clicking(forgetPasswordButtonElement);
    }

    public void enterResetEmail(String email) {
        resetEmailFieldElement = driver.findElement(resetEmailFieldLocator);
        sendText(resetEmailFieldElement, email);
    }


    public void clickOnCreateAnAccountButton() {
        CreateAnAccountButtonElement = driver.findElement(CreateAnAccountButtonLocator);
        clicking(CreateAnAccountButtonElement);
    }




}
