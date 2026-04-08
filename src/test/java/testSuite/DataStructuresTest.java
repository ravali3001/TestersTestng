package testSuite;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import factory.DriverFactory;
import pageObjects.DataStructuresPages;
import pageObjects.LoginPage;
import utilities.ConfigReader;
import utilities.ExcelReader;

public class DataStructuresTest {
	

    WebDriver driver;
    DataStructuresPages DSintro;
    LoginPage loginPage;
    Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();

   @BeforeMethod()
   public void setup() {
      
       DriverFactory.initDriver();  
       ConfigReader.loadConfig();
       
       DSintro= new DataStructuresPages(DriverFactory.getDriver());
             loginPage=new LoginPage();
   	loginPage.clickGetStarted();
       loginPage.clickSignIn();
       loginPage.login(
           ConfigReader.getProperty("username"),
           ConfigReader.getProperty("password")
       );
   }
   
    @Test
    public void DSclick() {
    	DSintro.DSClickIntro();
    	DSintro.DSClickTimeComplexity();
    	DSintro.DSClickTryhere();
    	
    }
    
    @Test
   public void DsclickPractice() {
    	DSintro.DSClickIntro();
     	DSintro.DSClickPracticeQ();

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




