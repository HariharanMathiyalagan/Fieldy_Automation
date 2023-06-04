package org.test;

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
import com.zaigo.pageobjects.TeamUserPage;
import com.zaigo.utility.BrowserSetup;

public class AvailabilityTechnicians extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String dateFrom;
	static String dateTo;
	static String JobListData;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("AvailabilityTechnician.html");
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

	@Test(priority = 1)
	private void userCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify a new User is created successfully through [Team User]");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("Navigate");
		landing.labelValidation("ListLabel");
		landing.labelValidation("FormLabel");
		String createMessage = landing.createUser();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UserCreatedMessgae"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("UserCreatedMessgae"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.responseMessage("AlternateFunction");

		}

	}

	@Test(priority = 2)
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

	@Test(priority = 3)
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

	@Test(priority = 4)
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
			dateFrom = jobPage.currentFilterPickerFromDate();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateRequestLabel.png");
			customerContactJobListPage = jobPage.customerName("DetailScreenCustomerName");
			dateFrom = jobPage.currentFilterPickerFromDate();
		}
	}

	@Test(priority = 5)
	private void createdJob90min() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Create a Job with 90min Duration - From Date is " + dateFrom + " From Time is 10.00 A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.jobStatusCreation("FromTime");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.listValidation("Technician");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreatedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreatedJob.png");
			JobListData = mandatory.listValidation("Technician");
		}
	}

	@Test(priority = 6)
	private void technicianAvailability1() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify the Technician Name is " + JobListData
				+ " not available for the Date is " + dateFrom + " and the time is 9.45 A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.scrollDown();
		mandatory.currentPickerFromDate();
		String checkTechnician = mandatory.checkTechnician("9.45A.M");
		extentTest.log(Status.INFO, "Actual Result is -" + checkTechnician);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TechnicianList") + " " + JobListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (checkTechnician.equals(getPropertyValue("TechnicianList") + " " + JobListData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TechnicianName1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TechnicianName1.png");
		}
	}

	@Test(priority = 7)
	private void technicianAvailability2() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Technician Name is " + JobListData
				+ " not available for the Date is " + dateFrom + " and the time is 10.30 A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String checkTechnician = mandatory.checkTechnician("10.30A.M");
		extentTest.log(Status.INFO, "Actual Result is -" + checkTechnician);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TechnicianList") + " " + JobListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (checkTechnician.equals(getPropertyValue("TechnicianList") + " " + JobListData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TechnicianName1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TechnicianName1.png");
		}
	}

	@Test(priority = 8)
	private void technicianAvailability3() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Technician Name is " + JobListData
				+ " not available for the Date is " + dateFrom + " and the time is 11.00 A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String checkTechnician = mandatory.checkTechnician("11.00A.M");
		extentTest.log(Status.INFO, "Actual Result is -" + checkTechnician);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TechnicianList") + " " + JobListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (checkTechnician.equals(getPropertyValue("TechnicianList") + " " + JobListData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TechnicianName1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TechnicianName1.png");
		}
	}

	@Test(priority = 9)
	private void technicianAvailability4() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Technician Name is " + JobListData
				+ " available for the Date is " + dateFrom + " and the time is 8.15 A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String checkTechnician = mandatory.checkTechnician("8.15A.M");
		extentTest.log(Status.INFO, "Actual Result is -" + checkTechnician);
		extentTest.log(Status.INFO, "Expected Result is -" + JobListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (checkTechnician.equals(JobListData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TechnicianName1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TechnicianName1.png");
		}
	}

	@Test(priority = 10)
	private void technicianAvailability5() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Technician Name is " + JobListData
				+ " not available for the Date is " + dateFrom + " and the time is 10.00 A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String checkTechnician = mandatory.checkTechnician("10.00A.M");
		extentTest.log(Status.INFO, "Actual Result is -" + checkTechnician);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TechnicianList") + " " + JobListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (checkTechnician.equals(getPropertyValue("TechnicianList") + " " + JobListData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TechnicianName1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TechnicianName1.png");
		}
	}

	@Test(priority = 11)
	private void technicianAvailability6() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify the Technician Name is " + JobListData
				+ " available for the Date is " + dateFrom + " and the time is 11.45 A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String checkTechnician = mandatory.checkTechnician("11.45A.M");
		extentTest.log(Status.INFO, "Actual Result is -" + checkTechnician);
		extentTest.log(Status.INFO, "Expected Result is -" + JobListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (checkTechnician.equals(JobListData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.jobStatusCreation("BackNav");
			dateTo = mandatory.twoDaysPickerToDate("ReturnValue");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TechnicianName1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TechnicianName1.png");
			mandatory.jobStatusCreation("BackNav");
			dateTo = mandatory.twoDaysPickerToDate("ReturnValue");
		}
	}

	@Test(priority = 12)
	private void createdJobTwoDays() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Create a Job with 2 Days Duration - From Date is " + dateFrom
				+ " From Time is 10.00 A.M & To Date is " + dateTo + " To Time is 11.00A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.jobStatusCreation("TwoDaysWork");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.listValidation("Technician");
			dateFrom = mandatory.currentFilterPickerToDate1();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreatedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreatedJob.png");
			JobListData = mandatory.listValidation("Technician");
			dateFrom = mandatory.currentFilterPickerToDate1();
		}
	}

	@Test(priority = 13)
	private void technianAvailabilityMidDate() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify the Technician Name is " + JobListData
				+ " not available for the Date is " + dateFrom + " and the time is 10.00 A.M");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.scrollDown();
		mandatory.currentPickerFromDate1();
		String checkTechnician = mandatory.checkTechnician("10.00A.M");
		extentTest.log(Status.INFO, "Actual Result is -" + checkTechnician);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TechnicianList") + " " + JobListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (checkTechnician.equals(getPropertyValue("TechnicianList") + " " + JobListData)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.jobStatusCreation("BackNav");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TechnicianName1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TechnicianName1.png");
			mandatory.jobStatusCreation("BackNav");
		}
	}

}
