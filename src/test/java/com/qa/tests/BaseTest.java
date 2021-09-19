package com.qa.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.qa.pages.BasePage;
import com.qa.pages.LoginPage;
import com.qa.pages.Page;

public class BaseTest{
	
	WebDriver driver;
	LoginPage loginPage;
	
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
	  
	  
	  @BeforeSuite
	  
	  public void startup() throws IOException, InterruptedException {
			
		  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\dockerTeardown.bat");
			
			File fs = new File(System.getProperty("user.dir")+"\\outputlog.txt");
			boolean deleteFlag = fs.delete();
		
			/*
			 * while(deleteFlag == false) {
			 * 
			 * Thread.sleep(1000); deleteFlag = fs.delete(); }
			 */
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\dockerSetup.bat");
			File fs1 = new File(System.getProperty("user.dir")+"\\outputlog.txt");
			int counter = 0;
			Thread.sleep(5000);
			while(!fs1.exists()) {
				System.out.println("wait"+counter++);
				Thread.sleep(5000);
			}
			
			
			boolean flag=false;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, 30);
			long stoptime = cal.getTimeInMillis();
			
			while(System.currentTimeMillis()<stoptime) {
				
				BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\outputlog.txt"));
				
				String fline = br.readLine();
			while(fline!=null) {
				
				if(fline.contains("The node is registered to the hub and ready to use")){
					flag = true;
				
					break;
					
					
				}
				else {
					
					fline = br.readLine();
				}
						
			}
					
			  if(flag) 
				  break;
			  
			 
			br.close();
			}
		
			Assert.assertTrue(flag);
			
		}
		
		
	
	@BeforeMethod
	
	
	//@Parameters(value= {"browser"})
	public void setup() throws MalformedURLException {
		
		String browser = "chrome";
		//String browser = System.getProperty("browser");
		String url = "http://localhost:4444/wd/hub";
		
		//cap.setPlatform(Platform.WINDOWS);
		DesiredCapabilities cap  = new DesiredCapabilities();
		if(browser.equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			cap.setBrowserName("chrome");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.merge(cap);
			driver = new RemoteWebDriver(new URL(url),options);
			
		}
		
		
		else if(browser.equalsIgnoreCase("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			
			//driver = new FirefoxDriver();
			
			FirefoxOptions options = new FirefoxOptions();
			cap.setBrowserName("firefox");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.merge(cap);
			driver = new RemoteWebDriver(new URL(url),cap);
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
	  
			/*
			 * if(result.getStatus()==ITestResult.FAILURE) {
			 * 
			 * extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " +result.getName());
			 * extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+
			 * result.getThrowable()); String desPath =
			 * getScreenshots(driver,result.getName()); extentTest.log(LogStatus.FAIL,
			 * extentTest.addScreenCapture(desPath)); }
			 * 
			 * ex.endTest(extentTest);
			 */
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
	  
	  
	  @AfterSuite
	  public void shutDown() throws IOException, InterruptedException {
		  
		  
		  Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\shutdown.bat");
		  Thread.sleep(10000);
	  }
	 

}
