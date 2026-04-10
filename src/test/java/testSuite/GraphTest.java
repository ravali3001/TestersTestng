package testSuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverFactory;
import pageObjects.GraphTestPage;
import pageObjects.LoginPage;
import pageObjects.RegisterTestPage;

public class GraphTest extends BaseTest  {
	private LoginPage loginPage;
	WebDriver driver;
    GraphTestPage graphPage;
	@Test(priority=1)
	@BeforeMethod(dependsOnMethods = {"baseSetup"})
	public void setup() {
		 driver = DriverFactory.getDriver();
	    graphPage = new GraphTestPage(driver); 
		loginPage = new LoginPage();
		loginPage.clickGetStarted();
		loginPage.clickSignIn();
		loginPage.successfulLogin();
}
	@Test
	public void verifyGraphModel() {
	
		graphPage.clickGetStarted();
		graphPage.graphLink();
		graphPage.graphReprasentation();
		graphPage.clickTryHere();
	}
	
	}
