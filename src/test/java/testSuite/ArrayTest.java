package testSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import base.BaseTest;
import factory.DriverFactory;
import pageObjects.ArrayPage;
import pageObjects.LoginPage;
import pageObjects.TryEditorPage;

import utilities.ExcelReader;


import java.util.Map;


@Listeners(listeners.TestListener.class) 
public class ArrayTest extends BaseTest{
	

	    WebDriver driver;
	    ArrayPage arrayPage;
	    LoginPage loginPage;
	    TryEditorPage tryEditorPage;
        Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
        Map<String, Map<String, String>> arrayData1 = ExcelReader.getArrayTryData();
       // arrayPage = new ArrayPage(DriverFactory.getDriver());

        private static final Logger logger = LogManager.getLogger(ArrayTest.class); 

        @BeforeMethod(dependsOnMethods = {"baseSetup"})
	  	    
	    public void setup() {
	       
            driver = DriverFactory.getDriver(); // or new ChromeDriver()

	        arrayPage = new ArrayPage(driver);
		    tryEditorPage = new TryEditorPage();

	        loginPage=new LoginPage();
	    	loginPage.clickGetStarted();
	        loginPage.clickSignIn();
	        loginPage.successfulLogin();
	            
	    }
	    @Test(priority = 1)
	   public void clickArrayModule() {
		    arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysInPython();
	        arrayPage.clickArraysUsingList();
	        arrayPage.clickBasicOperationsInLists();
	        arrayPage.clickApplicationsOfArray();
	    }
	        
	 

	    @Test(priority = 2)
	    public void verifyArrayNavigation() {
	        arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysInPython();
	        arrayPage.clickTryHere();
	    }
	    @Test(priority = 3)
	    public void verifyUsingList() {
	        arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysUsingList();
	        arrayPage.clickTryHere();
	    }
	    @Test(priority = 4)
	    public void verifyBasicOperations() {
	        arrayPage.clickGetStartesArr();
	        arrayPage.clickBasicOperationsInLists();
	        arrayPage.clickTryHere();
	    }
	    @Test(priority = 5)
	    public void verifyApplicationofArray() {
	        arrayPage.clickGetStartesArr();
	        arrayPage.clickApplicationsOfArray();
	        arrayPage.clickTryHere();
	    }
	    
	   //=========================Excel Data[ArrayDATA]=======================
	    
	    
	  @Test(dataProvider = "arrayPracticeData",dataProviderClass = utilities.TestDataProvider.class,priority = 6)

	    public void runArrayCodeTests(String testCaseName, String code, String expectedOutput) {
		 
		 
		    System.out.println("Running TRY HERE test");
		  
		    arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysInPython();
	        arrayPage.clickTryHere();
	        arrayPage.enterCodeInEditor(code);
	        arrayPage.clickRunButton();
	        String actualOutput = arrayPage.getTryEditorResult();
	       // String actualOutput = arrayPage.getOutput();
	        System.out.println("TestCase: " + testCaseName + " | Expected: " + expectedOutput + " | Actual: " + actualOutput);

	        Assert.assertEquals(actualOutput, expectedOutput);
	    }
	  //==========================TestDataProvider PRACTICE QUESTIONS
	  
	  @Test(dataProvider = "ArrayPracticeQuestions",dataProviderClass = utilities.TestDataProvider.class,priority = 7)
	  public void RunPracticeQuestions(String Questions) {
		  arrayPage.clickGetStartesArr();

		  arrayPage.clickArraysInPython();
          arrayPage.clickPracticeQuestions();
          arrayPage.clickQuestion(Questions); 

         String ExpectedTitle="Assessment";

          String actualTitle =arrayPage.getPageTitle();
	        Assert.assertEquals(ExpectedTitle,actualTitle);
	        logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
	  }
	  //======================Excel Submit button verify============
	  
	  @Test(dataProvider = "arrayData1" ,dataProviderClass = utilities.TestDataProvider.class,priority = 8)

	  public void SubmitPracticeQuestions(String testcaseName,String Questions,String pythonCode,String expectedOutput) {
		  arrayPage.clickGetStartesArr();

		  arrayPage.clickArraysInPython();
          arrayPage.clickPracticeQuestions();
          //arrayPage.clickSearchTheArray();
          arrayPage.clickQuestion(Questions); 
          arrayPage.clearEdit();
          
          arrayPage.enterCodeInEditor(pythonCode); 
         arrayPage.submit();//Error occurred during submission
         String ExpectedTitle="Error occurred during submission";

         String actualTitle =arrayPage.getResultAfterSubmit();
	        Assert.assertEquals(ExpectedTitle,actualTitle);
	        logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
	  
	  } 
	  
	  //======================Excel Run button verify============

	  @Test(dataProvider = "arrayData1" ,dataProviderClass = utilities.TestDataProvider.class,priority = 9)

	  public void RunBTNPracticeQuestions(String testcaseName,String Questions,String pythonCode,String expectedOutput) {
		  arrayPage.clickGetStartesArr();

		  arrayPage.clickArraysInPython();
          arrayPage.clickPracticeQuestions();
          //arrayPage.clickSearchTheArray();
          arrayPage.clickQuestion(Questions); 
          arrayPage.clearEdit();
          
          arrayPage.enterCodeInEditor(pythonCode); 
         arrayPage.clickRunButton();
         String ExpectedTitle=expectedOutput;

         String actualTitle =arrayPage.getTryEditorResult();
         Assert.assertEquals(actualTitle.trim().toLowerCase(), ExpectedTitle.trim().toLowerCase());
	       // Assert.assertEquals(ExpectedTitle,actualTitle);
	        logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
	  
	  }
	   

	  @AfterMethod
	  public void tearDown() {
	    if (driver != null) {
	    driver.quit();
	      }
	  }

}


