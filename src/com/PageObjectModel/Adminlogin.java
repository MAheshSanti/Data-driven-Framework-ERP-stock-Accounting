package com.PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Adminlogin {
	WebDriver driver;
	public Adminlogin(WebDriver driver)
	{
		this.driver=driver;
	}

	//Define Repository for Admin login
	
	@FindBy(xpath="//button[@id='btnreset']")
	WebElement objreset ;
	@FindBy(xpath="//input[@id='username']")
	WebElement objuser ;
	@FindBy(xpath="//input[@id='password']")
	WebElement  objpass;
	@FindBy(xpath="//button[@id='btnsubmit']")
	WebElement  objlogin;
	
	// mehod for login
	public void login(String user,String pass)
	{
		objreset.click();
		objuser.sendKeys(user);
		objpass.sendKeys(pass);
		objlogin.click();
		String exp="dashboard";
		String act = driver.getCurrentUrl();
		
		try {
			Assert.assertTrue(act.equalsIgnoreCase(exp),"Invalid user and pass");

		} catch (AssertionError a) {
			System.out.println(a.getMessage());
		}
	}
	
	
	
}
