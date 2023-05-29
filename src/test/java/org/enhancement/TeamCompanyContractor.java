package org.enhancement;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class TeamCompanyContractor extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("Create Team Company Contractor.html");
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

	@Test(priority = 1)
	public void verify() throws IOException {
		// Single Account User
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password", getPropertyValue("Enviromment")));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("1.png");
		}
	}

	@Test(priority = 2)
	public void createLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Contractor Company Page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		edit.clickEvent("Navigate");
		String assertionMessage = edit.labelValidation();
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorCreatePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("CompanyContractorCreatePage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 3)
	public void createContract() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the Contractor Company has Successfully Created");
		CreateContractorPage create = PageFactory.initElements(driver, CreateContractorPage.class);
		create.companyName("ValidData");
		create.validData("BasicPage");
		create.validData("LocationPage");
		String asssertCreate = create.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals(getPropertyValue("CompanyContractorCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
			create.responseMessage("AlternateFunction");
		}
	}
}
