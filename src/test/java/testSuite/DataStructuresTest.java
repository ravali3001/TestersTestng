package testSuite;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverFactory;
import pageObjects.DataStructuresPages;
import pageObjects.LoginPage;
//import utilities.ConfigReader;
import utilities.ExcelReader;

public class DataStructuresTest extends BaseTest{
	

    WebDriver driver;
    DataStructuresPages DSintro;
    LoginPage loginPage;
    Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
    private static final Logger logger = LogManager.getLogger(DataStructuresTest.class); 


   @BeforeMethod(dependsOnMethods = {"baseSetup"})
   public void setup() {
	   
	   driver = DriverFactory.getDriver(); 
       DSintro= new DataStructuresPages();
      logger.info("*****SUCCESSFULL LOGIN******"); 
  
   }
   
    @Test
    public void DSclick() {
    	DSintro.DSClickIntro();
    	DSintro.DSClickTimeComplexity();
    	DSintro.DSClickTryhere();
    	String ExpectedTitle="Assessment";

        String actualTitle =DSintro.getPageTitle();
	        Assert.assertEquals(ExpectedTitle,actualTitle);
	        logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
    }
    
    @Test
   public void DsclickPractice() {
    	DSintro.DSClickIntro();
     	DSintro.DSClickPracticeQ();
     	String ExpectedTitle="Practice Questions";

        String actualTitle =DSintro.getPageTitle();
	        Assert.assertEquals(ExpectedTitle,actualTitle);
	        logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);

   }
   
    @Test(dataProvider = "arrayPracticeData", dataProviderClass = utilities.TestDataProvider.class)
    public void DSclickPracticeWithData(String testCaseName,String code, String expected) {
    	DSintro.DSClickIntro();
    	DSintro.DSClickTimeComplexity();
    	DSintro.DSClickTryhere();
        
        // Send input to Try Editor (example)
        DSintro.enterCode(code);  

        // Click Run
        DSintro.clickRunButton();

        // Assertion
        String actualOutput = DSintro.getTryEditorResult();
        Assert.assertEquals(actualOutput, expected, "Output is not as expected!");
    }

}




