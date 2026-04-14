package utilities;



import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	Map<String, Map<String, String>> arrayData = ExcelReader.getArraydataData();
	Map<String, Map<String, String>> arrayData1 = ExcelReader.getArrayTryData();

	//-----------------------ARRAY TRY HERE
   

	
            @DataProvider(name = "arrayPracticeData") 
	        public Object[][] getArrayData() {
	        Object[][] data = new Object[arrayData.size()][3]; 
	        int i=0;
	        for (String key : arrayData.keySet()) {
	            Map<String, String> values = arrayData.get(key);
	            //int i=0;
	            data[i][0] = values.get("TestCaseName");
	            data[i][1] = values.get("PythonCode");
	            data[i][2] = values.get("ExpectedResults");
	            i++;
	        }
	        return data;  
	    }
    //--------------------------ARRAY PRACTICE QUESTIONS
            
            @DataProvider(name = "arrayData1")
            public Object[][] getArrayTry() {

                List<Object[]> dataList = new ArrayList<>();

                for (String key : arrayData1.keySet()) {

                    Map<String, String> values = arrayData1.get(key);

                    System.out.println("Row: " + key + " -> " + values);

                    if (values.get("Results") == null || values.get("Results").isEmpty() ||
                        values.get("PythonCode") == null || values.get("PythonCode").isEmpty()) {
                    	
                        System.out.println("Skipping invalid row: " + key);
                        continue;
                    }

                    dataList.add(new Object[] {
                        key,  // testcasename
                        values.get("Questions"),
                        values.get("PythonCode"),
                        values.get("Results")
                    });
                }

                return dataList.toArray(new Object[0][]);
            }

                
        
 
//_____|-----------|__________|----------HOMEMODULE

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
         	    
//=============================ARRAY PRACTICECQUESTIONS		    
			@DataProvider(name = "ArrayPracticeQuestions")
			    public Object[][] codeData3() {
			        return new Object[][] { 
			        	{"Search the array"},
			            {"Max Consecutive Ones"},
			            {"Find Numbers with Even Number of Digits"},
			            {"Squares of a Sorted Array"}
			            
		    };
			    }
	 
//__________________________LOGIN DATA_____________________________
	
	// Login

	private Object[][] getLoginDataForTestCase(String testCaseName) {
		Object[][] data = new Object[1][1];
		data[0][0] = ExcelReader.getLoginData().get(testCaseName);
		return data;
	}

	@DataProvider(name = "validLogin")
	public Object[][] validLogin() {
		return getLoginDataForTestCase("Login_Valid");
	}

	@DataProvider(name = "invalidLogin")
	public Object[][] invalidLogin() {
		return getLoginDataForTestCase("Login_Invalid");
	}

	@DataProvider(name = "invalidUsername")
	public Object[][] invalidUsername() {
		return getLoginDataForTestCase("Login_Invalid_Username");
	}

	@DataProvider(name = "invalidPassword")
	public Object[][] invalidPassword() {
		return getLoginDataForTestCase("Login_Invalid_Password");
	}

	

	// Stack

	@DataProvider(name = "stackTopics")
	public Object[][] stackTopics() {
		Object[][] data = new Object[3][1];
		data[0][0] = "Operations in Stack"; 
		data[1][0] = "Implementation";
		data[2][0] = "Applications";

		return data;
	}
	
	//  Queue
	
	@DataProvider(name = "queueTopics")
	public Object[][] queueTopics() {
		Object[][] data = new Object[4][1];
		data[0][0] = "Implementation of Queue in Python";
		data[1][0] = "Implementation using collections.deque";
		data[2][0] = "Implementation using array";
		data[3][0] = "Queue Operations";

		return data;
	}

	// try editor
	private Object[][] getEditorDataForTestCase(String testCaseName) {
		Object[][] data = new Object[1][1];
		data[0][0] = ExcelReader.getEditorData().get(testCaseName);
		return data;
	}

	@DataProvider(name = "validPythonCode")
	public Object[][] validPythonCode() {
		return getEditorDataForTestCase("PythonCode_Valid");
	}

	@DataProvider(name = "invalidPythonCode")
	public Object[][] invalidPythonCode() {
		return getEditorDataForTestCase("PythonCode_Invalid");
	}
}
