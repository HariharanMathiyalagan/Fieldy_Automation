package org.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.utility.BrowserSetup;

public class Login {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("Login.html");
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
	public void verifyEmptyEmail() throws IOException {
		extentTest = extentReports.createTest("Verify the Fieldy Login Page Validation with Blank Space");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("", "");
		loginInPage.clickLoginButton();
		String nameerr = driver.findElement(By.id("login")).getAttribute("validationMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + nameerr);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("EmptyEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (nameerr.equals(loginInPage.getPropertyValue("EmptyEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("1.EmptyEmail.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("1.EmptyEmail.png");
		}

	}

	@Test(priority = 2)
	public void verifyEmptyPassword() throws IOException {
		extentTest = extentReports.createTest("Verify the Fieldy Login Page to Validate the Empty Password");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("fieldy@mailinator.com", "");
		loginInPage.clickLoginButton();
		String nameerr = loginInPage.getErrorMessagePassword();
		extentTest.log(Status.INFO, "Actual Result is -" + nameerr);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("InvalidPasswordField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (nameerr.equals(loginInPage.getPropertyValue("InvalidPasswordField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("2.EmptyPassword.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("2.EmptyPassword.png");
		}

	}

	@Test(priority = 3)
	public void verifyIncorrectMail() throws IOException {
		extentTest = extentReports.createTest("Verify the Fieldy Login Page to Validate the Invalid MailID");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("fieldys@zaiportal.com", "Zaiserve@123");
		loginInPage.clickLoginButton();
		String nameerr = loginInPage.getErrorMessageUserName();
		extentTest.log(Status.INFO, "Actual Result is -" + nameerr);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("InvalidEmailField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (nameerr.equals(loginInPage.getPropertyValue("InvalidEmailField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("3.IncorrectEmail.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("3.IncorrectEmail.png");
		}

	}

	@Test(priority = 4)
	public void verifyIncorrectPassword() throws IOException {
		extentTest = extentReports.createTest("Verify the Fieldy Login Page to Validate the InValid Password");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("fieldy@mailinator.com", "Zaisuite@124");
		loginInPage.clickLoginButton();
		String nameerr = loginInPage.getErrorMessagePassword();
		extentTest.log(Status.INFO, "Actual Result is -" + nameerr);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("InvalidPasswordField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (nameerr.equals(loginInPage.getPropertyValue("InvalidPasswordField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("4.IncorrectPassword.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("4.IncorrectPassword.png");
		}

	}

	@Test(priority = 5)
	public void verifyInvalidEmail() throws IOException {
		extentTest = extentReports.createTest("Verify the Fieldy Login Page to Validate the InValid EmaiID");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("admin", "Zaiserve@123");
		loginInPage.clickLoginButton();
		String nameerr = loginInPage.getErrorMessageUserName();
		extentTest.log(Status.INFO, "Actual Result is -" + nameerr);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("InvalidEmailField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (nameerr.equals(loginInPage.getPropertyValue("InvalidEmailField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("5.InvalidEmail.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("5.InvalidEmail.png");
		}

	}

//	@Test(priority = 6)
	public void verifyInvalidPassword() throws IOException {
		extentTest = extentReports.createTest("Verify the Fieldy Login Page to Min Validation in Password Field");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField("fieldy@mailinator.com");
		loginInPage.passwordField("asd");
		loginInPage.clickLoginButton();
		String nameerr = loginInPage.getErrorMessagePassword();
		extentTest.log(Status.INFO, "Actual Result is -" + nameerr);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("MinCharacterPasswordField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (nameerr.equals(loginInPage.getPropertyValue("MinCharacterPasswordField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("6.InvalidPassword.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("6.InvalidPassword.png");
		}
	}

	@Test(priority = 7)
	public void verifyInvalidCredentials() throws IOException {
		extentTest = extentReports.createTest("Verify the Fieldy Login Page to Validate the InValid EmaiID");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.setUserCredentials("fieldy@zaiportal.co", "sxdasdsdd");
		loginInPage.clickLoginButton();
		String nameerr = loginInPage.getErrorMessageUserName();
		extentTest.log(Status.INFO, "Actual Result is -" + nameerr);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("InvalidEmailField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (nameerr.equals(loginInPage.getPropertyValue("InvalidEmailField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("7.InvalidCredentials.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("7.InvalidCredentials.png");
		}

	}

	@Test(priority = 8)
	public void verify() throws IOException {
		// Single Account User
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(loginInPage.getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(loginInPage.getPropertyValue("Password"));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(loginInPage.getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("8.ValidCredentials.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("8.ValidCredentials.png");
		}

	}
}
