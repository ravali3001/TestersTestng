package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import utilities.ExtentManager;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		System.out.println("*******Test Suite Started: " + context.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("********Test Started: " + result.getName());
		ExtentManager.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("*******Test Passed: " + result.getName());
		ExtentManager.createTest(result.getMethod().getMethodName()).log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("********Test Failed: " + result.getName());
		ExtentManager.createTest(result.getMethod().getMethodName()).log(Status.FAIL,
				"Test Failed: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("********Test Skipped: " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("*******Test Suite Finished: " + context.getName());
		ExtentManager.getExtentReports().flush();
	}
}
