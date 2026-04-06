package pageObjects;

	import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import factory.DriverFactory;

	public class RegisterTestPage {

		    // Locators
		    private By registerLink = By.linkText("Register");
		    private By Registerlink=By.xpath("//a[@href=\'/register\']");
		    private By usernameField = By.id("id_username");
		    private By passwordField = By.id("id_password1");
		    private By confirmPasswordField = By.xpath("//input[@name='password2']");
		    private By registerButton = By.xpath("//input[@type='submit']");
		    private By successMessage = By.id("successMessage");
		    private By errorMessage = By.xpath("//div[@class='alert alert-primary']");
		    private By ErrMsg=By.xpath("//div[@role='alert']");
		    private  By errorMsg = By.xpath("//div[contains(@class,'alert')]"); 
		    private By passwordErrorMessage = By.id("passwordError"); 
            private By loginTxt=By.xpath("//a[text()='Login ']");
		    private WebDriver driver;
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        public RegisterTestPage(WebDriver driver) { 
		    	
		        this.driver =driver;
		    }
		    
		    
		     // Actions
		    public void clickRegisterLink() {
		        driver.findElement(Registerlink).click(); 
		    }
	        public void getintohomepage() {
		    // driver.get("https://dsportalapp.herokuapp.com/home"); // replace with real URL
	        }
	        public boolean isRegistrationPageDisplayed() {

	            System.out.println("Current URL: " + driver.getCurrentUrl());
	            System.out.println("Page Title: " + driver.getTitle());

	            return driver.getCurrentUrl().contains("register");
	        }
	 
		    /*public boolean isRegistrationPageDisplayed() {
		    	
		        return driver.getTitle().contains("register");
		    }*/

		    public void enterUsername(String username) {
		    	WebElement userField = driver.findElement(usernameField);
		    	userField.clear();
		    	userField.sendKeys(username);
		    	
		    }

		    public void enterPassword(String password) {
		    	WebElement passField = driver.findElement(passwordField);
		    	passField.clear();
		    	passField.sendKeys(password);
		       
		    }

		    public void enterConfirmPassword(String password) {
		    	WebElement pass1Field = driver.findElement(confirmPasswordField);
		    	pass1Field.clear();
		    	pass1Field.sendKeys(password);
		        
		    }

		    public void clickRegisterButton() {
		        driver.findElement(registerButton).click();
		    }

		    public boolean isSuccessMessageDisplayed() {
		        return driver.findElement(successMessage).isDisplayed();
		    }

		    public String getPasswordErrorMessage() {
		        return driver.findElement(errorMessage).getText();
		    }
		    public String getAlertText() {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//div[contains(@class,'alert')]")
		        ));
		        return alert.getText();
		    }
		        public void clickAlertR() { 
		       	
		            WebElement result = driver.findElement(errorMsg); 
		      	   String text = result.getText();
		            System.out.println("Result Message: " + text);
		         }
		    
		       // public String getAlertMessage() {
		        //	By alertMessage;
		          //  return wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage)).getText();
          // }

		        public String getValidationMessage(By field) {
		            WebElement element = driver.findElement(field);
		            System.out.println(element);
		            return element.getAttribute("validationMessage");
		        }

		        // Specific Field Validation Methods
		        public String getUsernameValidationMessage() {
		        	
		            return driver.findElement(usernameField).getAttribute("validationMessage");
		        }

		        public String getPasswordValidationMessage() {
		            return driver.findElement(passwordField).getAttribute("validationMessage");
		        }

		        public String getConfirmPasswordValidationMessage() {
		            return driver.findElement(confirmPasswordField).getAttribute("validationMessage");
		        }
		        public void LoginClick() {
		        	driver.findElement(loginTxt).click();
		        }
		    }
		   
		

 
