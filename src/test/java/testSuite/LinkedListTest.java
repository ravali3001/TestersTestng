package testSuite;


 import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import factory.DriverFactory;
import managers.PageObjectManager;
import utilities.TestDataProvider;
	

public class LinkedListTest extends BaseTest  {
    private static final Logger logger = LogManager.getLogger(LinkedListTest.class); 

		WebDriver driver;
		PageObjectManager pom; 
		
       	@BeforeMethod(dependsOnMethods = {"baseSetup"})
		public void setup() {
			logger.info("******LINKEDLIST MODULE********");
			 driver = DriverFactory.getDriver();
	         pom = new PageObjectManager(driver);
	         pom.getLoginPage().successfulLogin(); 

	}
		
		@Test(dataProvider = "LinkedlistData", dataProviderClass = utilities.TestDataProvider.class)
		public void verifyLinkedModel(String topic) {
		
			pom.getLinkedPage().clickGetStartedLlinkedlist();
			pom.getLinkedPage().clickTopic(topic);
			
		}
		@Test(priority = 2, dataProvider = "validPythonCode", dataProviderClass = TestDataProvider.class)
		public void testValidCodeInPythonEditor(Map<String, String> editorData) {
			pom.getTreePage().clickGetStarted();
			pom.getTreePage().hereT();
			
			String pythonCode = editorData.get("PythonCode");
			pom.getTryEditorPage().enterCode(pythonCode);
	        pom.getTryEditorPage().clickRun();

			String output = pom.getTryEditorPage().getOutput();
			//logger.info("python code is valid.");
			Assert.assertTrue(output.equals(editorData.get("ExpectedOutput")),
					"Expected Success but failed.");
		}

		@Test(priority = 3, dataProvider = "invalidPythonCode", dataProviderClass = TestDataProvider.class)
		public void testInvalidCodeInPythonEditor(Map<String, String> editorData) {
			pom.getTreePage().clickGetStarted();;
			pom.getTreePage().hereT();
			
			String pythonCode = editorData.get("PythonCode");
			pom.getTryEditorPage().enterCode(pythonCode);
	        pom.getTryEditorPage().clickRun();

			pom.getTryEditorPage().acceptAlert();
			//logger.info("python code is Invalid.");
			Assert.assertTrue(pom.getTryEditorPage().isRunButtonDisplayed(),
					"Expected to display run button, but not displayed.");
		}
		}

		
		


