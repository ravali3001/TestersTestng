package pageObjects;




	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;

import factory.DriverFactory;

	public class TryEditorPage {

	    WebDriver driver;

	    public TryEditorPage() {
	        this.driver = DriverFactory.getDriver();
	    }

	    // Locators
	    private By codeTextArea = By.xpath("//textarea[@tabindex='0']");
	    private By runButton = By.xpath("//button[text() = 'Run']");
	    private By output = By.id("output");
	    private By submit=By.xpath("//input[@type='submit']");


	    public void enterCodeTest(String code) {
	        driver.findElement(codeTextArea).clear();
	        driver.findElement(codeTextArea).sendKeys(code);
	    }

	    public void clickRun() {
	        driver.findElement(runButton).click();
	    }

	    public String getOutput() {
	        return driver.findElement(output).getText();
	    }

	    public boolean isRunButtonDisplayed() {
	        return driver.findElements(runButton).size() > 0;
	    }

	    public void acceptAlert() {
	        driver.switchTo().alert().accept();
	    }
	    public void submit() {
	    	driver.findElement(submit).click();
	    }
	    
		public void enterCode(String code) {
			WebElement element = driver.findElement(codeTextArea);
			new Actions(driver).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
			String[] string1 = code.split("\n");
			for (int i = 0; i < string1.length; i++) {
				if (string1[i].equalsIgnoreCase("\\b")) {
					element.sendKeys(Keys.BACK_SPACE);
				} else {
					element.sendKeys(string1[i]);
					element.sendKeys(Keys.RETURN);
				}
			}
		
		}
		
		
	}



