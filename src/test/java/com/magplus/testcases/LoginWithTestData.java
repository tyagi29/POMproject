package com.magplus.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.magplus.base.TestBase;
import com.magplus.pages.HomePage;
import com.magplus.pages.LoginPage;
import com.magplus.util.MagplusUtil;

public class LoginWithTestData extends TestBase{
	LoginPage lgn;
	
	LoginWithTestData(){
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		lgn= new LoginPage();
	
	}
	
	@DataProvider   //get data from excel sheet through array object 
    public Object[][] GetLoginData() throws EncryptedDocumentException, IOException
	{
		Object[][] data=MagplusUtil.getlogindata("cred");
		return data;
	}
	// We can also use another method to get data from sheet through array list
	
	/*@DataProvider
	public Iterator<Object[]> GetLoginData2()
	{
		ArrayList<Object[]> data=MagplusUtil.getlogindata2("cred");
		return data.iterator();
	}*/
	// We can use anyone data provider by passing the name into test method
	@Test(dataProvider ="GetLoginData")
	public void MultipleLogin(String user, String password)
	{
		lgn.login(user,password );
	}
	@AfterMethod
	public void Closebrower()
	{
		//driver.close();
		//driver.quit();
	}

}
