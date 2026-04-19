package testSuite;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import factory.DriverFactory;
import managers.PageObjectManager;
import utilities.ConfigReader;
import utilities.ExcelReader;

@Listeners(listeners.chainTestListener.class) 

public class HomeTest {
	 private static final Logger logger = LogManager.getLogger(HomeTest.class); 

	WebDriver driver;
    PageObjectManager pom;

    Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
   
  
    @BeforeMethod
    public void setup() { 
        logger.info("************HOME MODULE**********");

    	pom = new PageObjectManager(driver);
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
        pom.getHomePage().clickLaunchStartR();        
    }
    
   
 
   //===========================Verify Register link=========================
   
   @Test
   public void AssertionReg() {
	   
	 pom.getHomePage().clickRegisterR();
     String expectedTitle = "Registration";
	 logger.info("1");
     String actualTitle = pom.getHomePage().getPagesTitle();
     logger.info("2");
     Assert.assertEquals(expectedTitle ,actualTitle);
     logger.info("expectedTitle{},{},{}",expectedTitle ,actualTitle);
     
}
  
  //=====================Before  sign in ============================
   @Test(dataProvider = "codeProvider", dataProviderClass = utilities.TestDataProvider.class)
   public void user_clicks_on_dropdown(String topic) {
	   
       logger.info("**********DROP DOWN*********");
       pom.getHomePage().clickDDArrayBS();
       pom.getHomePage().clickModule(topic);
   	   String expectedTitle = "You are not logged in";
   	   logger.info("1"); 
       String actualTitle =  pom.getHomePage().clickAlertR();
       logger.info("2");
       Assert.assertEquals(expectedTitle ,actualTitle);
       logger.info("expectedTitle {},{},{}",expectedTitle ,actualTitle);
       
   }
   //=======================Successfully sign in message==============================
   @Test
   public void VerifySignlink() {
	   
    pom.getHomePage().clickSignInlinkR();
    String expectedTitle = "Login";
    logger.info("1");
    String actualTitle = pom.getHomePage().getPagesTitle();
    logger.info("2");
    Assert.assertEquals(expectedTitle ,actualTitle);
    logger.info("expectedTitle {},{},",expectedTitle ,actualTitle);
    pom.getLoginPage().successfulLogin();
    String expectedTitle1 = "You are logged in"
    		+ "";
	   logger.info("1"); 
    String actualTitle1 = pom.getHomePage().clickAlertR();
    logger.info("2");
    Assert.assertEquals(expectedTitle1 ,actualTitle1);
    logger.info("expectedTitle {},{}",expectedTitle ,actualTitle);
    
    
   }
   //==================After sign in===============================
  @Test(dataProvider = "codeProvider", dataProviderClass = utilities.TestDataProvider.class)

   public void VerifySigninAfter(String topic) { 
	  
	  
	  pom.getHomePage().clickSignInlinkR();
	    String expectedTitle = "Login";
		logger.info("1");
	    String actualTitle =  pom.getHomePage().getPagesTitle();
	    logger.info("2");
	    Assert.assertEquals(expectedTitle ,actualTitle);
	    logger.info("expectedTitle {},{},",expectedTitle ,actualTitle);
	    pom.getLoginPage().successfulLogin();
	        
	   
	    pom.getHomePage().clickDDArrayBS(); 
	    pom.getHomePage().clickModule(topic);
	       String actualTitle2= pom.getHomePage().getPagesTitle();
	       Assert.assertTrue(
	    		    actualTitle2.toLowerCase().contains(topic.toLowerCase()) ||
	    		    topic.toLowerCase().contains(actualTitle2.toLowerCase()),
	    		    "Mismatch: topic=" + topic + ", actual=" + actualTitle2
	    		);
		    logger.info("expectedTitle {},{}",topic,actualTitle2);
	       
   }
   
  @AfterMethod
  public void tearDown() {
	  
      if (driver != null) {
          driver.quit(); 
      }
  }
}
