package io.branch.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import io.branch.utilities.BrowserUtils;
import io.branch.utilities.BrowserUtils.ExtentManager;
import io.branch.utilities.ConfigurationReader;
import io.branch.utilities.Driver;

public class TestBase {
	

	protected WebDriver driver;
	protected Actions actions;

	public static ExtentReports report;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest extentLogger;
	
	@BeforeSuite(alwaysRun = true)
	public void setUpTest() {
		String filePath = System.getProperty("user.dir") + "/report.html";
		report = ExtentManager.createInstance(filePath);
		htmlReporter = new ExtentHtmlReporter(filePath);
		

		report.attachReporter(htmlReporter);

		report.setSystemInfo("browser", ConfigurationReader.getProperty("browser"));
		report.setSystemInfo("OS", System.getProperty("os.name"));

		htmlReporter.config().setReportName("Branch Test Reports");
	}
	
	@BeforeClass(alwaysRun=true)
	public void setUp() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(ConfigurationReader.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		String testMethodName = method.getName();
		extentLogger = report.createTest(testMethodName);
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) throws IOException {
		//extentLogger.pass("Page title verified");
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
			
			extentLogger.fail(result.getName());
			
			extentLogger.addScreenCaptureFromPath(screenshotLocation);
			
			extentLogger.fail(result.getThrowable());

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentLogger.skip("Test Case Skipped is " + result.getName());
		}
		
		//Driver.closeDriver();
	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDownTest() {
		report.flush();
	}
	
	
}
