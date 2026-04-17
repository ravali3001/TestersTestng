package testSuite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import base.BaseTest;
import factory.DriverFactory;
import managers.PageObjectManager;
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
	    PageObjectManager pom;
        Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
        Map<String, Map<String, String>> arrayData1 = ExcelReader.getArrayTryData();

        private static final Logger logger = LogManager.getLogger(ArrayTest.class); 
        
        @BeforeMethod(dependsOnMethods = {"baseSetup"}) 
         public void setup() {
	       
           /* driver = DriverFactory.getDriver();  
	        arrayPage = new ArrayPage(); 
		    tryEditorPage = new TryEditorPage(); */
        	pom = new PageObjectManager(driver);

        	arrayPage = pom.getArrayPage();
        	tryEditorPage = pom.getTryEditorPage();
        	loginPage = pom.getLoginPage();
	     
	            
	    }
	    @Test(priority = 1)
	   public void clickArrayModule() {
	    	
	    	pom.getArrayPage().clickGetStartesArr();
	    	pom.getArrayPage().clickArraysInPython();
	    	pom.getArrayPage().clickArraysUsingList();
	    	pom.getArrayPage().clickBasicOperationsInLists();
	    	pom.getArrayPage().clickApplicationsOfArray();
	    }
	        
	 

	    @Test(priority = 2)
	    public void verifyArrayNavigation() {
	    	pom.getArrayPage().clickGetStartesArr();
	    	pom.getArrayPage().clickArraysInPython();
	        String expectedTitle= "Arrays in Python";
	        String actualTitle =pom.getArrayPage().getPageTitle();  
		    Assert.assertEquals(actualTitle, expectedTitle);
			logger.info("*********EXPECTED TITLE********* {} ,{} ,{} ", expectedTitle,"******ACTUAL TITLE******",actualTitle);

			pom.getArrayPage().clickTryHere();
	    }
	    @Test(priority = 3)
	    public void verifyUsingList() {
	    	
	    	pom.getArrayPage().clickGetStartesArr();
	    	pom.getArrayPage().clickArraysUsingList();
	        String expectedTitle= "Arrays Using List";
	        String actualTitle = pom.getArrayPage().getPageTitle();  
		    Assert.assertEquals(actualTitle, expectedTitle);
			logger.info("*********EXPECTED TITLE********* {} ,{} ,{} ", expectedTitle,"******ACTUAL TITLE******",actualTitle);

	        
			arrayPage.clickTryHere();
	        
	    }
	    @Test(priority = 4)
	    public void verifyBasicOperations() {
	    	
	    	pom.getArrayPage().clickGetStartesArr();
	    	pom.getArrayPage().clickBasicOperationsInLists();
	        String expectedTitle= "Basic Operations in Lists";
	        String actualTitle =pom.getArrayPage().getPageTitle();  
		    Assert.assertEquals(actualTitle, expectedTitle);
			logger.info("*********EXPECTED TITLE********* {} ,{} ,{} ", expectedTitle,"******ACTUAL TITLE******",actualTitle);

			pom.getArrayPage().clickTryHere();
	    }
	    @Test(priority = 5)
	    public void verifyApplicationofArray() {
	    	
	    	pom.getArrayPage().clickGetStartesArr();
	    	pom.getArrayPage().clickApplicationsOfArray();
	        String expectedTitle= "Applications of Array";
	        String actualTitle =pom.getArrayPage().getPageTitle();  
		    Assert.assertEquals(actualTitle, expectedTitle);
			logger.info("*********EXPECTED TITLE********* {} ,{} ,{} ", expectedTitle,"******ACTUAL TITLE******",actualTitle);

			pom.getArrayPage().clickTryHere();
	        
	    }
	    
	   //=========================Excel Data[ArrayDATA]=======================
	    
	    
	  @Test(dataProvider = "arrayPracticeData",dataProviderClass = utilities.TestDataProvider.class,priority = 6)

	    public void runArrayCodeTests(String testCaseName, String code, String expectedOutput) {
		 
		 
		   logger.info("Running TRY HERE test");
		  
		   pom.getArrayPage().clickGetStartesArr();
		   pom.getArrayPage().clickArraysInPython();
		   pom.getArrayPage().clickTryHere();
		   pom.getArrayPage().enterCodeInEditor(code);
		   pom.getArrayPage().clickRunButton();
	        String actualOutput =pom.getArrayPage().getTryEditorResult();
	     logger.info("TestCase: " + testCaseName + " | Expected: " + expectedOutput + " | Actual: " + actualOutput);

	        Assert.assertEquals(actualOutput, expectedOutput);
	    }
	  //==========================TestDataProvider PRACTICE QUESTIONS
	  
	  @Test(dataProvider = "ArrayPracticeQuestions",dataProviderClass = utilities.TestDataProvider.class,priority = 7)
	  public void RunPracticeQuestions(String Questions) {
		  pom.getArrayPage().clickGetStartesArr();

		  pom.getArrayPage().clickArraysInPython();
		  pom.getArrayPage().clickPracticeQuestions();
		  pom.getArrayPage().clickQuestion(Questions); 

          String ExpectedTitle="Assessment";

          String actualTitle =pom.getArrayPage().getPageTitle();

	      Assert.assertEquals(ExpectedTitle,actualTitle);
	      logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
	  }

	  //======================Excel Submit button verify============
	  
	  @Test(dataProvider = "arrayData1" ,dataProviderClass = utilities.TestDataProvider.class,priority = 8)


	  public void SubmitPracticeQuestions(String testcaseName,String Questions,String pythonCode,String expectedOutput) {
		  pom.getArrayPage().clickGetStartesArr();
		  pom.getArrayPage().clickArraysInPython();
		  pom.getArrayPage().clickPracticeQuestions();

   
		  pom.getArrayPage().clickQuestion(Questions);  
		  pom.getArrayPage().clearEdit();
          
		  pom.getArrayPage().enterCodeInEditor(pythonCode); 
		  pom.getArrayPage().submit();//Error occurred during submission

          String ExpectedTitle="Error occurred during submission";

          String actualTitle =pom.getArrayPage().getResultAfterSubmit();
	      Assert.assertEquals(ExpectedTitle,actualTitle);
	      logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);

	  
	  } 
	  
	  //======================Excel Run button verify============

	  @Test(dataProvider = "arrayData1" ,dataProviderClass = utilities.TestDataProvider.class,priority = 9)

	  public void RunBTNPracticeQuestions(String testcaseName,String Questions,String pythonCode,String expectedOutput) {
		  pom.getArrayPage().clickGetStartesArr();

		  pom.getArrayPage().clickArraysInPython();
		  pom.getArrayPage().clickPracticeQuestions();
		  pom.getArrayPage().clickQuestion(Questions); 
		  pom.getArrayPage().clearEdit();
          
		  pom.getArrayPage().enterCodeInEditor(pythonCode); 
		  pom.getArrayPage().clickRunButton();
          String ExpectedTitle=expectedOutput;

          String actualTitle =pom.getArrayPage().getTryEditorResult();
          Assert.assertEquals(actualTitle.trim().toLowerCase(), ExpectedTitle.trim().toLowerCase());
	      logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
	  
	  }
	   
	   
	  @AfterMethod
	  public void tearDown() {

	   if (driver != null) {
	       driver.quit();
	  }
	  }

}

