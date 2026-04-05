package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StackPage {

    WebDriver driver;

    public StackPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By operationsLink = By.linkText("Operations in Stack");
    private By implementationLink = By.linkText("Implementation");
    private By applicationsLink = By.linkText("Applications");
    private By tryHereBtn = By.linkText("Try here>>>");
    private By practiceQuestionsBtn = By.linkText("Practice Questions");
    private By getStartedStack = By.xpath("//a[@href='stack']");

    // Actions
    
    public void clickGetStartedStack() {
    	driver.findElement(getStartedStack).click();
	}
    
    public void clickTopic(String topic) {
        switch (topic) {
            case "Operations in Stack":
                driver.findElement(operationsLink).click();
                break;
            case "Implementation":
                driver.findElement(implementationLink).click();
                break;
            case "Applications":
                driver.findElement(applicationsLink).click();
                break;
        }
    }

    public boolean isCorrectPageDisplayed(String expectedPage) {
        return driver.getTitle().toLowerCase().contains(expectedPage.toLowerCase());
    }

    public void clickTryHere() {
        driver.findElement(tryHereBtn).click();
    }

    public void clickPracticeQuestions() {
        driver.findElement(practiceQuestionsBtn).click();
    }
}
