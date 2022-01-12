package com.magplus.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.magplus.util.MagplusUtil;
import com.magplus.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	
	 public static Properties prop;
	 public static WebDriver driver;
	 public static EventFiringWebDriver e_driver;
	 public static WebEventListener eventListener;
	 public static Logger log;
	 public static ExtentReports report;
	 public static ExtentTest test;
	 
	 @BeforeTest
	 public void ExtentReportSetup()
	 {
		 report= new ExtentReports(System.getProperty("user.dir")+"/ExtentReport/Report.html",true );
		 report.addSystemInfo("Host Name", "Ravikant mac");
		 report.addSystemInfo("User name", "Ravikant");
		 report.addSystemInfo("Environment", "QA");
	 }
	 
	 @AfterTest
	 public void EndReport()
	 {
		 
		 report.flush();
	 }
	 
	 
	 public TestBase()
	 {
		 try
		 {
			 prop= new Properties();
			 prop.load(new FileInputStream("src//main//java//com//magplus//config//config.properties"));
		 }
		 
		 catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	 

}
	 
	 public static void initialization()
	 {
		 String browsername=prop.getProperty("browser");
		 String url=prop.getProperty("url");
		 if(browsername.equals("chrome"))
		 {
			 System.setProperty("webdriver.chrome.driver", "chromedriver//chromedriver");
			 driver=new ChromeDriver();
		 }else if(browsername.equals("FF"))
		 {
			 System.setProperty("webdriver.gecko.driver", "firefoxdriver//geckodriver");
			 driver=new FirefoxDriver();
		 }
		 e_driver= new EventFiringWebDriver(driver);
		 eventListener= new WebEventListener();
		 e_driver.register(eventListener);
		 driver=e_driver;
		 
		 
		 driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().pageLoadTimeout(MagplusUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(MagplusUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		 driver.get(url);
	 }
}
