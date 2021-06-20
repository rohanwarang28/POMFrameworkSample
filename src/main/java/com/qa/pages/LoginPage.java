package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		
	}
	
	//defining locators of loginPage
	
	
	private By username = By.name("username");
	
	private By password = By.name("password");
	
	private By loginButton = By.xpath("//input[@value='Login']");
	
	private By header = By.xpath("//a[text()='Home']");
	
	
	//getters
	
	public WebElement getUsername() {
		
		return getElement(username);
	}

	public WebElement getPassword() {
		return getElement(password);
	}

	public WebElement getLoginButton() {
		return getElement(loginButton);
	}


	
	//defining methods
	
	public HomePage doLogin(String uname,String pwd) throws Exception {
		
		getUsername().sendKeys(uname);
		getPassword().sendKeys(pwd);
		
		getLoginButton().click();
		
		return getInstance(HomePage.class);
	
	
	}
	
	
	public String getLoginPageTitle() {
		
		return getPageTitle();
		
	}
	
	
	public String getLoginHeader() {
		
		
		
		return getPageHeader(header);
		
		
	}
	
	


}
