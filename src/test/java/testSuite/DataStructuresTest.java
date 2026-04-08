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
             loginPage=new LoginPage(DriverFactory.getDriver());
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
   
    @DataProvider(name = "excelData")
    public Map<String, Map<String, String>> getExcelData() {
        return ExcelReader.getArraydataData(); // returns Object[][]
    }

    @Test(dataProvider = "excelData")
    public void testWithExcel(String input, String expected) {
    	DSintro.DSClickIntro();
    	DSintro.DSClickTimeComplexity();
    	DSintro.DSClickTryhere();
        DSintro.enterCode(input);
        DSintro.clickRunButton();
        String actual = DSintro.getOutput();
        Assert.assertEquals(actual, expected);
    }
    @Test(dataProvider = "arrayPracticeData")
    public void DSclickPracticeWithData(String input, String expected) {
    	DSintro.DSClickIntro();
    	DSintro.DSClickTimeComplexity();
    	DSintro.DSClickTryhere();
        
        // Send input to Try Editor (example)
        DSintro.enterCode(input);  

        // Click Run
        DSintro.clickRunButton();

        // Assertion
        String actualOutput = DSintro.getOutput();
        Assert.assertEquals(actualOutput, expected, "Output is not as expected!");
    }

}




