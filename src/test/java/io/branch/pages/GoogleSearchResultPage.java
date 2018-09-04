package io.branch.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.branch.utilities.Driver;

public class GoogleSearchResultPage {
	
	private WebDriver driver;

	public GoogleSearchResultPage() {
		this.driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	
}
