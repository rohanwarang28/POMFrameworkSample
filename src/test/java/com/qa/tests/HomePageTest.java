package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest extends BaseTest{
	
	HomePage homepage;
	
	public HomePageTest() {
		
		super();
	}
	
	@Test
	public void validateHomePageTitleTest() throws Exception {
		
		homepage =  page.getInstance(LoginPage.class).doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actualTitle = homepage.getHomePageTitle();
		Assert.assertEquals(actualTitle, "CRMPRO");
	}

}
