package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.PageObjectModel.AdminLogout;
import com.PageObjectModel.Adminlogin;

public class POM_APPUTIL {

	public static WebDriver driver;
	public static Properties p;
	@BeforeTest
	public void setup() throws Throwable, IOException
	{
		p= new Properties();
		p.load(new FileInputStream("./PropertyFile\\Enviounment.properties"));
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.get(p.getProperty("url"));
	
	Adminlogin login = PageFactory.initElements(driver, Adminlogin.class);
	login.login("admin", "master");
	}
	
	@AfterTest
	public void tearDown()
	{
		AdminLogout logout=PageFactory.initElements(driver, AdminLogout.class);
	logout.adminLogout();
	driver.quit();
	}
}
