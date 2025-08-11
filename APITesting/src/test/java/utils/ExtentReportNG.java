package utils;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNG {
	
	private static ExtentReports report;
	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
	
	public static ExtentReports setupExtentReport() throws IOException {
		
		// SimpleDateFormat format= new SimpleDateFormat("dd-MM-yy HH-mm-ss");
				// Date date = new Date();
				// String actualDate=format.format(date);
		 if (report == null) {

				String reportPath = System.getProperty("user.dir") + "\\Reports\\BNRCExtentReport.html";

				ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);

				report = new ExtentReports();
				report.attachReporter(sparkReport);

				sparkReport.config().setDocumentTitle("Automation Report");
				sparkReport.config().setTheme(Theme.DARK);
				sparkReport.config().setReportName("Extent Report");
				sparkReport.loadXMLConfig(System.getProperty("user.dir")+"\\logoconfig.xml");

				//report.setSystemInfo("Executed on Environment:", System.getProperty(constants.url));
				//report.setSystemInfo("Executed on Browser:", System.getProperty(constants.browser));
				report.setSystemInfo("Executed on OS:", System.getProperty("os.name"));
				report.setSystemInfo("Executed by User:", System.getProperty("user.name"));
		 }

				return report;
		
	   }
	
	public static void createTest(String testName) throws IOException {
        ExtentTest test = setupExtentReport().createTest(testName);
        testThread.set(test);
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void logInfo(String message) {
        if (getTest() != null) {
            getTest().info(message);
        }
    }

    public static void logPass(String message) {
        if (getTest() != null) {
            getTest().pass(message);
        }
    }

    public static void logFail(String message) {
        if (getTest() != null) {
            getTest().fail(message);
        }
    }

    public static void flushReport() {
        if (report != null) {
        	report.flush();
        }
    }
}