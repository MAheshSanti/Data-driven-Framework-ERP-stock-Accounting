package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.PageObjectModel.CustomerPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import config.POM_APPUTIL;
import utilities.ExcelFileUtil;

public class POM_TestScript extends POM_APPUTIL{

	String inputpath="FileInput\\customerData.xlsx./";
	String outputpath="./Fileoutput/POMResult.xlsx";
	ExtentReports report;
	ExtentTest logger;
	String tcsheet="customer";
	@Test
	public void startTest() throws Throwable
	{
		
		report = new ExtentReports("./Reports/POM_Customer.html");
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount(tcsheet);
		Reporter.log("no of row ="+rc);
		
		for(int i=1;i<=rc;i++)
		{
		logger = report.startTest("Add  customer");
			String cusname=xl.getCellData(tcsheet, i, 0);
			String caddress=xl.getCellData(tcsheet, i, 1);
			String city=xl.getCellData(tcsheet, i, 2);
			String country=xl.getCellData(tcsheet, i, 3);
			String cperson=xl.getCellData(tcsheet, i, 4);
			String cphn=xl.getCellData(tcsheet, i, 5);
			String cemail=xl.getCellData(tcsheet, i, 6);
			String cmob=xl.getCellData(tcsheet, i, 7);
			String cnotes=xl.getCellData(tcsheet, i, 8);
			
			CustomerPage c= PageFactory.initElements(driver, CustomerPage.class);
		boolean res = c.addCustomer(cusname, caddress, city, country, cperson, cphn, cemail, cmob, cnotes);
		
		logger.log(LogStatus.INFO, cusname+"==="+ caddress +"==="+ city+"==="+ country+"==="+ cperson+"==="+ cphn+"==="+ cemail+"==="+ cmob+"==="+ cnotes);
		if(res)
		{
			xl.setCellData(tcsheet, i, 9, "pass", outputpath);
			Reporter.log("Customer add sucessfull");
			logger.log(LogStatus.PASS,"customer add sucess" );
		}
		else
		{
			xl.setCellData(tcsheet, i, 9, "fail", outputpath);
			Reporter.log("Customer add fail");
			logger.log(LogStatus.FAIL,"customer add sucess" );

		}
		report.endTest(logger);
		report.flush();
		}
		
	}
	
	
}
