package testSuite;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.StackPage;
import pageObjects.TryEditorPage;
import utilities.TestDataProvider;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StackTest extends BaseTest {

	private static final Logger logger = LogManager.getLogger(StackTest.class);
	StackPage stackPage;

	@BeforeMethod(dependsOnMethods = { "baseSetup", "dsAlgoLogin" })
	public void setup() {
		stackPage = new StackPage();
		stackPage.clickGetStartedStack();
	}

	@Test(priority = 0, dataProvider = "stackTopics", dataProviderClass = TestDataProvider.class)
	public void testStackTopics(String[] topics) {
		for (int i = 0; i < topics.length; i++) {
			stackPage.clickTopic(topics[i]);
			Assert.assertTrue(stackPage.isCorrectPageDisplayed(topics[i]),
					"Expected " + topics[i] + " to be displayed, but its not displayed.");
		}
	}

	@Test(priority = 1)
	public void testPracticeQuestionsLink() {
		stackPage.clickTopic("Implementation");
		stackPage.clickPracticeQuestions();
		Assert.assertTrue(stackPage.driver.getTitle().contains("Practice"),
				"Expected Praction questions page to be displayed, but its not displayed.");
	}

	@Test(priority = 2, dataProvider = "validPythonCode", dataProviderClass = TestDataProvider.class)
	public void testValidCodeInPythonEditor(Map<String, String> editorData) {
		stackPage.clickTopic("Implementation");
		stackPage.clickTryHere();
		
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
		stackPage.clickTopic("Implementation");
		stackPage.clickTryHere();
		
		TryEditorPage editorPage = new TryEditorPage();
		String pythonCode = editorData.get("PythonCode");
		editorPage.enterCode(pythonCode);
        editorPage.clickRun();

		editorPage.acceptAlert();
		Assert.assertTrue(editorPage.isRunButtonDisplayed(),
				"Expected to display run button, but not displayed.");
	}
}
