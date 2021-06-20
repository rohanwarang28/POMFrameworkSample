package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page{

	public BasePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String getPageHeader(By locator) {
		// TODO Auto-generated method stub
		 return getElement(locator).getText();
	}

	@Override
	public String getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	@Override
	public WebElement getElement(By locator) {
		// TODO Auto-generated method stub
		try{
			
			
			waitForElement(locator);
	
			return driver.findElement(locator);
		
		}
		
		catch(Exception e){
			
			System.out.println("Error occured while creating the WebElement" + locator.toString());
			return null;
		}
	}

	@Override
	public void waitForElement(By locator) {
		// TODO Auto-generated method stub
		try{
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		}
		
		catch(Exception e) {
			
			System.out.println("Exception occured while waiting for element "+ locator.toString());
			e.printStackTrace();
		}
	}
	

	

}
