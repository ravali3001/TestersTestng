package hooks;


	import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.testng.ITestContext;
	import org.testng.Reporter;

	import factory.DriverFactory;
	//import io.cucumber.java.After;
	//import io.cucumber.java.Before;
	//import io.cucumber.java.Scenario;
	//import pageObjects.LoginPage;

	public class Hooks {

		private static final Logger logger = LogManager.getLogger(Hooks.class);

		//LoginPage loginPage;

		@BeforeMethod
		//@Before(order = 0)
		public void setUp() {
			logger.info("========== Test Started ==========");
			DriverFactory.initDriver(); 
			logger.info("landed on to the dsalgo portal ");
			//loginPage = new LoginPage();
		}

		@BeforeMethod
		//@Before(value = "@LaunchHome", order = 1)
		public void LaunchHome() {
			//loginPage.clickGetStarted();
			logger.info("landed on to home page: ");
		}

		//@BeforeMethod(alwaysRun = "@ValidLogin", order = 2)
		public void validLogin() {
			//loginPage.clickSignIn();
			//loginPage.successfulLogin();
			logger.info("successfull login landed on homepage: ");
		}

		@AfterMethod
		//public void tearDown() {
		public void tearDown(Scenario scenario) {
			logger.info("===== Test Finished =====");

			if (scenario.isFailed()) {

				String fileName = scenario.getName().replaceAll(" ", "_");

				byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);

				scenario.attach(screenshot, "image/png", fileName);
			}
			System.out.println("Driver before quit: " + DriverFactory.getDriver());
			DriverFactory.quitDriver();
		}
	}



