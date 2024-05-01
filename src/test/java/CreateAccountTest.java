import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateAccountTest {
    private WebDriver driver;
    private CreateAccountPage createAccountPage;

    @BeforeClass
    public void setUp() {

        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        createAccountPage = new CreateAccountPage(driver);

    }

  //  @Test(description = "Verify that all required fields are marked with an asterisk and error messages are displayed when left blank")
  //  public void testRequiredFieldsAndErrorMessages() {
     //   Assert.assertTrue(createAccountPage.areRequiredFieldsMarked(), "Not all required fields are marked with an asterisk.");
      //  createAccountPage.clickCreateAccountButton();
      //  Assert.assertTrue(createAccountPage.areErrorMessagesDisplayed(), "Error messages are not displayed for required fields left blank.");
       // createAccountPage.clearAllFields();
  //  }

    @Test(description = "Verify that the email field only accepts valid email addresses")
    public void testEmailFieldValidation() {
        // Enter valid data for other fields
        createAccountPage.enterFirstName("Mohamed");
        createAccountPage.enterLastName("Ali");
        createAccountPage.enterPassword("M@hmed123");
        createAccountPage.enterConfirmPassword("M@hmed123");
        // Enter invalid email address
        String invalidEmail = "MohamedAli"; // Invalid email address
        boolean isInvalidEmailAccepted = createAccountPage.isEmailFieldAcceptingValidEmails(invalidEmail);
        // Verify that an error message appears for invalid email and form isn't submitted
        Assert.assertFalse(isInvalidEmailAccepted, "The email field accepted an invalid email address.");
        createAccountPage.clearAllFields();
    }
    @Test(description = "Verify that the password strength meter accurately reflects the strength of the entered password")
    public void testPasswordStrengthMeter() {
        // Enter valid data for other fields
        createAccountPage.enterFirstName("Mohamed");
        createAccountPage.enterLastName("Ali");
        createAccountPage.enterEmail("MohamedAli@hotmail.com");
        // Enter weak password
        String weakPassword = "Mhmd"; // Weak password
        boolean isPasswordStrengthAccurate = createAccountPage.isPasswordStrengthMeterAccurate(weakPassword);
        // Verify that an error message appears for weak password
        Assert.assertFalse(isPasswordStrengthAccurate, "The password strength meter did not accurately reflect the strength of the entered password.");
        createAccountPage.clearAllFields();
    }
    @Test(description = "Verify that error message appears when entering different password in the confirm password field")
    public void testErrorMessageForDifferentPassword() {
        // Enter valid data for other fields
        createAccountPage.enterFirstName("Mohamed");
        createAccountPage.enterLastName("Ali");
        createAccountPage.enterEmail("MohamedAli@hotmail.com");
        createAccountPage.enterPassword("M@hmed123");
        // Enter different password in confirm password field
        String confirmPassword = "M@hmed12"; // Different password
        boolean isErrorMessageDisplayed = createAccountPage.isErrorMessageDisplayedForDifferentPassword("M@hmed123", confirmPassword);
        // Verify that an error message appears for different password
        Assert.assertTrue(isErrorMessageDisplayed, "No error message appeared when entering different password in the confirm password field.");
        createAccountPage.clearAllFields();
    }
    @Test(description = "Verify that the user is successfully registered and redirected to the MyAccountPage")
    public void testSuccessfulRegistrationAndRedirection() {
        // Enter valid data for all fields
        String firstName = "Mohamed";
        String lastName = "Ali";
        String email = "MohamedAlii5678@hotmail.com";
        String password = "M@hmed123";
        // Register user
        boolean isRedirectedToMyAccountPage = createAccountPage.registerUser(firstName, lastName, email, password);
        // Verify successful registration and redirection
        Assert.assertTrue(isRedirectedToMyAccountPage, "User registration was not successful or redirection to MyAccountPage failed.");
        createAccountPage.clearAllFields();
    }

    @AfterClass
    public void cleanup() {


        driver.quit();

    }


}
