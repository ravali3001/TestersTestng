package testSuite;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverFactory;
import pageObjects.LoginPage;
import pageObjects.TreeTestPage;
import pageObjects.TryEditorPage;
import utilities.TestDataProvider;

public class TreeTest extends BaseTest {
	private LoginPage loginPage;
	TryEditorPage tryEditorPage;
	WebDriver driver;
    TreeTestPage treePage;
	@Test()
	@BeforeMethod(dependsOnMethods = {"baseSetup"})
	public void setup() {
			 driver = DriverFactory.getDriver();
		    treePage = new TreeTestPage(driver); 
	loginPage = new LoginPage();
		//	loginPage.clickGetStarted();
	tryEditorPage = new TryEditorPage();
		//	loginPage.clickSignIn();
		//	loginPage.successfulLogin();
	}
	@Test
	public void verifyTreeModel() {
	
		treePage.clickGetStarted();
		treePage.overviewoftree();
		treePage.terminologies();
		treePage.typesoftrees();
		treePage.tree1();
		treePage.traversals1();
		treePage.binary1();
		treePage.tree1();
		treePage.imple1();
		treePage.binarytree1();
		treePage.impletrees1();
		treePage.apply1();
		treePage.binarysearch1();
		treePage.implementation1();
		//linkedPage.graphReprasentation();
		//linkedPage.clickTryHere();
	}
	/*@Test(dataProvider = "arrayPracticeData",dataProviderClass = utilities.TestDataProvider.class)
	public void linkTryHere(String testCaseName,String code,String expectedOutput) {
	treePage.clickGetStarted();
		treePage.hereT();
		tryEditorPage.enterCode(code);
		tryEditorPage.clickRun();
		
		String actualOutput = treePage.getOutput();
	       
	       System.out.println("TestCase: " + testCaseName + " | Expected: " + expectedOutput + " | Actual: " + actualOutput);

	      Assert.assertEquals(actualOutput, expectedOutput);
	    }}*/
	@Test(priority = 2, dataProvider = "validPythonCode", dataProviderClass = TestDataProvider.class)
	public void testValidCodeInPythonEditor(Map<String, String> editorData) {
		treePage.clickGetStarted();
		treePage.hereT();
		
		TryEditorPage editorPage = new TryEditorPage();
		String pythonCode = editorData.get("PythonCode");
		editorPage.enterCode(pythonCode);
        editorPage.clickRun();

		String output = editorPage.getOutput();
		//logger.info("python code is valid.");
		Assert.assertTrue(output.equals(editorData.get("ExpectedOutput")),
				"Expected Success but failed.");
	}

	@Test(priority = 3, dataProvider = "invalidPythonCode", dataProviderClass = TestDataProvider.class)
	public void testInvalidCodeInPythonEditor(Map<String, String> editorData) {
		treePage.clickGetStarted();;
		treePage.hereT();
		
		TryEditorPage editorPage = new TryEditorPage();
		String pythonCode = editorData.get("PythonCode");
		editorPage.enterCode(pythonCode);
        editorPage.clickRun();

		editorPage.acceptAlert();
		//logger.info("python code is Invalid.");
		Assert.assertTrue(editorPage.isRunButtonDisplayed(),
				"Expected to display run button, but not displayed.");
	}
}
	

