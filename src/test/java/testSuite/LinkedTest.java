package testSuite;
    import org.openqa.selenium.WebDriver;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import base.BaseTest;
	import factory.DriverFactory;
	import pageObjects.LinkedTestPage;
	import pageObjects.LoginPage;
	

	public class LinkedTest {//extends BaseTest  {
		private LoginPage loginPage;
		WebDriver driver;
	    LinkedTestPage linkedPage;
		@Test(priority=1)
		@BeforeMethod()// (dependsOnMethods = {"baseSetup"})//
		public void setup() {
			 driver = DriverFactory.getDriver();
		    linkedPage = new LinkedTestPage(driver); 
	loginPage = new LoginPage();
			loginPage.clickGetStarted();
			loginPage.clickSignIn();
			loginPage.successfulLogin();
	}
		@Test
		public void verifyLinkedModel(String topic) {
		
			linkedPage.clickGetStartedLlinkedlist();
			linkedPage.clickTopic(topic);
			//linkedPage.graphReprasentation();
			//linkedPage.clickTryHere();
		}
		
		}

