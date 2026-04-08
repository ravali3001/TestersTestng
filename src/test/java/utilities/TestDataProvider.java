package utilities;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	private Object[][] getDataForTestCase(String testCaseName) {
		Object[][] data = new Object[1][1];
		data[0][0] = ExcelReader.getLoginData().get(testCaseName);
		return data;
	}

	@DataProvider(name = "validLogin")
	public Object[][] validLogin() {
		return getDataForTestCase("Login_Valid");
	}

	@DataProvider(name = "invalidLogin")
	public Object[][] invalidLogin() {
		return getDataForTestCase("Login_Invalid");
	}

	@DataProvider(name = "invalidUsername")
	public Object[][] invalidUsername() {
		return getDataForTestCase("Login_Invalid_Username");
	}

	@DataProvider(name = "invalidPassword")
	public Object[][] invalidPassword() {
		return getDataForTestCase("Login_Invalid_Password");
	}
}
