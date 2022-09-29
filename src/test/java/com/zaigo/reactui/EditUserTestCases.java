package com.zaigo.reactui;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zaigo.pageobjects.EditUserPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditUserTestCases {
	private WebDriver driver = null;
	private LoginPage loginInPage = null;

	@BeforeClass
	public void setup() {
		this.driver = BrowserSetup.startBrowser();
	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
	}

	@AfterMethod
	public void setVariableEmpty() {
		loginInPage = null;
	}

	@Test(priority = 1) // 1-Login
	public void loginPage() throws InterruptedException {
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("fieldy@zaiportal.com", "Zaiserve@123");
		loginInPage.clickLoginButton();
	}

	@Test(priority = 2)
	public void modulePage() throws InterruptedException {
		EditUserPage module = new EditUserPage(driver);
		module.modules();

	}

	@Test(priority = 3)
	public void editFunctionality() {
		EditUserPage edit = new EditUserPage(driver);
		edit.editField();
	}

	@Test(priority = 4)
	public void updateContent() throws InterruptedException {
		EditUserPage update = new EditUserPage(driver);
		update.updateField("Kumar");
	}

	@Test(priority = 5)
	public void deleteList() throws InterruptedException {
		EditUserPage delete = new EditUserPage(driver);
		delete.deleteField();
	}

}
