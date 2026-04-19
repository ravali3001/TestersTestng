package testSuite;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import managers.PageObjectManager;
import pageObjects.LoginPage;
import utilities.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {

	private static final Logger logger = LogManager.getLogger(LoginTest.class);

	PageObjectManager pom; 
	private LoginPage loginPage;

	@BeforeMethod(dependsOnMethods = { "baseSetup", "dsAlgoLogin" })
	public void setup() {
		pom = new PageObjectManager();
		loginPage = pom.getLoginPage();
	}

	@Test(priority = 0, dataProvider = "validLogin", dataProviderClass = TestDataProvider.class)
	public void testValidLogin(Map<String, String> credentials) {
		loginPage.enterUsername(credentials.get("Username"));
		loginPage.enterPassword(credentials.get("Password"));
		loginPage.clickLoginButton();
		logger.info("login button is Successful.");
		Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed, but expected to succeed.");
	}

	@Test(priority = 1, dataProvider = "invalidLogin", dataProviderClass = TestDataProvider.class)
	public void testInvalidLogin(Map<String, String> credentials) {
		loginPage.enterUsername(credentials.get("Username"));
		loginPage.enterPassword(credentials.get("Password"));
		loginPage.clickLoginButton();
		logger.info("LoginErrorDisplayed.");
		Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Login succeeded, but expected to fail.");
	}

	@Test(priority = 2, dataProvider = "invalidUsername", dataProviderClass = TestDataProvider.class)
	public void testinvalidUsername(Map<String, String> credentials) {
		loginPage.enterUsername(credentials.get("Username"));
		loginPage.enterPassword(credentials.get("Password"));
		loginPage.clickLoginButton();
		logger.info("LoginErrorDisplayed.");
		Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Login succeeded, but expected to fail.");
	}

	@Test(priority = 3, dataProvider = "invalidPassword", dataProviderClass = TestDataProvider.class)
	public void testinvalidPassword(Map<String, String> credentials) {
		loginPage.enterUsername(credentials.get("Username"));
		loginPage.enterPassword(credentials.get("Password"));
		loginPage.clickLoginButton();
		logger.info("LoginErrorDisplayed.");
		Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Login succeeded, but expected to fail.");
	}

	@Test(priority = 4)
	public void verifyLoginPerformance() {
		long startTime;
		long endTime;
		int maxSeconds = 5;
		startTime = System.currentTimeMillis();
		loginPage.successfulLogin();
		loginPage.waitForSuccessfulLogin();

		endTime = System.currentTimeMillis();
		long responseTime = (endTime - startTime) / 1000;
		logger.info("Expected time is {} seconds.", maxSeconds);
		logger.info("Actual time is {} seconds.", responseTime);

		Assert.assertTrue(responseTime <= maxSeconds);

	}

	@Test(priority = 5, dataProvider = "validLogin", dataProviderClass = TestDataProvider.class)
	public void verifyLoginButtonEnable(Map<String, String> credentials) {
		loginPage.enterUsername(credentials.get("Username"));
		loginPage.enterPassword(credentials.get("Password"));
		boolean isEnabled = loginPage.isLoginButtonEnabled();
		logger.info("login button is enabled.");

		Assert.assertTrue(isEnabled);
	}

	@Test(priority = 6)
	public void verifyPasswordIsMasked() {
		loginPage.enterPassword("Sample@123");
		String fieldType = loginPage.getPasswordFieldType();
		logger.info("password field is masked.");

		Assert.assertEquals(fieldType, "password", "Password field is not masked");
	}

	@Test(priority = 7, dataProvider = "invalidLogin", dataProviderClass = TestDataProvider.class)
	public void verifyInvalidLoginErrorMsg(Map<String, String> credentials) {
		loginPage.enterUsername(credentials.get("Username"));
		loginPage.enterPassword(credentials.get("Password"));
		loginPage.clickLoginButton();
		boolean isErrorDisplayed = loginPage.isLoginErrorDisplayed();
		logger.info("login error message displayed.");

		Assert.assertFalse(isErrorDisplayed);
	}

}
