package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {

	public static WebDriver driver;
	public static Properties p;
	
	@BeforeTest
	public static void setUp() throws Throwable, IOException
	{
		p = new Properties();
		p.load(new FileInputStream("./PropertyFile\\Enviounment.properties"));
	if(p.getProperty("browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
	}
	else if(p.getProperty("browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
	}
	else
	{
		Reporter.log("Browser value does n't match",true);
	}
	
	}
	
	@AfterTest
	public static void tearDown()
	{
		driver.quit();
	}
}
