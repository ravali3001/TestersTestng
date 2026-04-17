package testSuite;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverFactory;
import pageObjects.GraphTestPage;
import pageObjects.LoginPage;
import pageObjects.TryEditorPage;

public class GraphTest extends BaseTest  {
	private LoginPage loginPage;
	TryEditorPage tryEditorPage;
	WebDriver driver;
    GraphTestPage graphPage;
	@Test(priority=1)
	@BeforeMethod(dependsOnMethods = {"baseSetup"})
	public void setup() {
		 driver = DriverFactory.getDriver();
	    graphPage = new GraphTestPage(driver); 
        //loginPage = new LoginPage();
		//loginPage.clickGetStarted();
	    tryEditorPage = new TryEditorPage();
	//	loginPage.clickSignIn();
		//loginPage.successfulLogin();
}
	@Test
	public void verifyGraphModel() {
	
		graphPage.clickGetStarted();
		graphPage.graphLink();
		graphPage.graphReprasentation();
		graphPage.clickTryHere();
	}
	@Test(dataProvider = "arrayPracticeData",dataProviderClass = utilities.TestDataProvider.class)
	public void linkTryHere(String testCaseName, String code, String expectedOutput) {
		graphPage.clickGetStarted();
		graphPage.graphReprasentation();
		graphPage.clickTryHere();
		tryEditorPage.enterCode(code);
		tryEditorPage.clickRun();
		
		 String actualOutput = graphPage.getOutput();
	       
	        System.out.println("TestCase: " + testCaseName + " | Expected: " + expectedOutput + " | Actual: " + actualOutput);

	        Assert.assertEquals(actualOutput, expectedOutput);
	    }
	
	
		
	}
