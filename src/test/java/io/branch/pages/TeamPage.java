package io.branch.pages;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.branch.utilities.Driver;

public class TeamPage {
	private WebDriver driver;

	public TeamPage() {
		this.driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@style='display: inline-block;']")
	public List<WebElement> Team;

	@FindBy(linkText = "ALL")
	public WebElement allDepartment;

	@FindBy(linkText = "DATA")
	public WebElement dataDepartment;

	@FindBy(linkText = "ENGINEERING")
	public WebElement engineeringDepartment;

	@FindBy(linkText = "MARKETING")
	public WebElement marketingDepartment;

	@FindBy(linkText = "OPERATIONS")
	public WebElement operationsDepartment;

	@FindBy(linkText = "PARTNER GROWTH")
	public WebElement partnerGrowthDepartment;

	@FindBy(linkText = "PRODUCT")
	public WebElement productDepartment;

	@FindBy(linkText = "RECRUITING")
	public WebElement recruitingDepartment;

	@FindBy(xpath = "//ul[@class='team-categories']/li")
	public List<WebElement> departments;

	@FindBy(xpath = "//div[@class='info-block']/h2")
	public List<WebElement> allName;

	@FindBy(xpath = "//div[@class='info-block']/h4")
	public List<WebElement> allDepartments;

	@FindBy(xpath = "//div[@style='display: inline-block;']//h2")
	public List<WebElement> empName;

	@FindBy(xpath = "//div[@style='display: inline-block;']//h4")
	public List<WebElement> empDept;
	
	@FindBy(xpath="//div[@class='overlay']/p")
	public List<WebElement> infoText;

	public Map<String, String> empNameDepartment() {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < empName.size(); i++) {
			map.put(empName.get(i).getText(), empDept.get(i).getText());
		}
		return map;
	}
}
