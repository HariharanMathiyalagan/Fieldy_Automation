
package org.test;

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
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.pageobjects.SubscriptionPage;
import com.zaigo.utility.BrowserSetup;

public class SubscriptionMonthlyTeamPack extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String calculation;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("SubscriptionMonthlyTeamPack.html");
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

	@Test(priority = 4)
	public void modulepage() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User to land on the Subscription Choose Plan Page");
		SubscriptionPage module = PageFactory.initElements(driver, SubscriptionPage.class);
		module.modulePage();
		String modulePage = module.labelValidation("");
		extentTest.log(Status.INFO, "Actual Result is -" + modulePage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ChoosePlanPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (modulePage.equals(getPropertyValue("ChoosePlanPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ChoosePlanPage.png");
		}
	}

	@Test(priority = 5)
	public void confirmPage() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User to land on the Confirm Page Order");
		SubscriptionPage module = PageFactory.initElements(driver, SubscriptionPage.class);
		module.subscriptionFlow("Team");
		String modulePage = module.labelValidation("TeamPlan");
		extentTest.log(Status.INFO, "Actual Result is -" + modulePage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ConfirmPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (modulePage.equals(getPropertyValue("ConfirmPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ConfirmPage.png");
		}
	}

	@Test(priority = 6)
	public void proAmount() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User to check the Pro rata amount in the Confirm order page");
		SubscriptionPage module = PageFactory.initElements(driver, SubscriptionPage.class);
		String value = module.getValue("ProAmount");
		String calculation = module.calculation("ProAmount");
		extentTest.log(Status.INFO, "Actual Result is -" + value);
		extentTest.log(Status.INFO, "Expected Result is -" + calculation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (value.equals(calculation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ConfirmPage.png");
		}
	}

	@Test(priority = 7)
	public void totalAmount() throws IOException {
		extentTest = extentReports.createTest("Verify the User to check the total amount in the Confirm order page");
		SubscriptionPage module = PageFactory.initElements(driver, SubscriptionPage.class);
		String value = module.getValue("TotalAmount");
		calculation = module.calculation("TotalAmount");
		extentTest.log(Status.INFO, "Actual Result is -" + value);
		extentTest.log(Status.INFO, "Expected Result is -" + calculation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (value.equals(calculation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TotalAmount.png");
		}
	}

	@Test(priority = 8)
	public void billingInformation() throws IOException {
		extentTest = extentReports.createTest("Verify the User to land on the billing information page");
		SubscriptionPage module = PageFactory.initElements(driver, SubscriptionPage.class);
		String value = module.labelValidation("2");
		extentTest.log(Status.INFO, "Actual Result is -" + value);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("BillingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (value.equals(getPropertyValue("BillingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TotalAmount.png");
		}
	}

	@Test(priority = 9)
	public void billingInformationAmount() throws IOException {
		extentTest = extentReports.createTest(
				"Verify the User to check the total amount to be paid is displayed in the Billing information page");
		SubscriptionPage module = PageFactory.initElements(driver, SubscriptionPage.class);
		String value = module.getValue("BillingAmount");
		extentTest.log(Status.INFO, "Actual Result is -" + value);
		extentTest.log(Status.INFO, "Expected Result is -" + SubscriptionPage.valueOf + " / Month");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (value.equals(SubscriptionPage.valueOf + " / Month")) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TotalAmount.png");
		}
	}

	@Test(priority = 9)
	public void paymentDetailsPage() throws IOException {
		extentTest = extentReports.createTest("Verify the User to land on the Payment Details Page");
		SubscriptionPage module = PageFactory.initElements(driver, SubscriptionPage.class);
		String value = module.labelValidation("3");
		extentTest.log(Status.INFO, "Actual Result is -" + value);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PaymentDetail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (value.equals(getPropertyValue("PaymentDetail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TotalAmount.png");
		}
	}

	@Test(priority = 10)
	public void paymentDetailsAmount() throws IOException {
		extentTest = extentReports
				.createTest("Verify the User to check the Total Amount is displayed in the Payment Details page");
		SubscriptionPage module = PageFactory.initElements(driver, SubscriptionPage.class);
		String value = module.getValue("MainAmount");
		extentTest.log(Status.INFO, "Actual Result is -" + value);
		extentTest.log(Status.INFO, "Expected Result is -" + SubscriptionPage.convertion + "0");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (value.equals(SubscriptionPage.convertion + "0")) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MisMatchPasswordValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TotalAmount.png");
		}
	}
}
