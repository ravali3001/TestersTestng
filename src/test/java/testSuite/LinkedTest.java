package testSuite;
    import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import base.BaseTest;
	import factory.DriverFactory;
	import pageObjects.LinkedTestPage;
	import pageObjects.LoginPage;
import pageObjects.TryEditorPage;
	

	public class LinkedTest extends BaseTest  {
		private LoginPage loginPage;
		TryEditorPage tryEditorPage;
		WebDriver driver;
	    LinkedTestPage linkedPage;
		@Test(priority=1)
       	@BeforeMethod(dependsOnMethods = {"baseSetup"})
		public void setup() {
			 driver = DriverFactory.getDriver();
		    linkedPage = new LinkedTestPage(driver); 
	loginPage = new LoginPage();
		//	loginPage.clickGetStarted();
	tryEditorPage = new TryEditorPage();
		//	loginPage.clickSignIn();
		//	loginPage.successfulLogin();
	}
		
		@Test(dataProvider = "LinkedlistData", dataProviderClass = utilities.TestDataProvider.class)
		public void verifyLinkedModel(String topic) {
		
			linkedPage.clickGetStartedLlinkedlist();
			linkedPage.clickTopic(topic);
			//linkedPage.graphReprasentation();
			//linkedPage.clickTryHere();
		}
		@Test(dataProvider = "arrayPracticeData",dataProviderClass = utilities.TestDataProvider.class)
		public void linkTryHere(String testCaseName, String code, String expectedOutput) {
			linkedPage.clickGetStartedLlinkedlist();
			linkedPage.here();
			tryEditorPage.enterCode(code);
			tryEditorPage.clickRun();
			
			 String actualOutput = linkedPage.getAlertMessage();
		       
		        System.out.println("TestCase: " + testCaseName + " | Expected: " + expectedOutput + " | Actual: " + actualOutput);

		        Assert.assertEquals(actualOutput, expectedOutput);
		    }
		}

		
		

