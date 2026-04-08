package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.Map;

import factory.DriverFactory;
import utilities.ConfigReader;
import utilities.ExcelReader;

public class LoginPage {

    WebDriver driver;
    ConfigReader configReader;
    static Map<String, Map<String, String>> testData;
  
    public LoginPage() {
    	this.driver = DriverFactory.getDriver();
 
    	//this.driver=driver;
       

    	ConfigReader.loadConfig();
    }

    // Locators
    private By getStartedBtn = By.linkText("Get Started");
    private By signInLink = By.linkText("Sign in");
    private By usernameField = By.id("id_username");
    private By passwordField = By.id("id_password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By logoutLink = By.linkText("Sign out");
    private By errorMsg = By.xpath("//div[contains(@class,'alert')]");

    // Actions

    /** Open DS Algo portal */
    public void openPortal() {
        driver.get(ConfigReader.getProperty("url"));
    }

    /** Click Get Started button on homepage */
    public void clickGetStarted() {
        driver.findElement(getStartedBtn).click();
    }

    /** Click Sign in link */
    public void clickSignIn() {
        driver.findElement(signInLink).click();
    }

    /** Verify Login page is displayed */
    public boolean isLoginPageDisplayed() {
        return driver.getTitle().contains("Login");
    }

    /** Enter username */
    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    /** Enter password */
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    /** Click Login button */
    public void clickLoginButton() {
        driver.findElement(loginBtn).click();
    }

    /** Verify login is successful */
    public boolean isLoginSuccessful() {
        return driver.findElements(logoutLink).size() > 0;
    }

    /** Verify login error is displayed */
    public boolean isLoginErrorDisplayed() {
        return driver.findElements(errorMsg).size() > 0;  
    }
    
    public void successfulLogin() {
    	if (testData == null) {
            testData = ExcelReader.getLoginData();
        }
    	Map<String, String> validCreds = testData.get("Login_Valid");
	    enterUsername(validCreds.get("Username"));
	    enterPassword(validCreds.get("Password"));
	    clickLoginButton();
    }
    
    public void waitForSuccessfulLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("home"));
    }
    
      // Check whether Login button is enabled
    public boolean isLoginButtonEnabled() {
        return driver.findElement(loginBtn).isEnabled();
    }

        //  Check password field masking
    public String getPasswordFieldType() {
        return driver.findElement(passwordField).getAttribute("type");
    }

}
