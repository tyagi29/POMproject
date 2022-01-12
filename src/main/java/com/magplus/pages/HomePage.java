package com.magplus.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.magplus.base.TestBase;

public class HomePage extends TestBase {
	
	// pajeObjects
	@FindBy(id="logo")
	WebElement homepagelogo;
	
	@FindBy(xpath="//a[contains(@href,'#test')]")
	WebElement testBrand;
	
	@FindBy(xpath="//a[contains(@href,'/brands/testingapp3-live\')]")
	WebElement brandlink;
	
	// initializing the page objects
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public boolean validateHomePage()
	{
		return homepagelogo.isDisplayed();
		
	}
	
	public BrandPage Brandlink()
	{
		testBrand.click();
		brandlink.click();
		return new BrandPage();
		
	}
	

}
