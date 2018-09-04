package io.branch.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BrowserUtils {

	public static void scrollDown(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static List<String> getElementsText(List<WebElement> list) {
		List<String> elemTexts = new ArrayList<>();
		for (WebElement el : list) {
			if (!el.getText().isEmpty()) {
				elemTexts.add(el.getText());
			}
		}
		return elemTexts;
	}

	public static void hover(WebElement element) {
		Actions actions = new Actions(Driver.getDriver());
		actions.moveToElement(element).perform();
	}

	public static String getScreenshot(String name) throws IOException {
		
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";

		File finalDestination = new File(target);

		FileUtils.copyFile(source, finalDestination);
		return target;
	}
	
	public static class ExtentManager {
	    
		private static ExtentReports report;
	    
	    public ExtentReports getInstance() {
	    	if (report == null)
	    		createInstance("test-output/report.html");
	    	
	        return report;
	    }
	    
	    public static ExtentReports createInstance(String fileName) {
	    	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        
	        report = new ExtentReports();
	        report.attachReporter(htmlReporter);
	        
	        return report;
	    }
	}
	

}
