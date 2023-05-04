package org.enhancement;

import java.awt.AWTException;
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
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.TaxPage;
import com.zaigo.utility.BrowserSetup;

public class TaxModule extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String ListField;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("Create Tax Module.html");
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
		extentTest = extentReports.createTest("Navigate to Tax Settings page");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		String editContact = initElements.modulePage("ListPage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ListTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("ListTax"))) {
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
	private void createFormLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Tax Page");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		String editContact = initElements.modulePage("CreatePage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreateTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CreateTax"))) {
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
	private void createTax() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify created successful message is displayed, when the Tax Created");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.validRecord();
		mandatory.clickEvent("ClickButton");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatedTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CreatedTax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			mandatory.message("AlternateMessage");
		}
	}

}
