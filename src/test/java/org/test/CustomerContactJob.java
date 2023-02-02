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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.QuotePage;
import com.zaigo.utility.BrowserSetup;

public class CustomerContactJob extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String dateFrom;
	static String dateTo;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerContactJob.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();

	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}

	@Test(priority = 0) // 1-Login
	public void loginPage() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("Verify the Fieldy Dashboard Page is launched when valid Email & Password is provided");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(loginInPage.getPropertyValue("UserName"));
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
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality.png");
		}
	}

	@Test(priority = 1)
	private void contactModule() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Customer Contact List Page is opened when clicking on Cusotmer->Contact");
		CustomerCreateContactPage module = new CustomerCreateContactPage(driver);
		module.modulePage();

	}

	@Test(priority = 2)
	private void CreateContact() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify a new Customer Contact is created successfully through [Create Contact]");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		initElements.contactPage();
		initElements.propertyPage();
		initElements.equipmentPage();
		String responseMessageCreateContact1 = initElements.responseMessageCreateContact1();
		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseMessageCreateContact1);
		extentTest.log(Status.INFO,
				"Expected Result create response messages is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact1.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.responseMessageCreateContact1();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
			initElements.responseMessageCreateContact1();
			initElements.alternateFunction();
			Assert.fail(responseMessageCreateContact1);
		}
	}

	static String customerContactJobListPage;

	@Test(priority = 3)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify Create Job page is opened from Contacts-> Jobs -> Create Job");
		JobPage jobPage = new JobPage(driver);
		customerContactJobListPage = jobPage.customerJobListPage("Contact");
		String jobLandPage = jobPage.jobLandPage();
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

	@Test(priority = 3)
	private void namePrepopulation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Customer Contact Name:(" + customerContactJobListPage
				+ ") is prepopulated in the Contact Name Field");
		JobPage jobPage = new JobPage(driver);
		String customerName = jobPage.customerName("PlaceHolderName");
		extentTest.log(Status.INFO, "Actual Result is -" + customerContactJobListPage);
		extentTest.log(Status.INFO, "Expected Result is -" + customerName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (customerContactJobListPage.equals(customerName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactNamePrepopulate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactNamePrepopulate.png");
		}
	}

//	@Test(priority = 5)
	private void mandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
		JobPage mandatoryValidation = new JobPage(driver);
		mandatoryValidation.mandatoryLocationField();
		String errorMandatoryValidation = mandatoryValidation.locationError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatoryValidation.picKLocation();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobMandatoryLocationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobMandatoryLocationValidation.png");
			mandatoryValidation.picKLocation();
		}
	}

