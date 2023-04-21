package org.enhancement;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
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
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.utility.BrowserSetup;

public class Onboarding extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("Create OnBoarding.html");
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
	public void createTenant() throws IOException {
		extentTest = extentReports.createTest("Creating a New Tenant, the page is redirect into the Dashboard page");
		OnBoardingPage mismatchPassword = new OnBoardingPage(driver);
		mismatchPassword.fillData();
		String errorConfirmMessage = mismatchPassword.dashBoardPage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorConfirmMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorConfirmMessage.equals(getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MisMatchConfirmPasswordValidation.png");
		}

	}

	@Test(priority = 2)
	public void subDomineURL() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Check the Sub Domain URL");
		OnBoardingPage mismatchPassword = new OnBoardingPage(driver);
		String actualURL = mismatchPassword.urlGet();
		String expectedURL = mismatchPassword.expectedURL();
		extentTest.log(Status.INFO, "Actual Result is -" + actualURL);
		extentTest.log(Status.INFO, "Expected Result is -" + expectedURL);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualURL.equals(expectedURL)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MisMatchConfirmPasswordValidation.png");
		}

	}

	@Test(priority = 3)
	public void ownerName() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Check the Sub Domain tenant owner profile name");
		OnBoardingPage mismatchPassword = new OnBoardingPage(driver);
		String actualOwner = mismatchPassword.getOwnerName();
		String expectedOwner = mismatchPassword.expectedOwnerName();
		extentTest.log(Status.INFO, "Actual Result is -" + actualOwner);
		extentTest.log(Status.INFO, "Expected Result is -" + expectedOwner);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualOwner.equals(expectedOwner)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MisMatchConfirmPasswordValidation.png");
		}

	}
}
