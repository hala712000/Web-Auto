import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestSuite {
    private static WebDriver driver;
    Class[] testClasses = {CreateAccountTest.class, LogINTest.class, SearchTest.class, ProductBrowsingTest.class, ShoppingCartTest.class};


    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://magento.softwaretestingboard.com/");

    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

    @Test
    public void runTestSuite() {
        // Run the test suite
        org.testng.TestNG testng = new org.testng.TestNG();
        testng.setTestClasses(testClasses);
        testng.run();
    }


}
