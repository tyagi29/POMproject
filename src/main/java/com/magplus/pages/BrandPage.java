package com.magplus.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magplus.base.TestBase;

public class BrandPage extends TestBase {
	
	//Page factory:
	@FindBy(linkText="Issues")
	WebElement Issuestablink;
	
	// initializing the page objects
		public BrandPage()
		{
			PageFactory.initElements(driver, this);
		}
	
	
	//Actions
	public String VerifyBrand()
	{
		return driver.getCurrentUrl();
		 
	}
	
	public void Issuetab()
	{
		Issuestablink.click();
		 
	}


}
