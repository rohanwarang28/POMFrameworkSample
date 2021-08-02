package com.qa.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;

import com.qa.pages.BasePage;
import com.qa.pages.LoginPage;
import com.qa.pages.Page;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest{
	
	WebDriver driver;
	LoginPage loginPage;
	ExtentReports ex;
	ExtentTest extentTest;
	Properties prop;
	public Page page;
	
	  public BaseTest(){
	  
	  try {
		  
	  prop = new Properties(); 
	  
	  FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\config\\config.properties");
	  prop.load(fis);
	  
	  }
	  catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
	  }
	  
	  
	  
	  }
	 
	
	@BeforeMethod
	
	
	//@Parameters(value= {"browser"})
	public void setup() {
		
		//String browser = "chrome";
		String browser = System.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
		
		
		else if(browser.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}
		
		else
			System.out.println("Browser not defined");
		
		
		  driver.manage().window().maximize(); 
		  driver.manage().deleteAllCookies();
		  
		 
		driver.get("https://classic.crmpro.com/index.html");
		page = new BasePage(driver);
		
	}
	
	
	
	  @AfterMethod 
	  public void tearDown(ITestResult result) throws IOException {
	  
		  if(result.getStatus()==ITestResult.FAILURE)
		  {
			  
			  extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " +result.getName());
			  extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+ result.getThrowable());
			  String desPath = getScreenshots(driver,result.getName());
			  extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(desPath));
		  }
		  
		  ex.endTest(extentTest);
	  driver.quit();
	  
	  }
	  
	  
	  public String getScreenshots(WebDriver driver , String screenshotname) throws IOException {
			 
			String dateSTring =  new SimpleDateFormat("yyMMddhhmmss").format(new Date());
			
			String destinationPath = System.getProperty("user.dir")+"\\ErrorScreenshots\\"+screenshotname+dateSTring+".png";
			 TakesScreenshot ts = (TakesScreenshot) driver;
			 File fs  = ts.getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(fs, new File(destinationPath));
			  return destinationPath;
		  }
	 

}
