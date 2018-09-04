package io.branch.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.branch.utilities.Driver;

public class GooglePage {
	private WebDriver driver;
	
	public GooglePage() {
		this.driver=Driver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@class='gLFyf gsfi']")
	public WebElement searchInput;
	
	@FindBy(partialLinkText = "Branch")
	public WebElement branchSite;
	
	
	
	
}
