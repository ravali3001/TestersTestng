package utilities;


import java.util.Map;

import org.testng.annotations.DataProvider;


public class TestDataProvider {

    Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
    Map<String, Map<String, String>> arrayData1 = ExcelReader.getArrayTryData();

	
    @DataProvider(name = "arrayPracticeData")
	    public Object[][] getArrayData() {
	        Object[][] data = new Object[arrayData.size()][3]; // assuming two columns: input, expected
	        int i = 0;
	        for (String key : arrayData.keySet()) {
	            Map<String, String> values = arrayData.get(key);
	            data[i][0] = values.get("TestCaseName");
	            data[i][1] = values.get("PythonCode");
	            data[i][2] = values.get("ExpectedResults");
	            i++;
	        }
	        return data;  
	    }
    @DataProvider(name = "arrayPracticeData1")
     public Object[][] getArrayTry() {
     Object[][] data = new Object[arrayData1.size()][4]; // assuming two columns: input, expected
     int i = 0;
     for (String key : arrayData1.keySet()) {
    	

    	        Map<String, String> values = arrayData1.get(key);

    	        
    	 if (values.get("TestCaseName") == null || values.get("ExpectedResults") == null) {
    		    System.out.println("Skipping invalid row: " + key);
    		    continue;
    		}
         data[i][0] = values.get("TestCaseName");
         data[i][1]=values.get("Questions");
         data[i][2] = values.get("PythonCode");
         data[i][3] = values.get("ExpectedResults");
         i++;
     }
     return data; 
 }


		    @DataProvider(name = "codeProvider")
		    public Object[][] codeData() {
		        return new Object[][] {
		            {"Arrays"},
		            {"Linked List"},
		            {"Stack"},
		            {"Queue"},
		            {"Tree"},
		            {"Graph"}
		            };
		    }
	 
		    @DataProvider(name = "codeProvider1")
		    public Object[][] codeData2() {
		        return new Object[][] {
		            {"Array"},
		            {"Linked List"},
		            {"Stack"},
		            {"Queue"},
		            {"Tree"},
		            {"Graph"}
		        };
		    }
			 @DataProvider(name = "ArrayPracticeQuestions")
			    public Object[][] codeData3() {
			        return new Object[][] { 
			        	{"Search the array"},
			            {"Max Consecutive Ones"},
			            {"Find Numbers with Even Number of Digits"},
			            {"Squares of a Sorted Array"}
			            
		    };
			    }
	 

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
