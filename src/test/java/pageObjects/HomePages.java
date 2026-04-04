package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePages {
	WebDriver driver;
	 WebDriverWait wait;
	
	 private static final Logger logger = LogManager.getLogger(HomePages.class); 

	public HomePages(WebDriver driver) { 
	      this.driver = driver;
	      this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	private By LaunchGetstart=By.linkText("Get Started");
	private By RegisterHm=By.xpath("//a[@href='/register']");
	private By dropDown1=By.linkText("//div[@class='nav-item dropdown show']");
	private By dropDown=By.linkText("dropdown-item");
	private By DSdropdown=By.xpath("//a[@href='#']");
	private	By Arrays=By.linkText("Arrays");
	private	By Linked=By.linkText("Linked List");
   private By stackHm=By.linkText("Stack");
	private	By QueueHm=By.linkText("Queue");
	private By GraphHm=By.linkText("Graph");
  private By AlertHm=By.xpath("//div[@role='alert']");
  private By SigninHm=By.linkText("Sign in"); 
  private By usernameField = By.id("id_username");
  private By passwordField = By.id("id_password");
  private By loginBtn = By.xpath("//input[@value='Login']");
  private By logoutLink = By.xpath("//a[@href='/logout']");
  private  By errorMsg = By.xpath("//div[contains(@class,'alert')]"); 
  private By Error=By.xpath("/html/body/div[2]");
  private By SignOut=By.linkText("Sign out");
		  //Actions action = new Actions(driver);
		

    
 public void clickLaunchStartR() { 
	  driver.findElement(LaunchGetstart).click(); 
	  
 }
   public void clickRegisterR() { 
   	driver.findElement(RegisterHm).click(); 
   	
   	
   }
   public void clickSignInlinkR() {
   	driver.findElement(SigninHm).click();
   }
   
  
   public void clickDDArrayBS() {
   	driver.findElement(DSdropdown).click();
   }
   
   
   public void clickModule(String moduleName) {
   	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

   	    // Open dropdown first
   	   /* driver.findElement(DSdropdown).click();*/
      	 wait.until(ExpectedConditions.elementToBeClickable(DSdropdown)).click();

   	    // Click module
   	    By module = By.xpath("//a[normalize-space()='" + moduleName + "']");
   	    wait.until(ExpectedConditions.elementToBeClickable(module)).click();
   	
   	    // Open dropdown first
   	// wait.until(ExpectedConditions.elementToBeClickable(DSdropdown)).click();
   	   //By module = By.linkText(moduleName);
         //wait.until(ExpectedConditions.elementToBeClickable(module)).click();
   }
   
  
   public void clickArrayR() {
		  wait.until(ExpectedConditions.elementToBeClickable(Arrays)).click();	
   }

   
   public void clickLinkedlistR() {
		  wait.until(ExpectedConditions.elementToBeClickable(Linked)).click();
   }
   
 
   public void clickStackR(){	  
		  wait.until(ExpectedConditions.elementToBeClickable(stackHm)).click();	 
   }
   
   
   public void clickQueueR() {
		  wait.until(ExpectedConditions.elementToBeClickable(QueueHm)).click();
   }
    
   
   public void clickAlertR() { 	
      WebElement result = driver.findElement(Error); 
	   String text = result.getText();
   logger.info("Result Message: " + text); 
   }
   
   
 public void ClickSigninR() {
   	driver.findElement(SigninHm).click();
   }
 
 
 public void clickUserPassR(String username, String password) {
   	driver.findElement(usernameField).clear();
       driver.findElement(usernameField).sendKeys(username);
   	driver.findElement(passwordField).clear();
   	driver.findElement(passwordField).sendKeys(password);

   }
 
 
public void ClickLoginR() {
   	driver.findElement(loginBtn).click();
   	
   }


 public void ClickLogoutR() {
   	WebElement logout = wait.until(
   	        ExpectedConditions.visibilityOfElementLocated(
   	                SignOut
   	        )
   	);
       logout.click(); 
   	logger.info("Logout button displayed: " + logout.isDisplayed());
   }
 
 public String getPagesTitle() {
	  
 	return driver.getTitle();
 	
 }
 public String getMessage() {
     return driver.findElement(AlertHm).getText();
 }
   
	}



