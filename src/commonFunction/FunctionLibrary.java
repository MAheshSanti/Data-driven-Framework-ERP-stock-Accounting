package commonFunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {

	//prepare method for login under function library

	public static boolean adminLogin(String user,String pass) throws Throwable
	{
		driver.get(p.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath(p.getProperty("objreset"))).click();
		driver.findElement(By.xpath(p.getProperty("objuser"))).sendKeys(user);
		driver.findElement(By.xpath(p.getProperty("objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(p.getProperty("objlogin"))).click();
		Thread.sleep(3000);

		String exp="dashboard";
		String act=driver.getCurrentUrl();
		if(act.contains(exp))
		{
			Reporter.log("Valid username and password"+exp+"==="+act,true);

			driver.findElement(By.xpath(p.getProperty("objlogout"))).click();
			return true;
		}
		else
		{
			Reporter.log("Valid invalidusername and password"+pass+"==="+user,true);
			String errmsg=driver.findElement(By.xpath(p.getProperty("objerr"))).getText();
			driver.findElement(By.xpath(p.getProperty("objok"))).click();
			Reporter.log(errmsg,true);
			return false;

		}

	}



}
