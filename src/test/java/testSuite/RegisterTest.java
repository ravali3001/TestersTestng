package testSuite;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import factory.DriverFactory;



public class RegisterTest {
	//WebDriver driver;
//	driver = DriverFactory.getDriver();
	@Test(priority=1)
	void reg1()
	{
		System.out.println("REGISTER");	
	WebDriver driver;
		driver = DriverFactory.getDriver();
	}

}
