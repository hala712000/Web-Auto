import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
public class LogINTest {
    private LogINPage logINPage;
    WebDriver driver;
    @BeforeTest
    public void setup()
    {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://magento.softwaretestingboard.com/collections/yoga-new.html");
        logINPage = new LogINPage(driver);
        logINPage.clickOnSignINButton();
    }
    @BeforeMethod
    public void cleanup() {
        logINPage.clearAllFields();
    }

    @Test
    public void clickOnSignin(){
        logINPage.clickOnSignINButton();
    }
    @Test(priority = 10)
    public void vaildLoginTestCase(){
        logINPage.enterEmail("MohamedAli@hotmail.com");
        logINPage.enterPassword("M@hmed123");
        logINPage.clickOnSignINSubmitButton();
        String expectedUrl = "https://magento.softwaretestingboard.com/collections/yoga-new.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

    }

    @Test
    public void invaildPassword(){
        logINPage.enterEmail("MohamedAli@hotmail.com");
        logINPage.enterPassword("123456");
        logINPage.clickOnSignINSubmitButton();
        String expectedErrorMessage = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualErrorMessage = driver.findElement(By.cssSelector(".message-error.error.message")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);


    }

    @Test
    public void invaildEmail(){
        logINPage.enterEmail("MohamedAli@ho.c");
        logINPage.enterPassword("M@hmed123");
        logINPage.clickOnSignINSubmitButton();
        String expectedErrorMessage = "Please enter a valid email address (Ex: johndoe@domain.com).";
        String actualErrorMessage = driver.findElement(By.xpath("//*[@id=\"email-error\"]")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }
    @Test
    public void invalidNewEmailTestCase(){
        logINPage.enterEmail("manar123@gmail.com");
        logINPage.enterPassword("5555478");
        logINPage.clickOnSignINSubmitButton();
        String expectedErrorMessage = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualErrorMessage = driver.findElement(By.cssSelector(".message-error.error.message")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void invalidEmptyEmailTestCase(){
        logINPage.enterEmail("");
        logINPage.enterPassword("5555478");
        logINPage.clickOnSignINSubmitButton();
        String expectedErrorMessage = "This is a required field.";
        String actualErrorMessage = logINPage.driver.findElement(By.id("email-error")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void invalidEmptyPasswordTestCase(){
        logINPage.enterEmail("MohamedAli@hotmail.com");
        logINPage.enterPassword("");
        logINPage.clickOnSignINSubmitButton();
        String expectedErrorMessage = "This is a required field.";
        String actualErrorMessage = driver.findElement(By.id("pass-error")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }


    @Test
    public void validResetForgetPassword(){
        logINPage.clickOnForgetPasswordButton();
        logINPage.enterResetEmail("MohamedAli@hotmail.com");
        //logINPage.clickOnResetMyPasswordButton();
        String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/forgotpassword/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.get("https://magento.softwaretestingboard.com/collections/yoga-new.html");
        logINPage.clickOnSignINButton();
    }

   @Test
  public void validGoToCreateAnAccountPage(){
       logINPage.clickOnCreateAnAccountButton();
       String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/create/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        driver.get("https://magento.softwaretestingboard.com/collections/yoga-new.html");
        logINPage.clickOnSignINButton();

    }


@AfterTest
    public void  quitDriver(){

        driver.quit();
}

}
