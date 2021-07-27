package com.action.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.framework.utils.ExceptionHandler;
import com.framework.utils.Report;
import com.webdrivers.WebDriverManager;

/*
 * Class to handle web UI actions
 * 
 * @author 10675365
 * 
 */

public class WebAction {	
	
	/*
	 * get - Open webURL
	 * 
	 */
	public synchronized static final void openURL(String openUrl) {
		String status = "FAIL";
		try {
			WebDriverManager.getDriver().get(openUrl);
			status = "PASS";
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		} finally {
			Report.printOperation("get");			
			Report.printStatus(status);
		}
	}
	
	/*
	 * click - To click on WebElement 
	 * 
	 */
	public synchronized static final void click(String locator, String objValue) {
		String status = "FAIL";
		try {
			WebElement element = getElement(WebDriverManager.getDriver(), locator, objValue);			
			element.click();
			status = "PASS";
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		} finally {
			Report.printOperation("click");			
			Report.printStatus(status);
		}
	}
	
	/*
	 * click - To double click on WebElement 
	 * 
	 */
	public synchronized static final void doubleClick(String locator, String objValue) {
		String status = "FAIL";
		try {
			WebElement element = getElement(WebDriverManager.getDriver(), locator, objValue);			
			new Actions(WebDriverManager.getDriver()).doubleClick(element).perform();
			status = "PASS";
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		} finally {
			Report.printOperation("doubleClick");			
			Report.printStatus(status);
		}
	}
	
	/*
	 * click - To contextClick on WebElement
	 *  
	 */
	public synchronized static final void contextClick(String locator, String objValue) {
		String status = "FAIL";
		try {
			WebElement element = getElement(WebDriverManager.getDriver(), locator, objValue);			
			new Actions(WebDriverManager.getDriver()).contextClick(element).perform();
			status = "PASS";
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		} finally {
			Report.printOperation("contextClick");			
			Report.printStatus(status);
		}
	}
	
	/*
	 * click - To clear text on Textbox 
	 * 
	 */
	public synchronized static final void clear(String locator, String objValue) {
		String status = "FAIL";
		try {
			WebElement element = getElement(WebDriverManager.getDriver(), locator, objValue);			
			element.clear();
			status = "PASS";
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		} finally {
			Report.printOperation("Clear");			
			Report.printStatus(status);
		}
	}
	
	/*
	 * Sendkeys - To type text in textbox  
	 * 
	 */
	public synchronized static final void sendKeys(String locator, String objValue, String value) {
		String status = "FAIL";
		try {
			WebElement element = getElement(WebDriverManager.getDriver(), locator, objValue);			
			element.sendKeys(value);
			status = "PASS";
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		} finally {
			Report.printOperation("sendKeys");
			Report.printValue(value);
			Report.printStatus(status);
		}
	}
	
	/*
	 * isElementPresent - Return true if element exist in DOM  
	 * 
	 */
	public synchronized static final void isPresent(String locator, String objValue) {
		String status = "FAIL";
		try {
			WebElement element = getElement(WebDriverManager.getDriver(), locator, objValue);
			if(element!=null)
			status = "PASS";
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		} finally {
			Report.printOperation("isElementPresent");			
			Report.printStatus(status);
		}
	}
	
	/*
	 * validatePageTitle - Get page title from DOM and validate it with input string  
	 */
	public synchronized static final void validateTitle(String pageTitle) {
		String status = "FAIL";
		try {			
			if(WebDriverManager.getDriver().getTitle().equals(pageTitle))
			status = "PASS";
		} catch (Exception e) {
			ExceptionHandler.handleException(e);
		} finally {
			Report.printOperation("validatePageTitle");			
			Report.printStatus(status);
		}
	}
	
	public static WebElement getElement(WebDriver driver, String locator, String attributeValue)
	{
		WebElement wb = null;
		try 
		{
			switch(locator.toLowerCase())
			{
			case "id":
				wb = driver.findElement(By.id(attributeValue));
				break;
			case "name":
				wb = driver.findElement(By.name(attributeValue));
				break;
			case "classname":
				wb = driver.findElement(By.className(attributeValue));
				break;
			case "tagname":
				wb = driver.findElement(By.tagName(attributeValue));
				break;
			case "linktext":
				wb = driver.findElement(By.linkText(attributeValue));
				break;
			case "partiallinktext":
				wb = driver.findElement(By.partialLinkText(attributeValue));
				break;
			case "css":
				wb = driver.findElement(By.cssSelector(attributeValue));
				break;
			case "xpath":
				wb = driver.findElement(By.xpath(attributeValue));
				break;						
		}
			
			return wb;
		}
		catch (Exception e)
		{
			ExceptionHandler.handleException(e);			
			return null;
		}
				
	}

}
