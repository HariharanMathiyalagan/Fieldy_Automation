//package com.zaigo.reactui;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.zaigo.pageobjects.FieldyLoginPage;
//import com.zaigo.utility.BrowserSetup;
//
//public class FieldyLogin {
//
//	private WebDriver driver = null;
//
//	@BeforeClass
//	public void setup() {
//		this.driver = BrowserSetup.startBrowser();
//	}
//
//	@AfterClass
//	public void exitBrowser() {
//		this.driver.quit();
//	}
//
//	@Test(priority = 1)
//	public void verifyEmptyLogin() 
//	{
//		FieldyLoginPage fieldyLoginPage = new FieldyLoginPage(this.driver);
//		fieldyLoginPage.clickElement(fieldyLoginPage.login_button);
//		fieldyLoginPage.AssertEqual(fieldyLoginPage.username_error_by, "Enter Your Email");
//		fieldyLoginPage.AssertEqual(fieldyLoginPage.password_error, "Enter Your Password");
//    }
//
//}
