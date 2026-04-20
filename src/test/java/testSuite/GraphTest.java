package testSuite;

	import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
    import org.openqa.selenium.WebDriver;
	import org.testng.Assert;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	import base.BaseTest;
	import factory.DriverFactory;
    import managers.PageObjectManager;


	public class GraphTest extends BaseTest  { 
        private static final Logger logger = LogManager.getLogger(GraphTest.class); 
 
		WebDriver driver;
	    PageObjectManager pom; 
   	
	
		@BeforeMethod(dependsOnMethods = {"baseSetup"})
		public void setup() {
			 driver = DriverFactory.getDriver();
	         pom = new PageObjectManager(driver);
	         pom.getLoginPage().successfulLogin(); 

		   
	}
		@Test
		public void verifyGraphModel() { 
		
			pom.getGraphPage().clickGetStarted();
			pom.getGraphPage().graphLink();
			pom.getGraphPage().graphReprasentation();
			pom.getGraphPage().clickTryHere();
			String ExpectedTitle="Assessment";

	          String actualTitle =pom.getGraphPage().getPageTitle();

		      Assert.assertEquals(ExpectedTitle,actualTitle);
		      logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
		}
		@Test(dataProvider = "arrayPracticeData",dataProviderClass = utilities.TestDataProvider.class)
		public void linkTryHere(String testCaseName, String code, String expectedOutput) {
			pom.getGraphPage().clickGetStarted();
			pom.getGraphPage().graphReprasentation();
			pom.getGraphPage().clickTryHere();
			pom.getTryEditorPage().enterCode(code);
			pom.getTryEditorPage().clickRun();
			
			 String actualOutput = pom.getArrayPage().getTryEditorResult();
		       
		        logger.info("TestCase: " + testCaseName + " | Expected: " + expectedOutput + " | Actual: " + actualOutput);

		        Assert.assertEquals(actualOutput, expectedOutput);
		    }
		
		
			
		}


