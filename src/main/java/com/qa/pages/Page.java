/**
 * 
 */
package com.qa.pages;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author rohan
 *
 */
public abstract class Page {

	public static WebDriver driver;
	public WebDriverWait wait;
	
	
	public Page(WebDriver driver){
		
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 15);
	}
	
	
	
	
	public abstract String getPageHeader(By locator);
	
	public abstract String getPageTitle();
	
	public abstract WebElement getElement(By locator);
	
	public abstract void waitForElement(By locator);
	
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
	
			try {
				
				return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} 
		
		
	}
	
	
}
