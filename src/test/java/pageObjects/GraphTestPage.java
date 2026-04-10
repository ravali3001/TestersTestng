package pageObjects;




	import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.NoAlertPresentException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.By.ById;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Wait;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import java.time.Duration;

	public class GraphTestPage {

		WebDriver driver;
	    private static final Logger logger = LogManager.getLogger(GraphTestPage.class); 


		public GraphTestPage(WebDriver driver) {
			this.driver = driver;
		}

	//Locators
		private By graphLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Graph']");
		private By graphrePresentations = By.xpath("//a[normalize-space()='Graph Representations']");
		private By getStartedGraph = By.xpath("//a[@href='graph']");
		private By tryHereBtn = ById.xpath("//a[@href='/tryEditor']");
		private By editor = By.xpath("//div[contains(@class,'CodeMirror')]//textarea");
		// private By codeEditor = By.xpath("//textarea[@autocorrect='off']");
		private By runButton = By.xpath("//button[text()='Run']");
		private By output = By.xpath("//*[@id='output']");
		private By practice = By.xpath("//a[@href='/graph/practice']");
		
		
		// Actions

		public void clickGetStarted() {
			driver.findElement(getStartedGraph).click();

		}

		public void clickTopic(String topic) {

			switch (topic) {
			case "Graph":
				driver.findElement(graphLink).click();
				logger.info("The topic is --" + topic);
				break;
			case "Graph Representations":
				driver.findElement(graphrePresentations).click();
				logger.info("The topic is --" + topic);
				break;
			}
		}
public void graphLink() {
	driver.findElement(graphLink).click();
}
public void graphReprasentation() {
	driver.findElement(graphrePresentations).click();
}	
		public String getPageTitle() {
			String title = driver.getTitle();
			return title;
		}

		// 0001
		public void clickTryHere() {
			WebElement tryHere = driver.findElement(tryHereBtn);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", tryHere);
			tryHere.click();

		}

		public void navigateToTryHere() {
			driver.findElement(graphLink).click();
			clickTryHere();
		}

		public boolean isButtonDisplayed() {
			return driver.findElement(runButton).isDisplayed();
		}

		// 0002
		public void enterCode(String code) {
			logger.info("The code is --" + code);
			driver.findElement(editor).sendKeys(code);
		}

		public void clickRunButton() {
			driver.findElement(runButton).click();
		}

		public String getAlertMessage() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			try {
				Alert alert = wait.until(ExpectedConditions.alertIsPresent());
				//alert = driver.switchTo().alert();
				String text = alert.getText();
				alert.accept();
				return text;
			} catch (NoAlertPresentException e) {
				throw new AssertionError("Expected alert is not present!");
			}
		}
		public String getOutput() {
	    	//return output.getText();	    	
	    String result = "";  
	        try {
	            Alert alert = driver.switchTo().alert();
	            result = alert.getText();
	            logger.info("Alert Text: " + result);
	            alert.accept();
	        } catch (NoAlertPresentException e) {
	           
	            result = driver.findElement(output).getText();  
	           logger.info("Editor Output: " + result);
	        }

	        return result;
	    
	    }
	    
		public String getOutPutText() {
			return driver.findElement(output).getText().toString();
		}
	//0003
	public void clickPracticalQuestions() {
		driver.findElement(graphLink).click();
		driver.findElement(practice).click();
	}


	}


