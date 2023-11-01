package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL = readConfig.getApplicationURL();
	public String username = readConfig.getUsernameL();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	
	public static Logger log;
	
	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeClass
	public void setUp(ITestContext ctx, String browser) {
		
		String testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);
		
		switch(browser)
		{
			case "chrome":
				driver = new ChromeDriver();
				log.info("Chrome browser started");
				break;
				
			case "firefox":
				driver = new FirefoxDriver();
				log.info("Firefox browser started");
				break;
				
			case "IE":
				driver = new InternetExplorerDriver();
				log.info("IE browser started");
				break;
				
			default: log.info("Default Chrome browser started");
				driver = new ChromeDriver();
				break;
		}
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
		log.info("Opened Url");
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		log.info("Screenshot taken");
	}
	

	//User defined method to generate random email/alphabet string
	public String randomstring() {
		
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}
	
	//User defined method to generate random number
	
	public static String randomNum() 
	{ 
		String generatedString2 = RandomStringUtils.randomNumeric(4); 
		return generatedString2;
	}
}
