package org.test;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.BusinessDaysPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.PrefixSettingPage;
import com.zaigo.utility.BrowserSetup;

public class PrefixSettings extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String ListField;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("PrefixSetting.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();

	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}
	
	@BeforeMethod
	public void deleteBeforeCatch() {
		driver.manage().deleteAllCookies();
	}
	
	@AfterMethod
	public void deleteAfterCatch() {
		driver.manage().deleteAllCookies();
	}

	@Test(priority = 0) // 1-Login
	public void loginPage() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("Verify the Fieldy Dashboard Page is launched when valid Email & Password is provided");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password"));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + text);
		extentTest.log(Status.INFO,
				"Expected Result Validation Data -" + loginInPage.getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(loginInPage.getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality.png");
		}
	}

	@Test(priority = 1)
	private void settingModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Navigate to Business Settings page");
		PrefixSettingPage initElements = PageFactory.initElements(driver, PrefixSettingPage.class);
		String editContact = initElements.modulePage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PrefixSettingsLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("PrefixSettingsLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
		}
	}
	
	@Test(priority = 2)
	private void jobPrefixNameMandatory() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Job Prefix Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		PrefixSettingPage initElements = PageFactory.initElements(driver, PrefixSettingPage.class);
		initElements.mandatoryValidation("JobPrefix");
		String editContact = initElements.message("Mandatory");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceModule"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("LeadSourceModule"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
		}
	}
	
	@Test(priority = 3)
	private void jobPrefixNameMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Job Prefix Name Field exceed its max-45 limit");
		PrefixSettingPage initElements = PageFactory.initElements(driver, PrefixSettingPage.class);
		initElements.maxValidation("JobPrefix");
		String editContact = initElements.message("MaxValidation");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceModule"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("LeadSourceModule"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.validData("JobPrefix");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.validData("JobPrefix");
		}
	}
	
	@Test(priority = 4)
	private void jobPrefixNoMandatory() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Job Prefix No field is set as Mandatory & Error Message is displayed when it is BLANK");
		PrefixSettingPage initElements = PageFactory.initElements(driver, PrefixSettingPage.class);
		initElements.mandatoryValidation("JobPrefixNumber");
		String editContact = initElements.message("Mandatory");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceModule"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("LeadSourceModule"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
		}
	}
	
	@Test(priority = 5)
	private void jobPrefixNoMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Job Prefix No Field exceed its max-45 limit");
		PrefixSettingPage initElements = PageFactory.initElements(driver, PrefixSettingPage.class);
		initElements.maxValidation("JobPrefixNumber");
		String editContact = initElements.message("MaxValidation");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceModule"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("LeadSourceModule"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.validData("JobPrefixNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.validData("JobPrefixNumber");
		}
	}
	
	
	
	
}
