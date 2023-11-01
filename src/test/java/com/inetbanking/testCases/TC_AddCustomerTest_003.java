package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws IOException
	{
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		log.info("User name is provided");
		
		lp.setPassword(password);
		log.info("Password is provided");
		
		lp.clickSubmit();
		
		AddCustomerPage addcus = new AddCustomerPage(driver);
		
		log.info("Providing Customer Details.....");
		addcus.clickAddNewCustomer();
		
		addcus.custName("Chotu");
		log.info("Entered Customer Name");
		
		addcus.custgender("male");
		log.info("Entered Customer Gender");
		
		addcus.custdob("10", "15", "1985");
		log.info("Entered Customer DOB");
		
		addcus.custaddress("INDIA");
		log.info("Entered Customer Address");
		
		addcus.custcity("BNG");
		log.info("Entered Customer City");
		
		addcus.custstate("Kar");
		log.info("Entered Customer State");
		
		addcus.custpinno("5000074");
		log.info("Entered Customer Pincode");
		
		addcus.custtelephoneno("9876543210");
		log.info("Entered Customer Telephone number");
		
		String email = randomstring()+"@gmail.com";
		addcus.custemailid(email);
		log.info("Entered Customer Email");
		
		addcus.custpassword("abcdefg");
		log.info("Entered Customer Password");
		
		addcus.custsubmit();
		
		log.info("Validation Started");
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res == true)
		{
			Assert.assertTrue(true);
			log.info("Test case Passed.....");
		}
		else
		{
			log.info("Test case Failed.....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
	}
	
}
