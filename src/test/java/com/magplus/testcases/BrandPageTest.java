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

public class BrandPageTest extends TestBase {
	
	HomePage homepage;
	LoginPage lgn;
	BrandPage brandpage;
	public BrandPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		homepage= new HomePage();
		lgn= new LoginPage();
		brandpage= new BrandPage();
		homepage=lgn.login(prop.getProperty("user"), prop.getProperty("password"));
		homepage.Brandlink();
		
	}
	
	@Test(priority=1)
	public void VerifyBrandTest()
	{
		test=report.startTest("Verify Brand Name", "To Check the correct Brand");
		String url=brandpage.VerifyBrand();
		boolean b=url.contains("testingapp3-live");
		Assert.assertTrue(b, "Brand is not Testing-app3");
	}
	
	@Test(priority=2)
	public void Issuetab()
	{
		test=report.startTest("Brand Home Page", "To Check the home page of the Brand");
		brandpage.Issuetab();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		MagplusUtil.ReportGeneration(result);
		driver.quit();
	}
	

}
