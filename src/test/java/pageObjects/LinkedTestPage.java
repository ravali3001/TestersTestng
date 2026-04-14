package pageObjects;
    import java.time.Duration;

	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.NoAlertPresentException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Wait;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;

	public class LinkedTestPage {
		 WebDriver driver;

		    public LinkedTestPage(WebDriver driver) {
		        this.driver = driver;
		    }
	//locators
		    private By getStartedlinkedlist = By.xpath("//a[@href='linked-list']");
		    private By Introductionlink = By.xpath("//a[text()='Introduction']");
		    private By CreatingLinkedLIstlink = By.linkText("Creating Linked LIst");
		    private By TypesofLinkedLIstlink = By.linkText("Types of Linked List");
		    private By ImplementLinkedListinPythonlink = By.linkText("Implement Linked List in Python");
		    private By Traversallink = By.linkText("Traversal");
		    private By Insertionlink = By.linkText("Insertion");
		    private By Deletionlink = By.linkText("Deletion");
		    private By PracticeQ= By.xpath("//a[@href='/linked-list/practice']");
		    private By run=By.xpath("//button[@onclick='runit()']");
		    private By here= By.xpath("//a[@href='/tryEditor']");
		    private By Tryhere=By.linkText("Try here");
		    private By Tryhere1=By.xpath("//a[text()='Try here>>>']");
		    private By editor = By.xpath("//div[contains(@class,'CodeMirror')]//textarea");
		    
		    
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  
		    public void clickGetStartedLlinkedlist() {
		driver.findElement(getStartedlinkedlist).click();
	}
	public void clickTopic(String topic) {
	    switch (topic) {
	        case "Introduction":
	            driver.findElement(Introductionlink).click();
	            break;
	        case "Creating Linked LIst":
	            driver.findElement(CreatingLinkedLIstlink).click();
	            break;
	        case "Types of Linked List":
	            driver.findElement(TypesofLinkedLIstlink).click();
	            break;
	        case "Implement Linked List in Python":
	            driver.findElement(ImplementLinkedListinPythonlink).click();
	            break;
	        case "Traversal":
	            driver.findElement(Traversallink).click();
	            break;
	        case "Insertion":
	            driver.findElement(Insertionlink).click();
	            break;
	            
	        case "Deletion":
	            driver.findElement(Deletionlink).click();
	            break;
	       
	    }}
	   //0003
	    public void clickPractice() {
	    	driver.findElement(Introductionlink).click();
	    	driver.findElement(PracticeQ).click();
	    
	}
	  //0004
	    public void here() {
	    	driver.findElement(Introductionlink).click();
	    	//System.out.println("The code is --" + TestCaseName);
			//driver.findElement(here).sendKeys(here1);
	    	WebElement txt=driver.findElement(Tryhere1);
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	js.executeScript("arguments[0].scrollIntoView(true);", txt);
		txt.click();
		//driver.findElement(editor).sendKeys(TestCaseName);
	    }

		public void clickRunButton() { 
			driver.findElement(run).click();
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

		public String getPageTitle() {
	    	return driver.getTitle();
	    }
	    

	}   
	    




