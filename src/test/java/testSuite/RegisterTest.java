package testSuite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import factory.DriverFactory;
import pageObjects.LoginPage;

import pageObjects.RegisterTestPage;



public class RegisterTest extends BaseTest{
	private LoginPage loginPage;
	WebDriver driver;
    RegisterTestPage registrationPage;
	@Test(priority=1)
	@BeforeMethod(dependsOnMethods = {"baseSetup"})
	public void setup() {
		 driver = DriverFactory.getDriver();
	     registrationPage = new RegisterTestPage(driver); 
		loginPage = new LoginPage();
		loginPage.clickGetStarted();
	}
   @Test()
   
}
