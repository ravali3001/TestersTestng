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
	import org.openqa.selenium.support.ui.WebDriverWait;

import factory.DriverFactory;


	public class TreePages {
		private static final Logger logger = LogManager.getLogger(TreePages.class);  
		WebDriver driver;
	   public TreePages() {
			this.driver = DriverFactory.getDriver();
	   }
	private	By output = By.xpath("//pre[@id='output']");

	//Locators
	private By getStartedTree = By.xpath("//a[@href='tree']");
	private By overview = By.xpath("//a[@href='overview-of-trees']");
	//private By term = By.linkText("terminologies");
	private By term = By.linkText("Terminologies");
	private By types = By.linkText("Types of Trees");
	private By tree = By.linkText("Tree Traversals");
	private By traversals = By.linkText("Traversals-Illustration");
	private By binary= By.linkText("Binary Trees");
	private By typesofbinary = By.linkText("Types of Binary Trees");
	private By imple = By.linkText("Implementation in Python");
	private By binarytree = By.linkText("Binary Tree Traversals");
	private By impletrees = By.linkText("Implementation of Binary Trees");
	private By applications = By.linkText("Applications of Binary trees");
	private By binarysearch = By.linkText("Binary Search Trees");
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	private By implementation = By.linkText("Implementation Of BST");
	//0002
	private By hereT = By.xpath("//a[text()='Try here>>>' and @href='/tryEditor']");
	private By run=By.xpath("//button[@onclick='runit()']");
	//Called method
	public void clickGetStarted(){
		driver.findElement(getStartedTree).click();
	}
	
		
	public void overviewoftree() {
		driver.findElement(overview).click();
	}
	public void terminologies() {
		driver.findElement(term).click();
	}
	public void typesoftrees() {
		driver.findElement(types).click();
	}
	public void tree1() {
		driver.findElement(tree).click();
	}
	public void traversals1() {
		driver.findElement(traversals).click();
	}
	public void binary1() {
		driver.findElement(binary).click();
	}
	public void types1() {
		driver.findElement( typesofbinary).click();
	}
	public void imple1() {
		driver.findElement(imple).click();
	}
	public void binarytree1() {
		driver.findElement(binarytree).click();
	}
	public void impletrees1() {
		driver.findElement(impletrees).click();
	}
	public void apply1() {
		driver.findElement(applications).click();
	}
	public void binarysearch1() {
		driver.findElement(binarysearch).click();
	}
	public void implementation1(){
		driver.findElement(implementation).click();
	}
	//0002
	public void hereT() {
	//driver.findElement(By.xpath("//a[text()='Try here>>>' and @href='/tryEditor']")).click();
	//driver.findElement(hereT).click();
		driver.findElement(overview).click();
		WebElement Tree2 = driver.findElement(hereT);
		   JavascriptExecutor js = (JavascriptExecutor) driver;
	 	js.executeScript("arguments[0].scrollIntoView(true);", Tree2);
		   Tree2.click();}
		   
		   public String getPageTitle() {
		    	return driver.getTitle();
		   }
		    	public String getOutput() {
			    	//return output.getText();	    	
			     String result = "";  
			        try {
			            Alert alert = driver.switchTo().alert();
			            result = alert.getText();
			            logger.info("****Alert Text:***** " + result);
			            alert.accept();
			        } catch (NoAlertPresentException e) {
			           
			            result = driver.findElement(output).getText();  
			            logger.info("******Editor Output:***** " + result);
			        }

			        return result;
			    
			    } 	
	}







