package com.zaigo.reactui;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zaigo.pageobjects.ContrctorLocationPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContractorLocationTestCases {
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

	@Test(priority = 1)
	public void verifyContractorTab() throws InterruptedException {
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("fieldy@zaiportal.com", "Zaiserve@123");
		loginInPage.clickLoginButton();

	}

	@Test(priority = 2)
	public void clickModule() {
		ContrctorLocationPage module = new ContrctorLocationPage(driver);
		module.modulePage();

	}

	@Test(priority = 3,invocationCount = 5)
	public void contractorField() {
		ContrctorLocationPage field = new ContrctorLocationPage(driver);
		String r = RandomStringUtils.randomAlphabetic(6);
		String r1 = RandomStringUtils.randomAlphabetic(3);
		String r3 = RandomStringUtils.randomNumeric(10);
		String r4 = RandomStringUtils.randomNumeric(6);
		field.ContractorField(r, r + "@" + r1 + "." + r1, r, r3, r3, "https//www.fieldy.com");
		field.Location(r, r + "@" + r1 + "." + r1, r, r3, r, r, r, r, r4);

	}

}
