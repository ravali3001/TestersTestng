package base;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.annotations.*;
import factory.DriverFactory;
import pageObjects.LoginPage;
import utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseTest {
	WebDriver driver;
	LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void baseSetup() {
        DriverFactory.initDriver();
        
       
        String url = ConfigReader.getProperty("url");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("baseUrl not found in config.properties");
        }
        DriverFactory.getDriver().get(url);
    }

    @BeforeMethod()
    public void dsAlgoLogin() {
    	loginPage = new LoginPage();
		loginPage.clickGetStarted();
		loginPage.clickSignIn();
		loginPage.successfulLogin();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
       DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