//	@Test(priority = 4)
	private void maximumValidationLocation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Location Field exceed its max-256 limit");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxValidationLocationField();
		String errorPasswordField = mandatory.locationError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobLocationMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobLocationMaximumValidation.png");
			mandatory.clearValidation("Location");

		}

	}

	@Test(priority = 6)
	private void maximumValidationTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Title Field exceed its max-256 limit");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxValidationTittle();
		String errorPasswordField = mandatory.tittleError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("Tittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTittleMaximumValidation.png");
			mandatory.clearValidation("Tittle");
		}

	}

	@Test(priority = 7)
	private void mandatoryValidationDescription() throws AWTException, IOException {
		extentTest = extentReports.createTest(
				"Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		JobPage mandatoryValidation = new JobPage(driver);
		mandatoryValidation.mandatoryDescriptionField();
		String errorMandatoryValidation = mandatoryValidation.descriptionError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobMandatoryDescriptionValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobMandatoryDescriptionValidation.png");
		}
	}

	@Test(priority = 8)
	private void maximumValidationDescription() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-2048 limit");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxValidationDescription();
		String errorPasswordField = mandatory.descriptionError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobDescriptionMaximumValidation.png");
			mandatory.clearValidation("Description");
		}

	}

	@Test(priority = 11)
	public void maximumTagValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tag field exceed its max-256 limit");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxCharacterTag();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MaxTagValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MaxTagValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("Tag");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTagMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTagMaximumValidation.png");
			mandatory.clearValidation("Tag");
		}

	}

	@Test(priority = 12)
	public void duplicateTagsValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when Duplicate tags are added");
		JobPage mandatory = new JobPage(driver);
		mandatory.duplicateTags();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DuplicateTags"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DuplicateTags"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("TagRemove");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobDuplicateTagValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobDuplicateTagValidation.png");
			mandatory.clearValidation("TagRemove");
		}

	}

	@Test(priority = 13)
	public void maxTagLimitValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when count of tags exceeds 64");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxTagCountValidation();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TagLimitValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TagLimitValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("RemoveMultipleTag");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTagMaximumLimitValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTagMaximumLimitValidation.png");
			mandatory.clearValidation("RemoveMultipleTag");
		}

	}

	@Test(priority = 14)
	private void maximumValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when notes field exceed its max-2048 limit");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxValidationNotes();
		String errorPasswordField = mandatory.notesError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("Notes");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobNotesMaximumValidation.png");
			mandatory.clearValidation("Notes");
		}

	}

	@Test(priority = 15)
	private void unsssignedJob() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Unassigned Job is created successfully from Customer Contact->Create Job");
		JobPage mandatory = new JobPage(driver);
		mandatory.jobStatusCreation("Unassigned");
		String errorPasswordField = mandatory.responseMessage("Created");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.JobNo("JobNo1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			JobListData = mandatory.JobNo("JobNo1");
		}
	}

	@Test(priority = 16)
	private void jobUnassignedStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Unassigned Status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UnassignedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("UnassignedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleStatus.png");
		}
	}

	@Test(priority = 17)
	private void editJobwithFromDateFromTime() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verfiy the unassigned Job is updated to scheduled when assigning the available technician");
		JobPage mandatory = new JobPage(driver);
		mandatory.jobStatusCreation("EditUnassigned");
		String errorPasswordField = mandatory.responseMessage("Updated");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditJob.png");
		}

	}

	@Test(priority = 18)
	private void createJob_FromDateandTime_ToDateandTime()
			throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Create a Job  with From Date & Time - To Date & Time with Scheduled status");
		JobPage mandatory = new JobPage(driver);
		mandatory.customerContactJob();
		mandatory.createdJob();
		String errorPasswordField = mandatory.responseMessage("Created");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.JobNo("JobNo1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreatedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreatedJob.png");
			JobListData = mandatory.JobNo("JobNo1");
		}
	}

	@Test(priority = 19)
	private void jobScheduleStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Schedule Status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ScheduleStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("ScheduleStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ScheduledStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ScheduledStatus.png");
		}
	}

	@Test(priority = 20)
	private void dispatchedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Dispatch tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		mandatory.tiggerFunction("Dispatch");
		String errorPasswordField = mandatory.responseMessage("Dispatched");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobDispatchMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobDispatchMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DispatchedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DispatchedJob.png");
		}

	}

	@Test(priority = 21)
	private void jobDispatchedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Dispatch Status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DispatchedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DispatchStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DispatchStatus.png");
		}

	}

	@Test(priority = 22)
	private void startedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Started tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		mandatory.tiggerFunction("Start");
		String errorPasswordField = mandatory.responseMessage("Started");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobStartedMessgae"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobStartedMessgae"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("JobStarted.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("JobStarted.png");
		}

	}

	@Test(priority = 23)
	private void jobStartedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Active Status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("StartedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("StartedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("StartedStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("StartedStatus.png");
		}

	}

	@Test(priority = 24)
	private void completedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Completed tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		mandatory.tiggerFunction("Complete");
		String errorPasswordField = mandatory.responseMessage("Completeds");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCompletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCompletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CompletedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CompletedJob.png");
		}

	}

	@Test(priority = 25)
	private void jobCompleteStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Completed Status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("StartedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("StartedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("StartedStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("StartedStatus.png");
		}

	}

	@Test(priority = 26)
	private void cancelledJob() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		Thread.sleep(2000);
		mandatory.tiggerFunction("Cancel");
		String errorPasswordField = mandatory.responseMessage("Cancelled");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.JobNo("JobNo2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DeletedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DeletedJob.png");
			JobListData = mandatory.JobNo("JobNo2");
		}

	}

	@Test(priority = 27)
	private void jobCancelledStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Cancelled Status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.cancelJobStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CancelledStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CancelledStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DraftStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DraftStatus.png");
		}

	}

	@Test(priority = 28)
	private void jobDraftStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job has been Draft Status");
		JobPage mandatory = new JobPage(driver);
		mandatory.jobStatusCreation("CustomerContactDraft");
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DraftStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DraftStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DraftStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DraftStatus.png");
		}

	}

	static String JobListData;

	@Test(priority = 29)
	private void deletedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Deleted tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.deletedTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.JobNo("JobNo1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DeletedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DeletedJob.png");
			JobListData = mandatory.JobNo("JobNo1");
		}

	}

	@Test(priority = 30)
	private void searchJobNo() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Enter the Job No:(" + JobListData + ") in the Search field & Job list retrived successfully");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.searchJobNo();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.resetOption();
			JobListData = mandatory.LocationName();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchJobNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchJobNo.png");
			mandatory.resetOption();
			JobListData = mandatory.LocationName();
		}

	}

	@Test(priority = 31)
	private void searchLocation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Enter the Job Location:(" + JobListData + ") in the Search field & Job list retrived successfully");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.searchLocation();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("ContactSearch");
			dateFrom = mandatory.dateFrom();
			dateTo = mandatory.dateTo();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchLocation.png");
			mandatory.clearValidation("ContactSearch");
			dateFrom = mandatory.dateFrom();
			dateTo = mandatory.dateTo();
		}

	}

	@Test(priority = 32)
	private void searchFilterByDate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Job List filter by From date:" + dateFrom + " & To date:" + dateTo);
		JobPage mandatory = new JobPage(driver);
		mandatory.filterByDate();
		String validateListFromDate = mandatory.validateListFromDate();
		String validateToDate = mandatory.validateToDate();
		extentTest.log(Status.INFO, "Actual Result is -" + validateListFromDate + " to " + validateToDate);
		extentTest.log(Status.INFO, "Expected Result is -" + validateListFromDate + " to " + validateToDate);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ((validateListFromDate + " to " + validateToDate).equals(validateListFromDate + " to " + validateToDate)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.resetOption();
			mandatory.jobLabel();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchLocation.png");
			mandatory.resetOption();
			mandatory.jobLabel();
		}

	}

	@Test(priority = 33)
	private void searchInvalid() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.invalidSearch();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchInvalid.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchInvalid.png");
		}

	}

