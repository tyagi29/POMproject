package com.magplus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.magplus.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
 


public class MagplusUtil extends TestBase {
	
	public static final long PAGE_LOAD_TIMEOUT = 20;
	//public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=10;
	 static XSSFWorkbook wb;
	static Sheet sheet;
	//static Xls_Reader reader;
	public static String sheetpath="/Volumes/MyData/Ravikant/automation/PageObjectModelFramework/POMModel/src/main/java/com/magplus/testdata/credential.xlsx";

	//method one to get data from excel sheet
	 public static Object[][] getlogindata(String sheetname) throws EncryptedDocumentException, IOException
	{
		FileInputStream file=null;
		try
		{
			file= new FileInputStream(sheetpath);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			wb = new XSSFWorkbook(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		sheet=wb.getSheet(sheetname);
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=1; i<sheet.getLastRowNum(); i++)
		{
			for(int j=0; j<sheet.getRow(0).getLastCellNum();j++)
			{
				data[i][j]= sheet.getRow(i).getCell(j).toString();
			}
		}
		return data;
		
	}
	
	//Another method to get data from excel sheet
	/*public static ArrayList<Object[]> getlogindata2(String sheetname)
	{
		ArrayList<Object[]> mydata= new ArrayList<Object[]>();
		try
		{
			reader= new Xls_Reader(sheetpath);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int rowNum= 1; rowNum<=reader.getRowCount("cred"); rowNum++;)
		{
			String User= reader.getCellData("cred","User",rowNum);
			String password= reader.getCellData("cred","Password",rowNum);
			Object ob[]= {User,Password};
			mydata.add(ob);
		}
		return mydata;
	} */
	// Method to capture screenshot
	public static String takeScreenshotAtEndOfTest(WebDriver driver, String screenshotname) throws IOException
	{
	File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	String destination=System.getProperty("user.dir")+"/FailedScreenshot/"+screenshotname+System.currentTimeMillis()+".png";
	FileUtils.copyFile(srcfile, new File(destination));
	return destination;
	}
	
	// Method to Generate extent report
	
	public static void ReportGeneration(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, "Failed Test is  "+result.getMethod()+ result.getThrowable());
			String screenshotpath=MagplusUtil.takeScreenshotAtEndOfTest(driver, result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotpath));
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, "Pass Testcase is  "+result.getMethod());
			
		}
		else
		{
			test.log(LogStatus.SKIP, "Test case Skipped");
		}
		report.endTest(test);
	}
}
