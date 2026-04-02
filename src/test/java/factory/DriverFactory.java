package factory;


	

	import java.time.Duration;


	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;

	import utilities.ConfigReader;

	public class DriverFactory {

		private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
		private static ThreadLocal<String> browserType = new ThreadLocal<>();
		private static final Logger logger = LogManager.getLogger(DriverFactory.class);
		
		public static void setBrowser(String browser) {
			browserType.set(browser);
		}
	   
		public static void initDriver() {
			String browser = browserType.get();

			if (browser != null && browser.isBlank()) {
				browser = ConfigReader.getProperty("browser");
			}
			logger.info("Running in :" + browser + ">browsers");
			switch (browser.toLowerCase()) {

			case "chrome":
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disk-cache-size=0");
				options.addArguments("--disk-cache-size=0");
				driver.set(new ChromeDriver(options));
				logger.info("launching chrome browser");
				break;

			case "edge":
				driver.set(new EdgeDriver());
				logger.info("launching edge browser");      
				break;

			case "firefox":
				driver.set(new FirefoxDriver()); 
				break;

			default:
				throw new RuntimeException("Browser not supported: " + browser);
			}

			getDriver().get(ConfigReader.getProperty("url"));
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}

		public static WebDriver getDriver() {
			return driver.get();
		}

		public static void setBrowserName(String browserName) {
			
		}

		public static void quitDriver() {
			if (driver.get() != null) {
				driver.get().quit();
				driver.remove();
			}
		}
	}



