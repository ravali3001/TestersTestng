
	package listeners;

	import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
	import org.testng.ITestResult;

;

	public class chainTestListener implements ITestListener {
		private static final Logger logger = LogManager.getLogger(chainTestListener.class);

	    @Override
	    public void onTestStart(ITestResult result) {
	     logger.info("******* chainTest Started:$$$$$ " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        logger.info("********chainTest Passed:$$$$$$ " + result.getName());
	    }
	}

