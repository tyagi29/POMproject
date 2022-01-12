package com.magplus.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.magplus.base.TestBase;
import com.magplus.pages.BrandPage;
import com.magplus.pages.HomePage;
import com.magplus.pages.LoginPage;
import com.magplus.util.MagplusUtil;

public class HomePageTest extends TestBase {
	HomePage homepage;
	LoginPage lgn;
	BrandPage brandpage;

	HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		homepage= new HomePage();
		lgn= new LoginPage();
		homepage=lgn.login(prop.getProperty("user"), prop.getProperty("password"));
		
	}
	
	@Test(priority= 1)
	public void validateHomePageTest()
	{
		test=report.startTest("Publication Page Logo Test", "To Check Logo on Publication page");
		// boolean flag=homepage.validateHomePage(); or
		
		Assert.assertTrue(homepage.validateHomePage(), "This is not Home page");
	}
	@Test(priority=2)
	public void BrandlinkTest()
	{
		test=report.startTest("Brand Link Test", "To Check Brand Link Click");
		brandpage=homepage.Brandlink();
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		MagplusUtil.ReportGeneration(result);
		driver.quit();
	}
	
}
