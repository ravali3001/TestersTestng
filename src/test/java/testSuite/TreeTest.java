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

	public class TreeTest extends BaseTest {
	    private static final Logger logger = LogManager.getLogger(TreeTest.class);

	    PageObjectManager pom; 
		WebDriver driver;
	  

		@BeforeMethod(dependsOnMethods = {"baseSetup"})
		public void setup() {
			logger.info("*****TreeMOdule*****");
				 driver = DriverFactory.getDriver();
				 pom = new PageObjectManager(driver);
			    pom.getLoginPage().successfulLogin();			   

			    
		}
		@Test
		public void verifyTreeModel() {
		
			pom.getTreePage().clickGetStarted();
			pom.getTreePage().overviewoftree();
			pom.getTreePage().terminologies();
			pom.getTreePage().typesoftrees();
			pom.getTreePage().tree1();
			pom.getTreePage().traversals1();
			pom.getTreePage().binary1();
			pom.getTreePage().tree1();
			pom.getTreePage().imple1();
			pom.getTreePage().binarytree1();
			pom.getTreePage().impletrees1();
			pom.getTreePage().apply1();
			pom.getTreePage().binarysearch1();
			pom.getTreePage().implementation1();
			

			
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
		



