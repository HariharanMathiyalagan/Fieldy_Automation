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
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerContactJob extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String dateFrom;
	static String dateTo;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("Create Customer Contact Job.html");
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

	@Test(priority = -2) // 1-Login
	public void loginPage() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("Verify the Fieldy Dashboard Page is launched when valid Email & Password is provided");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password", getPropertyValue("Enviromment")));
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
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality.png");
		}
	}

	@Test(priority = -1)
	private void contactModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Navigate to Customer Contact Page");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String editContact = initElements.modulePage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerContactList"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CustomerContactList"))) {
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

	@Test(priority = 0)
	private void CreateContact() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify a new Customer Contact is created successfully through [Create Contact]");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		initElements.contactPage();
		initElements.propertyPage();
		initElements.equipmentPage("SaveComplete");
		String responseMessageCreateContact1 = initElements.responseMessage("CustomerCreate");
		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseMessageCreateContact1);
		extentTest.log(Status.INFO,
				"Expected Result create response messages is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact1.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
			initElements.responseMessage("AlternateFunction");
		}

	}

	static String customerContactJobListPage;

	@Test(priority = 1)
	private void listabelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User is Land on the Customer / Contact / Job page");
		JobPage jobPage = PageFactory.initElements(driver, JobPage.class);
		String jobLandPage = jobPage.labelValidation("Customer");
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContactJobListPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("ContactJobListPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerContactJobListPage = jobPage.customerName("DetailScreenCustomerName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateRequestLabel.png");
			customerContactJobListPage = jobPage.customerName("DetailScreenCustomerName");
		}
	}

	@Test(priority = 2)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify Create Job page is opened from Contacts-> Jobs -> Create Job");
		JobPage jobPage = PageFactory.initElements(driver, JobPage.class);
		String jobLandPage = jobPage.labelValidation("CreateLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatePageJobLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("CreatePageJobLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateJobLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateJobLabel.png");
		}
	}

	@Test(priority = 3, invocationCount = 5)
	private void createJob_FromDateandTime_ToDateandTime()
			throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Create a Job  with From Date & Time - To Date & Time with Scheduled status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.customerName("PlaceHolderName");
		mandatory.jobStatusCreation("CreateJob");
		String errorPasswordField = mandatory.message("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.mouseActionClick(JobPage.CreateButton);
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreatedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreatedJob.png");
			mandatory.message("AlternateForm");
			mandatory.mouseActionClick(JobPage.CreateButton);
		}
	}

}
