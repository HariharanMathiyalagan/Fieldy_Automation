//package com.zaigo.utility;
//
//import org.openqa.selenium.WebDriver;
//
//public class BrowserThread {
//	
//	private BrowserThread() {
//
//	}
//
//	private static BrowserThread instance = new BrowserThread();
//	
//	public static BrowserThread getInstance() {
//		return instance;
//	}
//	
//	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
//	
//	public WebDriver getDriver() {
//		return driver.get();
//	}
//	
//	public void setDriver(WebDriver wdriver) {
//		driver.set(wdriver);
//	}
//	
//	public void closeBrowser() {
//		driver.get().close();
//		driver.remove();
//	}
//}
