package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunction.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {

	String inputpath="./FileInput\\Login.xlsx";

	String outputpath="./Fileoutput/datadrivenresult.xlsx";

	ExtentReports report;
	ExtentTest logger;


	@Test
	public void starTest() throws Throwable
	{

		report = new ExtentReports("./Reports//Login.html");

		//crate object for excelfile
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);

		int rc=xl.rowCount("Logindata");

		Reporter.log("no of rows are="+rc,true);
		for(int i=1;i<=rc;i++)
		{
			
			logger= report.startTest("validate login");
			String username=xl.getCellData("LoginData", i, 0);
			String password=xl.getCellData("LoginData", i, 1);
			
			logger.log(LogStatus.INFO, username,password);

			// calling login method from Function library
			
			boolean res = FunctionLibrary.adminLogin(username, password);

			if(res)
			{
				xl.setCellData("LoginData", i, 2, "valid username and password", outputpath);			
				xl.setCellData("LoginData", i, 3, " pass", outputpath);			
				
				logger.log(LogStatus.PASS, "Login is Suceess");
				
			}
			else
			{
				xl.setCellData("LoginData", i, 2, "Invalid username and password", outputpath);			
				xl.setCellData("LoginData", i, 3, " fail", outputpath);			
				logger.log(LogStatus.FAIL, "Login is Suceess");

			}

report.endTest(logger);
report.flush();
		}

	}
}