//	@Test(priority = 33)
//	public void unassignedtoUnscheduled() throws IOException, InterruptedException {
//		extentTest = extentReports.createTest("Verify to create a unScheduled job, and check the Status & Job No");
//		JobPage mandatory = new JobPage(driver);
//		mandatory.customerContactJob();
//		mandatory.jobStatusCreation("Unschedule");
//		jobNo = mandatory.jobNo();
//		String createdJobStatus = mandatory.jobStatus();
//		extentTest.log(Status.INFO,
//				"Actual Result is -" + "The " + jobNo + " is currently in the " + createdJobStatus + " status.");
//		extentTest.log(Status.INFO, "Expected Result is -" + "The " + jobNo + " is currently in the "
//				+ getPropertyValue("UnscheduleStatus") + " status.");
//		if (("The " + jobNo + " is currently in the " + createdJobStatus + " status.")
//				.equals("The " + jobNo + " is currently in the " + getPropertyValue("UnscheduleStatus") + " status.")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("Status of Unassigned Job No.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("Status of Unassigned Job No.png");
//		}
//
//	}
//
//	@Test(priority = 34)
//	public void updatedUnassignedJob() throws InterruptedException, IOException {
//		extentTest = extentReports.createTest("Verify the " + jobNo + " change the status as Unassigned");
//		JobPage mandatory = new JobPage(driver);
//		mandatory.unassignedJob();
//		String updatedJobStatus = mandatory.jobStatus();
//		extentTest.log(Status.INFO,
//				"Actual Result is -" + "The " + jobNo + " is updated into " + updatedJobStatus + " status.");
//		extentTest.log(Status.INFO, "Expected Result is -" + "The " + jobNo + " is updated into "
//				+ getPropertyValue("UnassignedStatus") + " status.");
//		if (("The " + jobNo + " is updated into " + updatedJobStatus + " status.")
//				.equals("The " + jobNo + " is updated into " + getPropertyValue("UnassignedStatus") + " status.")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("Updated Status of Job No.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("Updated Status of Job No.png");
//		}
//
//	}
//
//	@Test(priority = 35)
//	private void createScheduledJob() throws WebDriverException, IOException, InterruptedException {
//		extentTest = extentReports.createTest("Verify to create a Schedule job, and check the Status & Job No");
//		JobPage mandatory = new JobPage(driver);
//		mandatory.customerContactJob();
//		mandatory.createdJob();
//		jobNo = mandatory.jobNo();
//		String createdJobStatus = mandatory.jobStatus();
//		extentTest.log(Status.INFO,
//				"Actual Result is -" + "The " + jobNo + " is currently in the " + createdJobStatus + " status.");
//		extentTest.log(Status.INFO, "Expected Result is -" + "The " + jobNo + " is currently in the "
//				+ getPropertyValue("ScheduleStatus") + " status.");
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (("The " + jobNo + " is currently in the " + createdJobStatus + " status.")
//				.equals("The " + jobNo + " is currently in the " + getPropertyValue("ScheduleStatus") + " status.")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("Status of Scheduled Job No.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("Status of Scheduled Job No.png");
//		}
//	}
//
//	@Test(priority = 36)
//	public void updatedReschedule() throws IOException {
//		extentTest = extentReports.createTest("Verify the " + jobNo + " change the status as Rescheduled");
//		JobPage mandatory = new JobPage(driver);
//		mandatory.rescheduleJob();
//		String updatedJobStatus = mandatory.jobStatus();
//		extentTest.log(Status.INFO,
//				"Actual Result is -" + "The " + jobNo + " is updated into " + updatedJobStatus + " status.");
//		extentTest.log(Status.INFO, "Expected Result is -" + "The " + jobNo + " is updated into "
//				+ getPropertyValue("RescheduleStatus") + " status.");
//		if (("The " + jobNo + " is updated into " + updatedJobStatus + " status.")
//				.equals("The " + jobNo + " is updated into " + getPropertyValue("RescheduleStatus") + " status.")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("Updated Reschedule Status of Job No.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("Updated Reschedule Status of Job No.png");
//		}
//
//	}
//
//	@Test(priority = 37)
//	public void rescheduleJobtoDispatch() throws IOException {
//		extentTest = extentReports.createTest("Verify the " + jobNo + " change the status Rescheduled to Dispatch");
//		JobPage mandatory = new JobPage(driver);
//		mandatory.dispatchTiggerFunction();
//		String updatedJobStatus = mandatory.jobStatus();
//		extentTest.log(Status.INFO,
//				"Actual Result is -" + "The " + jobNo + " is updated into " + updatedJobStatus + " status.");
//		extentTest.log(Status.INFO, "Expected Result is -" + "The " + jobNo + " is updated into "
//				+ getPropertyValue("DispatchStatus") + " status.");
//		if (("The " + jobNo + " is updated into " + updatedJobStatus + " status.")
//				.equals("The " + jobNo + " is updated into " + getPropertyValue("DispatchStatus") + " status.")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("Updated Dispatch Status of Job No.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("Updated Dispatch Status of Job No.png");
//		}
//
//	}
//
//	@Test(priority = 38)
//	public void dispatchtoCancelled() throws IOException {
//		extentTest = extentReports.createTest("Verify the " + jobNo + " change the status Dispatch to Cancelled");
//		JobPage mandatory = new JobPage(driver);
//		mandatory.cancelledTigerFunction();
//		String updatedJobStatus = mandatory.jobStatus();
//		extentTest.log(Status.INFO,
//				"Actual Result is -" + "The " + jobNo + " is updated into " + updatedJobStatus + " status.");
//		extentTest.log(Status.INFO, "Expected Result is -" + "The " + jobNo + " is updated into "
//				+ getPropertyValue("DispatchStatus") + " status.");
//		if (("The " + jobNo + " is updated into " + updatedJobStatus + " status.")
//				.equals("The " + jobNo + " is updated into " + getPropertyValue("CancelledStatus") + " status.")) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("Updated Dispatch Status of Job No.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("Updated Dispatch Status of Job No.png");
//		}
//	}
//	
//	@Test(priority = 37)

}
