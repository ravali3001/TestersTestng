package base;

import org.testng.annotations.*;
import factory.DriverFactory;
import pageObjects.LoginPage;
import utilities.ConfigReader;
import org.openqa.selenium.WebDriver;


public class BaseTest {
	
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
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
       DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
