package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;



public class LoginPageTest extends BaseTest{
	
	
	  LoginPageTest(){
	  
	  super(); 
	  
	  }
	
	  @BeforeTest
	  public void setExtent() {
		  
		  ex = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\FreeCRM_Extent.html",true);
	  }
	  
	 
	  
	 
	@AfterTest
	public void closeExtent() {
		
		ex.flush();
		ex.close();
	}
	
	
	@Test(priority=1,enabled = true)
	public void LoginPageTitleTest() {
		extentTest = ex.startTest("LoginPageTitleTest");
		String loginTitle = page.getInstance(LoginPage.class).getLoginPageTitle();
		Assert.assertEquals(loginTitle, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@DataProvider
	public Object[][] getLoginData() throws Exception {
		Object[][] logindata = TestUtil.getTestData("LoginData");
		
		return logindata;
		
	}
	
	@Test(priority = 2 , dataProvider = "getLoginData",enabled = true)
	public void LoginTest(String uname,String pwd) throws Exception {
		
		extentTest = ex.startTest("LoginTest");
		HomePage homepage = page.getInstance(LoginPage.class).doLogin(uname, pwd);
		String homeTitle = homepage.getHomePageTitle();
		
		Assert.assertEquals(homeTitle, "CRMPRO");
		
	}
	
	@Test()
	public void verifyLoginHeader() {
		extentTest = ex.startTest("verifyLoginHeader");
		Assert.assertEquals(page.getInstance(LoginPage.class).getLoginHeader(), "HOME"); 
	}
	
	
}
