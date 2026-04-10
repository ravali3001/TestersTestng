package testSuite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.QueuePage;
import pageObjects.TryEditorPage;
import utilities.TestDataProvider;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class QueueTest extends BaseTest {

	private static final Logger logger = LogManager.getLogger(StackTest.class);
	QueuePage queuePage;

	@BeforeMethod(dependsOnMethods = { "baseSetup", "dsAlgoLogin" })
	public void setup() {
		queuePage = new QueuePage();
		queuePage.clickQueueGetStarted();
	}
	@Test(priority = 0, dataProvider = "queueTopics", dataProviderClass = TestDataProvider.class)
	public void testQueueTopics(String[] topics) {
		for (int i = 0; i < topics.length; i++) {
			queuePage.clickTopic(topics[i]);
			Assert.assertTrue(queuePage.isOnTopicPage(topics[i]),
					"Expected " + topics[i] + " to be displayed, but its not displayed.");
		}
	}
	@Test(priority = 1)
	public void testPracticeQuestionsLink() {
		queuePage.clickTopic("Implementation of Queue in Python");
		queuePage.clickPracticeQuestions();
		Assert.assertTrue(queuePage.isOnPracticeQuestionsPage(),
				"Expected Praction questions page to be displayed, but its not displayed.");
	}
	@Test(priority = 2, dataProvider = "validPythonCode", dataProviderClass = TestDataProvider.class)
	public void testValidCodeInPythonEditor(Map<String, String> editorData) {
		queuePage.clickTopic("Implementation of Queue in Python");
		queuePage.clickTryHere();
		
		TryEditorPage editorPage = new TryEditorPage();
		String pythonCode = editorData.get("PythonCode");
		editorPage.enterCode(pythonCode);
        editorPage.clickRun();

		String output = editorPage.getOutput();
		Assert.assertTrue(output.equals(editorData.get("ExpectedOutput")),
				"Expected Success but failed.");
	}
	@Test(priority = 3, dataProvider = "invalidPythonCode", dataProviderClass = TestDataProvider.class)
	public void testInvalidCodeInPythonEditor(Map<String, String> editorData) {
		queuePage.clickTopic("Implementation of Queue in Python");
		queuePage.clickTryHere();
		
		TryEditorPage editorPage = new TryEditorPage();
		String pythonCode = editorData.get("PythonCode");
		editorPage.enterCode(pythonCode);
        editorPage.clickRun();

		editorPage.acceptAlert();
		Assert.assertTrue(editorPage.isRunButtonDisplayed(),
				"Expected to display run button, but not displayed.");
	}
}