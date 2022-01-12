package com.magplus.testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.magplus.base.TestBase;
import com.magplus.pages.HomePage;
import com.magplus.pages.LoginPage;
import com.magplus.util.MagplusUtil;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase {
	LoginPage logn;
	HomePage homepage;
	
	public LoginPageTest()
	{
	super();
	
	}
	
	@BeforeMethod
	public void setup()
	{
	initialization();
	 logn= new LoginPage();
	 log= Logger.getLogger(LoginPageTest.class);
	}
	
	@Test(priority=1)
	public void LoginPageTitle()
	{
		test=report.startTest("Login Page Title", "To Check the title of the login page");
		String title=logn.ValdateLoginpage();
		Assert.assertEquals(title, "Login | mag+");
		log.info("Title is correct");
	}
	
	@Test(priority=2)
	public void LogoPresent()
	{
		test=report.startTest("Logo Present", "To the logo of the login page");
		boolean flag=logn.ValidateLogo();
		Assert.assertTrue(flag, "Logo not present");
		log.info("Logo is present");
	}
	
	@Test(priority=3)
	public void PublishLogin()
	{
		test=report.startTest("Login", "To the login into publish");
		homepage=logn.login(prop.getProperty("user"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		MagplusUtil.ReportGeneration(result);
		driver.quit();
	}

}
