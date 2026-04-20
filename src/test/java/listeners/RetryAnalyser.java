package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
	private static final Logger logger = LogManager.getLogger(RetryAnalyser.class);

	private int retryCount = 0;
    private int maxRetries = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && retryCount < maxRetries) {
            retryCount++;
            logger.info("Retry #" + retryCount + " for test " + result.getName());
            return true;
        }
        return false;
    }	
}
