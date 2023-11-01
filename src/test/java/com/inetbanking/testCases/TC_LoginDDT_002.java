package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd)
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		log.info("User name is provided");
		lp.setPassword(pwd);
		log.info("Entered Password");
		lp.clickSubmit();
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			log.warn("Login Failed");
		}
		else
		{
			Assert.assertTrue(true);
			log.info("Login Passed");
			lp.clickLogout();
			driver.switchTo().alert().accept();//close the logout alert
			driver.switchTo().defaultContent();
		}
	}
	
	//User defined method created to check alert is present or not
	public boolean isAlertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		//C:\Users\User\eclipse-workspace\inetBankingV1\src\test\java\com\inetbanking\testData\LoginData.xlsx
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rownum = XLUtils.getRowCount(path,"Sheet1");
		int colcount = XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][] = new String[rownum][colcount];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);//1 0
			}
		}
		return logindata;
	}
}
