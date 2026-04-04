package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QueuePage {

    private WebDriver driver;

    public QueuePage(WebDriver driver) {
        this.driver = driver;
    }

    // ---------------- Locators ----------------
    private By getStartedQueue = By.xpath("//a[@href='queue']");
    private By practiceQuestionsBtn = By.linkText("Practice Questions");
    private By tryHereBtn = By.linkText("Try here>>>");
    

    // ---------------- Methods ----------------

    // Click Get Started on Queue module
    public void clickQueueGetStarted() {
        driver.findElement(getStartedQueue).click();
    }

    // Verify if on Queue page
    public boolean isOnQueuePage() {
        return driver.getCurrentUrl().toLowerCase().contains("queue");
    }

    // Click on a topic link dynamically
    public void clickTopic(String topic) {
    	By topicElement = By.linkText(topic);
    	driver.findElement(topicElement).click();
    }

    // Verify if on a topic page (by page title or URL)
    public boolean isOnTopicPage(String expectedPage) {
        return driver.getTitle().toLowerCase().contains(expectedPage.toLowerCase()) ||
               driver.getCurrentUrl().toLowerCase().contains(expectedPage.toLowerCase());
    }

    // Click Try Here button on topic page
    public void clickTryHere() {
        driver.findElement(tryHereBtn).click();
    }

    // Verify if on TryEditor page
    public boolean isOnTryEditorPage() {
        return driver.getCurrentUrl().toLowerCase().contains("tryeditor");
    }

    // Click Practice Questions button
    public void clickPracticeQuestions() {
        driver.findElement(practiceQuestionsBtn).click();
    }

    // Verify if on Practice Questions page
    public boolean isOnPracticeQuestionsPage() {
        return driver.getCurrentUrl().toLowerCase().contains("practice");
    }
}
