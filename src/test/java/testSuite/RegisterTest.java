package testSuite;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import factory.DriverFactory;
import managers.PageObjectManager;
import utilities.ConfigReader;
import utilities.ExcelReader;

public class RegisterTest {
	
	WebDriver driver;
    PageObjectManager pom;
    
    Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
    Map<String, Map<String, String>> arrayData1 = ExcelReader.getArrayTryData();
    private static final Logger logger = LogManager.getLogger(RegisterTest.class);
    
    @BeforeMethod() 
    public void setup() { 
    	
        logger.info("************REGISTER MODULE**********");
    	pom = new PageObjectManager(driver);
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));        
        pom.getLoginPage().clickGetStarted();                
    
             }
    @Test
    public void VerifyLogo() {
    	pom.getRegisterPage().clickRegisterLink();
    	pom.getRegisterPage().numpylogo();
    	String expectedTitle= "Numpy Ninja";
        String actualTitle =pom.getArrayPage().getPageTitle();  
	    Assert.assertEquals(actualTitle, expectedTitle);
		logger.info("*********EXPECTED TITLE********* {} ,{} ,{} ", expectedTitle,"******ACTUAL TITLE******",actualTitle);

    }
    @Test
    public void VerifySigninLink() {
    	pom.getRegisterPage().clickRegisterLink();
    	pom.getRegisterPage().SigninLink();
    	String expectedTitle= "Login";
        String actualTitle =pom.getArrayPage().getPageTitle();  
	    Assert.assertEquals(actualTitle, expectedTitle);
		logger.info("*********EXPECTED TITLE********* {} ,{} ,{} ", expectedTitle,"******ACTUAL TITLE******",actualTitle);

    }
    @Test(dataProvider = "Registration", dataProviderClass = utilities.TestDataProvider.class)

    public void VerifyRegisterLink(String username,String password,String confirmPasswors,String ExpectedTitle) {

    	pom.getRegisterPage().clickRegisterLink();
    	pom.getRegisterPage().enterUsername(username);
    	pom.getRegisterPage().enterPassword(password);
    	pom.getRegisterPage().enterConfirmPassword(password);
    	pom.getRegisterPage().clickRegisterButton();
    	if (username.isEmpty()) {

    	    String validationMsg = pom.getRegisterPage()
    	                             .getUsernameValidationMessage1();

    	    Assert.assertEquals(validationMsg, ExpectedTitle);

    	} else {

    	    String actualAlert = pom.getRegisterPage().getAlertText();

    	    Assert.assertEquals(actualAlert, ExpectedTitle);
 	        logger.info("*******ExpectedTitle******* {} ,{}, {}",ExpectedTitle , "*****actualTitle*****", actualAlert);

    	}
    }
}
