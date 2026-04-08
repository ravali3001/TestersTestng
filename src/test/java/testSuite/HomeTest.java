package testSuite;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import factory.DriverFactory;
import pageObjects.HomePages;
import pageObjects.LoginPage;

import utilities.ConfigReader;
import utilities.ExcelReader;

public class HomeTest {
	 private static final Logger logger = LogManager.getLogger(HomeTest.class); 

	WebDriver driver;
	HomePages HomeDS;
    LoginPage loginPage;
    Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();

    @BeforeMethod()
    public void setup() {
      
      DriverFactory.initDriver();  
      ConfigReader.loadConfig();
      HomeDS= new HomePages(DriverFactory.getDriver());
      loginPage=new LoginPage();
      loginPage.clickGetStarted();
       
   }
   
   //===========================Verify Register link=========================
   
   @Test
   public void AssertionReg() {
	   
	 HomeDS.clickRegisterR();
     String expectedTitle = "Registration";
	 logger.info("1");
     String actualTitle = HomeDS.getPagesTitle();
     logger.info("2");
     Assert.assertEquals(expectedTitle ,actualTitle);
     logger.info("expectedTitle{},{},{}",expectedTitle ,actualTitle);
     
}
  
  //=====================Before  sign in ============================
   @Test(dataProvider = "codeProvider", dataProviderClass = utilities.TestDataProvider.class)
   public void user_clicks_on_dropdown(String topic) {
	   
       logger.info("**********DROP DOWN*********");
       HomeDS.clickDDArrayBS();
       HomeDS.clickModule(topic);
   	   String expectedTitle = "You are not logged in";
   	   logger.info("1"); 
       String actualTitle = HomeDS.clickAlertR();
       logger.info("2");
       Assert.assertEquals(expectedTitle ,actualTitle);
       logger.info("expectedTitle {},{},{}",expectedTitle ,actualTitle);
       
   }
   //=======================Successfully sign in message==============================
   @Test
   public void VerifySignlink() {
	   
   HomeDS.clickSignInlinkR();
   String expectedTitle = "Login";
	logger.info("1");
   String actualTitle = HomeDS.getPagesTitle();
   logger.info("2");
    Assert.assertEquals(expectedTitle ,actualTitle);
    logger.info("expectedTitle {},{},",expectedTitle ,actualTitle);
    //loginPage.clickSignIn();
    loginPage.login(
        ConfigReader.getProperty("username"),
        ConfigReader.getProperty("password"));
    String expectedTitle1 = "You are logged in"
    		+ "";
	   logger.info("1"); 
    String actualTitle1 = HomeDS.clickAlertR();
    logger.info("2");
    Assert.assertEquals(expectedTitle1 ,actualTitle1);
    logger.info("expectedTitle {},{},{}",expectedTitle ,actualTitle);
    
    
   }
   //==================After sign in===============================
  @Test(dataProvider = "codeProvider", dataProviderClass = utilities.TestDataProvider.class)

   public void VerifySigninAfter(String topic) { 
	  
	  
	    HomeDS.clickSignInlinkR();
	    String expectedTitle = "Login";
		logger.info("1");
	    String actualTitle = HomeDS.getPagesTitle();
	    logger.info("2");
	    Assert.assertEquals(expectedTitle ,actualTitle);
	    logger.info("expectedTitle {},{},",expectedTitle ,actualTitle);
	    loginPage.login(
	        ConfigReader.getProperty("username"),
	        ConfigReader.getProperty("password"));
	   
	    HomeDS.clickDDArrayBS();
	    HomeDS.clickModule(topic);
	       String actualTitle2=HomeDS.getPagesTitle();
	       Assert.assertTrue(
	    		    actualTitle2.toLowerCase().contains(topic.toLowerCase()) ||
	    		    topic.toLowerCase().contains(actualTitle2.toLowerCase()),
	    		    "Mismatch: topic=" + topic + ", actual=" + actualTitle2
	    		);
		    logger.info("expectedTitle {},{},{}",topic,actualTitle2);
	       
   }
   
  @AfterMethod
  public void tearDown() {
	  
      if (driver != null) {
          driver.quit(); 
      }
  }
}
