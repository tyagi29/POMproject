package com.magplus.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magplus.base.TestBase;

public class LoginPage extends TestBase {
	
	//Page factory:
	
	@FindBy(id="auth_key")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(name="commit")
	WebElement lgbtn;
	
	@FindBy(xpath="//img[contains(@class,'logo')]")
	// or @FindBy(xpath="//img[@class='logo']")
	WebElement maglogo;
	
	// initializing the page objects
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public String ValdateLoginpage()
	{
		return driver.getTitle();
	}
	
	public boolean ValidateLogo()
	{
		return maglogo.isDisplayed();
	}
	
	public HomePage login(String usr, String pass)
	{
		username.sendKeys(usr);
		password.sendKeys(pass);
		lgbtn.click();
		return new HomePage();
		
		
	}


}
