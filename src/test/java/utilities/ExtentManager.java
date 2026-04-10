package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

    public static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");

            extent = new ExtentReports();
            extent.attachReporter(spark);

       }

        return extent;
    }
    public static ExtentTest createTest(String testName) {
        test = getExtentReports().createTest(testName);
        return test;
    }
}