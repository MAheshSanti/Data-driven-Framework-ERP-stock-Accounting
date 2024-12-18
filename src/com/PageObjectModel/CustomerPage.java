package com.PageObjectModel;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage {


	WebDriver driver;

	public CustomerPage(WebDriver driver)
	{
		this.driver=driver;
	}	

	@FindBy(xpath="(//a[contains(.,'Customers')])[2]")
	WebElement clickcustomer;
	@FindBy(xpath="(//span[contains(@data-caption,'Add')])[1]")
	WebElement clickAddIconButton;
	@FindBy(xpath="//input[@id='x_Customer_Number']")
	WebElement customernumber;
	@FindBy(xpath="//input[@id='x_Customer_Name']")
	WebElement EnterCusname;
	@FindBy(xpath="//textarea[@id='x_Address']")
	WebElement EnterCusaddress;
	@FindBy(xpath="//input[@id='x_City']")
	WebElement entercuscity;
	@FindBy(xpath="//input[@id='x_Country']")
	WebElement entercuscountry;
	@FindBy(xpath="//input[@id='x_Contact_Person']")
	WebElement entercontactprsn;
	@FindBy(xpath="//input[@id='x_Phone_Number']")
	WebElement enterphnnumbr;
	@FindBy(xpath="//input[@id='x__Email']")
	WebElement enteremail;
	@FindBy(xpath="//input[@id='x_Mobile_Number']")
	WebElement entermobnmbr;
	@FindBy(xpath="//input[@id='x_Notes']")
	WebElement enternotes;

	@FindBy(id="btnAction")
	WebElement clickaddButton;

	@FindBy(xpath="//button[normalize-space()='OK!']")
	WebElement clickconfirmOK;
	@FindBy(xpath="//button[@class='ajs-button btn btn-primary']")
	WebElement clickalertOk;

	@FindBy(xpath="//span[@class='glyphicon glyphicon-search ewIcon']")
	WebElement clickserchpanel;
	@FindBy(xpath="//input[@id='psearch']")
	WebElement Searchtextbox;
	@FindBy(xpath="//button[@id='btnsubmit']")
	WebElement clicksearchbutton;
	@FindBy(xpath="//table[@id='tbl_a_customerslist']/tbody/tr/td[5]/div/span/span")
	WebElement customertabledata;

	//Method for craete addcustomer
	public boolean addCustomer(String cname,String caddrs,String ccity,String ccountry,String cperson, String cphnnumbr,String cemail, String cmobnumber,String cnotes) throws Throwable
	{
		Actions act = new Actions(driver);
		act.moveToElement(clickcustomer).click().perform();
		Thread.sleep(1000);
		act.moveToElement(clickAddIconButton).click().perform();
		Thread.sleep(1000);
		String expdata=customernumber.getAttribute("value");
		Thread.sleep(2000);

		EnterCusname.sendKeys(cname);
		EnterCusaddress.sendKeys(caddrs);
		entercuscity.sendKeys(ccity);
		entercuscountry.sendKeys(ccountry);
		entercontactprsn.sendKeys(cperson);
		enterphnnumbr.sendKeys(cphnnumbr);
		enteremail.sendKeys(cemail);
		entermobnmbr.sendKeys(cmobnumber);
		enternotes.sendKeys(cnotes);
		clickaddButton.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		act.moveToElement(clickconfirmOK).click().perform();
		Thread.sleep(2000);
		act.moveToElement(clickalertOk).click().perform();
		Thread.sleep(1000);

		//if search text box not display then click on sewarchicon button
		if(!Searchtextbox.isDisplayed())
			clickserchpanel.click();
		Thread.sleep(1000);
		Searchtextbox.clear();
		Searchtextbox.sendKeys(expdata);
		clicksearchbutton.click();

		String actdata= customertabledata.getText();

		if(actdata.equals(expdata))
		{
			Reporter.log(actdata+"======"+expdata);
			return true;
		}
		else
		{
			Reporter.log(actdata+"==!=!Not matching =!=!"+expdata);
			return false;
		}		

	}





}
