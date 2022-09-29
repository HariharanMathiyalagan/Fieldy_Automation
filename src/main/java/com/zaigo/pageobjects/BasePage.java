//package com.zaigo.pageobjects;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
////import org.openqa.selenium.WebElement;
////import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//
//public class BasePage {
//
//	private WebDriver driver;
//	private WebDriverWait wait;
//	
//	public BasePage(WebDriver driver) {
//		this.driver = driver;
//		this.setWait(new WebDriverWait(this.driver, 10));
//	}
//
//	public WebDriverWait getWait() {
//		return wait;
//	}
//
//	public void setWait(WebDriverWait wait) {
//		this.wait = wait;
//	}
//	
//	public void clickElement(By e) {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(e));
//		driver.findElement(e).click();
//	}
//	
//	public void setValue(By e, String v) {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(e));
//		driver.findElement(e).sendKeys(v);
//	}
//	
//	public String getElementText(By e) {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(e));
//		return driver.findElement(e).getText();
//	}
//	
//	public void AssertEqual(By e, String expectedText) {
//		Assert.assertEquals(this.getElementText(e), expectedText);
//	}
//
//}
