package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		log.info("Entered Username");
		
		lp.setPassword(password);
		log.info("Entered Password");
		
		lp.clickSubmit();
		
		String expectedUrl = "https://demo.guru99.com/v4/manager/Managerhomepage.php";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual url is not same as expected url");
		
		//WebElement successMessage = driver.findElement(By.xpath("//html/body/table[@class='layout']/tbody//table//marquee[@class='heading3']"));
		//String expectedMessage = "Welcome To Manager's Page of Guru99 Bank";
		//String actualMessage = successMessage.getText();
		//Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not same as expected message");
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			log.info("Login Test Passed");
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			log.info("Login Test Passed");
		}
	}
}
