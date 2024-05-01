import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAccountPage extends BasePage {

    // Locators for elements on the create account page
    private By firstNameField = By.id("firstname");
    private By lastNameField = By.id("lastname");
    private By emailField = By.id("email_address");
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("password-confirmation");
    private By createAccountButton = By.cssSelector(".action.submit.primary");

    // Error message locators
    private By errorMessageFirstName = By.id("firstname-error");
    private By errorMessageLastName = By.id("lastname-error");
    private By errorMessageEmail = By.id("email_address-error");
    private By errorMessagePassword = By.id("password-error");
    private By errorMessageConfirmPassword = By.id("password-confirmation-error");

    private String MyAccountPageUrl = "https://magento.softwaretestingboard.com/customer/account/";


    // Constructor
    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }



    // Methods to interact with elements on the create account page

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }

    public void clickCreateAccountButton() {
        driver.findElement(createAccountButton).click();
    }

    // Method to verify if all required fields are marked with an asterisk
    public boolean areRequiredFieldsMarked() {
        boolean isFirstNameMarked = driver.findElement(firstNameField).getAttribute("required") != null;
        boolean isLastNameMarked = driver.findElement(lastNameField).getAttribute("required") != null;
        boolean isEmailMarked = driver.findElement(emailField).getAttribute("required") != null;
        boolean isPasswordMarked = driver.findElement(passwordField).getAttribute("required") != null;
        boolean isConfirmPasswordMarked = driver.findElement(confirmPasswordField).getAttribute("required") != null;

        return isFirstNameMarked && isLastNameMarked && isEmailMarked && isPasswordMarked && isConfirmPasswordMarked;
    }
    // Method to verify if an error message is displayed for each required field left blank
    public boolean areErrorMessagesDisplayed() {
        boolean isFirstNameErrorDisplayed = driver.findElement(errorMessageFirstName).isDisplayed();
        boolean isLastNameErrorDisplayed = driver.findElement(errorMessageLastName).isDisplayed();
        boolean isEmailErrorDisplayed = driver.findElement(errorMessageEmail).isDisplayed();
        boolean isPasswordErrorDisplayed = driver.findElement(errorMessagePassword).isDisplayed();
        boolean isConfirmPasswordErrorDisplayed = driver.findElement(errorMessageConfirmPassword).isDisplayed();

        return isFirstNameErrorDisplayed && isLastNameErrorDisplayed && isEmailErrorDisplayed && isPasswordErrorDisplayed && isConfirmPasswordErrorDisplayed;
    }
    // Method to verify if the email field only accepts valid email addresses
    public boolean isEmailFieldAcceptingValidEmails(String validEmail) {
        driver.findElement(emailField).sendKeys(validEmail);
        driver.findElement(createAccountButton).click();
        return !driver.findElement(errorMessageEmail).isDisplayed();
    }
    // Method to verify if the password strength meter accurately reflects the strength of the entered password
    public boolean isPasswordStrengthMeterAccurate(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return !driver.findElement(errorMessagePassword).isDisplayed();
    }
    // Method to verify if error message appears when entering different password in the confirm password field
    public boolean isErrorMessageDisplayedForDifferentPassword(String password, String confirmPassword) {
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
        driver.findElement(createAccountButton).click();
        return driver.findElement(errorMessageConfirmPassword).isDisplayed();
    }
    // Method to verify successful registration and redirection to homepage
    public boolean registerUser(String firstName, String lastName, String email, String password) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        driver.findElement(createAccountButton).click();

        // Wait implicitly for redirection to complete
        waitForRedirectionToMyAccountPage();

        // Check if redirected to homepage
        return driver.getCurrentUrl().equals(MyAccountPageUrl);
    }

    // Method to implicitly wait until redirection to homepage occurs
    private void waitForRedirectionToMyAccountPage() {
        long startTime = System.currentTimeMillis();
        while (!driver.getCurrentUrl().equals(MyAccountPageUrl)) {
            if (System.currentTimeMillis() - startTime >= 10000) { // Timeout after 10 seconds
                throw new RuntimeException("Timed out waiting for redirection to homepage.");
            }
            try {
                Thread.sleep(5000); // Check every 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearAllFields() {
        driver.findElement(firstNameField).clear();
        driver.findElement(lastNameField).clear();
        driver.findElement(emailField).clear();
        driver.findElement(passwordField).clear();
        driver.findElement(confirmPasswordField).clear();
    }
}
