package testSuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class LoginTest extends BaseTest {
	@Test()
	public void testValidLogin() {
		Assert.assertTrue(true, "Basic test to verify test execution.");
	}
}
