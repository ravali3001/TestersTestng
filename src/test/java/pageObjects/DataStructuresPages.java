package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.DriverFactory;

public class DataStructuresPages {
	 WebDriver driver;
	 WebDriverWait wait;
	 private static final Logger logger = LogManager.getLogger(DataStructuresPages.class); 

	 
	 public DataStructuresPages() { 
          this.driver = DriverFactory.getDriver();
	      this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 }
	
	By DsIntro = By.xpath("//a[@href='data-structures-introduction' and normalize-space()='Get Started']");
	By Timecomplexity =By.xpath("//a[@href='time-complexity']");
	By practice=By.xpath("//a[@href='/data-structures-introduction/practice']");
	By TryHereDS=By.xpath("//a[@href='/tryEditor']");
	By TryHere=By.xpath("//a[contains(text(),'Try here')]");
	By TRYhereDS=By.xpath("//a[text()='Try here>>>']");
	By editor1=By.xpath("//pre[@class=' CodeMirror-line ']");
	private By editorDS = By.xpath("//div[contains(@class,'CodeMirror')]//textarea");
	private By ClickRunBtn=By.xpath("//button[@type='button']");
	private By output = By.xpath("//pre[@id='output']");
	private By DStitle=By.xpath("//h4[text()='Data Structures-Introduction']");
	private By DSlogo=By.xpath("//a[@href='/']");
	private By DSlink=By.xpath("/html/body/div[1]/nav/div/div[2]/ul/a[2]");
	private By DSdrop1=By.xpath("//a[@href='#']");
	private By DSbutton=By.xpath("//a[@href='/logout']");

	

	
	public String isTitleDisplayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(DStitle)).getText();
    }
	
	public String islogoDisplayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(DSlogo)).getText();
    }
	public String islinkDisplayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(DSlink)).getText();
    }
	public String isbuttonDisplayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(DSbutton)).getText();
    }
	public String isdropdownDisplayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(DSdrop1)).getText();
    }
	public void DSClickIntro() {
		driver.findElement(DsIntro).click();
		
	}
	public void DSClickTimeComplexity() {
		driver.findElement(Timecomplexity).click();
		
	}

	public void DSClickPracticeQ() {
		driver.findElement(Timecomplexity).click();

		driver.findElement(practice).click();
	}
	public void DSClickTryhere() {
	WebElement txt2=driver.findElement(TryHere);
    	
	Actions actions = new Actions(driver);
	actions.moveToElement(txt2).perform();
	txt2.click();
	}
	public void enterCode(String Code) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(editor1));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);",
            Code);
		
}
	public void clickRunButton() {
       driver.findElement(ClickRunBtn).click();
    }

	public String getResult() {

	    try {
	        Alert alert = driver.switchTo().alert();
	        String alertText = alert.getText();
	        logger.info("Alert Text: " + alertText);  // print
	        alert.accept();
	        return alertText;

	    } catch (NoAlertPresentException e) {
	    	String outputText = driver.findElement(output).getText();  // get text
	    	 logger.info("Output Text: " + outputText);          // print text
	        return outputText;
	       
	    }
	}
     public String getOutput() {
    	WebElement txt3=driver.findElement(output);
    	
	    String result = "";  
	        try {
	            Alert alert = driver.switchTo().alert();
	            result = alert.getText();
	            logger.info("*******Alert Text******: " + result);
	            alert.accept();
	        } catch (NoAlertPresentException e) {
	           
	            result = driver.findElement(output).getText();  
	            logger.info("******Editor Output*****: " + result);
	        }

	        return result;
	    
	    }
    
    public String getPageTitle() {
    	return driver.getTitle();
    }
    public boolean isTextDisplayed(String text) {
        return driver.findElement(DStitle).isDisplayed();
    }
    
    public String getTryEditorResult() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return "Error";  // or return alertText if needed
        } catch (NoAlertPresentException e) {
            return driver.findElement(output).getText();
        }
    }
    
}





