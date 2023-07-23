//package com.zaigo.pageobjects;
//
//import java.util.HashMap;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//public class FieldyLoginPage extends BasePage {
//	
//	
////	HashMap<String, String> hmap = new HashMap<String, String>();
//	
////	hmap.put("userNameError","login-login-error");
//	
//	
//	public By username_error_by = By.cssSelector("[data-automationid='login-login-error']");
//	public By password_error = By.cssSelector("[data-automationid='login-password-error']");
//	public By login_button = By.cssSelector("[data-automationid='loginBtn']");
//
//	public FieldyLoginPage (WebDriver driver)  {
//		super(driver);
//		String APP_URL = System.getenv("APP_URL");
//		if(APP_URL == null) {
//			APP_URL = "http://qatenant3.zaicrm.com/#/";
//		}
//		driver.get(APP_URL);
//	}
//	
//}
