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
import utilities.ConfigReader;
import utilities.ExcelReader;

import java.time.Duration;
import java.util.List;
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
	       // driver = new ChromeDriver();
	        //driver.manage().window().maximize();
	        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        //DriverFactory.initDriver();  
	        //ConfigReader.loadConfig();
            driver = DriverFactory.getDriver(); // or new ChromeDriver()

	        arrayPage = new ArrayPage(driver);
		  	 tryEditorPage = new TryEditorPage(driver);

	        loginPage=new LoginPage();
	    	loginPage.clickGetStarted();
	        loginPage.clickSignIn();
	        loginPage.successfulLogin();
	            
	    }
	    @Test
	   public void clickArrayModule() {
		   arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysInPython();
	        arrayPage.clickArraysUsingList();
	        arrayPage.clickBasicOperationsInLists();
	        arrayPage.clickApplicationsOfArray();
	    }
	        
	 /*  }
	   public void clickArrayUsingList() {
		   arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysUsingList();
	   }
	   public void clickBasicoperations() {
		   arrayPage.clickGetStartesArr();
	        arrayPage.clickBasicOperationsInLists();
	   }
	   public void clickApplicationsofArray() {
		   arrayPage.clickGetStartesArr();
	        arrayPage.clickApplicationsOfArray();
	   }*/
	  /*  public void setupPages() {
	        //loginPage = new LoginPage(getDriver()); 
	        //arrayPage = new ArrayPage(getDriver());
	    	loginPage.clickGetStarted();
	        loginPage.clickSignIn();
	        loginPage.login(
	            ConfigReader.getProperty("username"),
	            ConfigReader.getProperty("password")
	        );
	    }*/


	    @Test(priority = 1)
	    public void verifyArrayNavigation() {
	        arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysInPython();
	    }
	  /*  @DataProvider(name = "codeProvider")
	    public Object[][] codeData() {
	        return new Object[][] {
	            {"print('Hello Array')"},
	            {"print('Test 123')"},
	            {"print(2+3)"}
	        };}*/
	   
	    
	    @Test(dataProvider = "codeProvider")
	    public void runCodeTest(String code) {
	    	arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysInPython();
	        arrayPage.clickTryHere();
	        arrayPage.enterCodeInEditor(code);
	        arrayPage.clickRunButton();

	        String result = arrayPage.getOutput();
	        System.out.println("Result: " + result);
	    }
	   /* @Test(priority = 2)
	    public void verifyTryEditorRun() {
	    	arrayPage.clickGetStartesArr();
	        arrayPage.clickArraysInPython();
	        arrayPage.clickTryHere();

	        String code = "print('Hello Array')";
	        arrayPage.enterCodeInEditor(code);
	        arrayPage.clickRunButton();

	        String result = arrayPage.getOutput();
	        System.out.println("Result: " + result);
	    }*/
	    //@Test(dataProvider = "codeProvider")
	   
	 /* @DataProvider(name = "arrayCodeProvider")
	    public Object[][] getArrayData() {
	        Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
	        Object[][] data = new Object[arrayData.size()][3];
	        int i = 0;
	        for (String testCase : arrayData.keySet()) {
	            data[i][0] = testCase;
	            data[i][1] = arrayData.get(testCase).get("Code");
	            data[i][2] = arrayData.get(testCase).get("ExpectedOutput");
	            i++;
	        }
	        return data;
	    }*/

	  @Test(dataProvider = "arrayPracticeData",dataProviderClass = utilities.TestDataProvider.class)
	  // @Test(dataProvider = "codeProvider", dataProviderClass = utilities.DataProvider1.class)

	    public void runArrayCodeTests(String testCaseName, String code, String expectedOutput) {
		 
		 
		      System.out.println("Running test");
		  
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
	  @Test(dataProvider = "ArrayPracticeQuestions",dataProviderClass = utilities.TestDataProvider.class)
	 // @Test(dataProvider = "arrayPracticeData1" ,dataProviderClass = utilities.DataProvider1.class)

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
	  @Test(dataProvider = "arrayData1" ,dataProviderClass = utilities.TestDataProvider.class)
	  //@Test(dataProvider = "ArrayPracticeQuestions",dataProviderClass = utilities.DataProvider1.class)

	  public void SubmitPracticeQuestions(String testcaseName,String Questions,String pythonCode,String expectedOutput) {
		  arrayPage.clickGetStartesArr();
		  arrayPage.clickArraysInPython();
          arrayPage.clickPracticeQuestions();
          arrayPage.clickQuestion(Questions);
		  //arrayPage.clickArraysInPython();
         // arrayPage.clickPracticeQuestions();
          //arrayPage.clickSearchTheArray();
          //arrayPage.clickQuestion(Questions); 
          arrayPage.clearEdit(); 
          
         arrayPage.enterCodeInEditor(pythonCode); 
         tryEditorPage.submit();
         String ExpectedTitle="Error occurred during submission";

         String actualTitle =arrayPage.getResultAfterSubmit();
	        Assert.assertEquals(ExpectedTitle,actualTitle);
	        logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
	  }
	  
	  @Test(dataProvider = "arrayData1" ,dataProviderClass = utilities.TestDataProvider.class)
	  //@Test(dataProvider = "ArrayPracticeQuestions",dataProviderClass = utilities.DataProvider1.class)

	  public void RunBtnPracticeQuestions(String testcaseName,String Questions,String pythonCode,String expectedOutput) {
		  arrayPage.clickGetStartesArr();
		  arrayPage.clickArraysInPython();
          arrayPage.clickPracticeQuestions();
          arrayPage.clickQuestion(Questions);
		  //arrayPage.clickArraysInPython();
         // arrayPage.clickPracticeQuestions();
          //arrayPage.clickSearchTheArray();
          //arrayPage.clickQuestion(Questions); 
          arrayPage.clearEdit(); 
          
         arrayPage.enterCodeInEditor(pythonCode); 
         tryEditorPage.clickRun();
         String ExpectedTitle=expectedOutput;

         String actualTitle =arrayPage.getResultAfterRun();
	        Assert.assertEquals(ExpectedTitle,actualTitle);
	        logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualTitle);
	  }
	  
	   
	  @AfterMethod
	  public void tearDown() {
	     // if (driver != null) {
	      //    driver.quit();
	      }
	  }

	


