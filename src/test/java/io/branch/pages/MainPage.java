package io.branch.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.branch.utilities.Driver;

public class MainPage {
	private WebDriver driver;
	
	public MainPage() {
		this.driver =  Driver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Team")
	public WebElement team;
}
