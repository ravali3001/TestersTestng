package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import utilities.ExtentManager;

public class TestListener implements ITestListener { 
	private static final Logger logger = LogManager.getLogger(TestListener.class);


	@Override
	public void onStart(ITestContext context) { 
		logger.info("*******Test Suite Started: " + context.getName());

	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("********Test Started: " + result.getName());
		ExtentManager.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("*******Test Passed: " + result.getName());
		ExtentManager.createTest(result.getMethod().getMethodName()).log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("********Test Failed: " + result.getName());
		ExtentManager.createTest(result.getMethod().getMethodName()).log(Status.FAIL,
				"Test Failed: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("********Test Skipped: " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("*******Test Suite Finished: " + context.getName());
		ExtentManager.getExtentReports().flush();
	}
}
