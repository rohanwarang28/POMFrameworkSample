package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;


public class HomePageTest extends BaseTest{
	
	HomePage homepage;
	
	public HomePageTest() {
		
		super();
	}
	/*
	 * @BeforeTest public void setExtent() {
	 * 
	 * ex = new ExtentReports(System.getProperty("user.dir")+
	 * "\\test-output\\FreeCRM_Extent.html",true); }
	 * 
	 * 
	 * 
	 * 
	 * @AfterTest public void closeExtent() {
	 * 
	 * ex.flush(); ex.close(); }
	 */
	
	@Test
	public void validateHomePageTitleTest() throws Exception {
		
		homepage =  page.getInstance(LoginPage.class).doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actualTitle = homepage.getHomePageTitle();
		Assert.assertEquals(actualTitle, "CRMPRO");
		
	}

}
