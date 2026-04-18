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
import pageObjects.LoginPage;
import utilities.ExcelReader;

public class DataStructuresTest extends BaseTest{
	

    WebDriver driver;
    PageObjectManager pom;
    Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
    private static final Logger logger = LogManager.getLogger(DataStructuresTest.class); 


   @BeforeMethod(dependsOnMethods = {"baseSetup"})
   public void setup() {
	   
       logger.info("************DATA STRUCTURES**********");
	   pom = new PageObjectManager(driver);
	   driver = DriverFactory.getDriver();
	   pom.getLoginPage().successfulLogin();
  
  }  
   @Test
  public void Verifylable() {
	   
       logger.info("*****SUCCESSFULL LOGIN******"); 
	   pom.getDataStructuresPage().islinkDisplayed();
	   String ExpectedTitle="NumpyNinja";

       String actualTitle =pom.getDataStructuresPage().getPageTitle();
	   Assert.assertEquals(ExpectedTitle,actualTitle);
	   logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
	   
   }
   @Test
   public void VerifyTitle() {
	   
	   pom.getDataStructuresPage().DSClickIntro();
	   pom.getDataStructuresPage().isTitleDisplayed();
	   String ExpectedTitle="Data Structures-Introduction";

       String actualTitle =pom.getDataStructuresPage().getPageTitle();
	   Assert.assertEquals(ExpectedTitle,actualTitle);
	   logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
   }
    @Test
    public void DSclick() {
    	pom.getDataStructuresPage().DSClickIntro();
    	pom.getDataStructuresPage().DSClickTimeComplexity();
    	pom.getDataStructuresPage().DSClickTryhere();
    	String ExpectedTitle="Assessment";

        String actualTitle =pom.getDataStructuresPage().getPageTitle();
	    Assert.assertEquals(ExpectedTitle,actualTitle);
	    logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
    }
    
    
    @Test
   public void DsclickPractice() { 
    	pom.getDataStructuresPage().DSClickIntro();
    	pom.getDataStructuresPage().DSClickPracticeQ();
     	String ExpectedTitle="Practice Questions";

        String actualTitle =pom.getDataStructuresPage().getPageTitle();
	    Assert.assertEquals(ExpectedTitle,actualTitle);
	    logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);

   }
   
    @Test(dataProvider = "arrayPracticeData", dataProviderClass = utilities.TestDataProvider.class)
    public void DSclickPracticeWithData(String testCaseName,String code, String expected) {
    	pom.getDataStructuresPage().DSClickIntro();
    	pom.getDataStructuresPage().DSClickTimeComplexity();
    	pom.getDataStructuresPage().DSClickTryhere();
        
        // Send input to Try Editor (example)
    	pom.getDataStructuresPage().enterCode(code);  

        // Click Run
    	pom.getDataStructuresPage().clickRunButton();

        // Assertion
        String actualOutput =pom.getDataStructuresPage().getTryEditorResult();
        Assert.assertEquals(actualOutput, expected, "Output is not as expected!");
    }

}




