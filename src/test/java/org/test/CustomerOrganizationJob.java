package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.CustomerCreateOrganizationPage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerOrganizationJob extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerOrganizationModule.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();
	}
 
	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}

	@Test(priority = 1) // 1-Login
	public void loginPage() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(loginInPage.getPropertyValue("UserName"));
		loginInPage.passwordField(loginInPage.getPropertyValue("Password"));
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
			File file = new File("OrganizationLogin.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrganizationLogin.png");
		}
	}

	@Test(priority = 2)
	private void modulePage() throws InterruptedException, AWTException {
		extentTest = extentReports.createTest("Navigate to Customer Organization Page");
		CustomerCreateOrganizationPage modulePage = new CustomerCreateOrganizationPage(driver);
		modulePage.modulePage();

	}
			
	@Test(priority = 3)
	private void createOrganization() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Customer Organization Successful Message");
		CustomerCreateOrganizationPage create = new CustomerCreateOrganizationPage(driver);
		create.organizationPage();
		create.contactPage();
		create.propertyPage();
		create.equipmentPage();
		String listName = create.create();
		extentTest.log(Status.INFO, "Actual Result - Created List Name -" + listName);
		extentTest.log(Status.INFO,
				"Expected Result - Created List Name -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listName.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("OrgCreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrgCreateValidation.png");
		}

	}
	
	@Test(priority = 4)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Job Page");
		JobPage jobPage = new JobPage(driver);
		jobPage.customerOrganizationJobListPage();
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
	
//	@Test(priority = 5)
	private void mandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation Location field in Job page");
		JobPage mandatoryValidation = new JobPage(driver);
		mandatoryValidation.mandatoryLocationField();
		String errorMandatoryValidation = mandatoryValidation.locationError();
		extentTest.log(Status.INFO, "Actual Result - Mandatory Validation is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO,
				"Expected Result - Mandatory Validation is -" + getPropertyValue("MandatoryErrorMessage"));
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
		extentTest = extentReports.createTest("Verify the Maximum Validation in Location Field");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxValidationLocationField();
		String errorPasswordField = mandatory.locationError();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO,
				"Expected Result Validation Data -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearLocation();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobLocationMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobLocationMaximumValidation.png");
			mandatory.clearLocation();

		}

	}

	@Test(priority = 6)
	private void maximumValidationTittle() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Tittle Field");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxValidationTittle();
		String errorPasswordField = mandatory.tittleError();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO,
				"Expected Result Validation Data -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearTittle();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTittleMaximumValidation.png");
			mandatory.clearTittle();
		}

	}

	@Test(priority = 7)
	private void mandatoryValidationDescription() throws AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation Description field");
		JobPage mandatoryValidation = new JobPage(driver);
		mandatoryValidation.mandatoryDescriptionField();
		String errorMandatoryValidation = mandatoryValidation.descriptionError();
		extentTest.log(Status.INFO, "Actual Result - Mandatory Validation is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO,
				"Expected Result - Mandatory Validation is -" + getPropertyValue("MandatoryErrorMessage"));
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
		extentTest = extentReports.createTest("Verify the Maximum Validation in Description Field");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxValidationDescription();
		String errorPasswordField = mandatory.descriptionError();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearDescription();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTittleMaximumValidation.png");
			mandatory.clearDescription();
		}

	}

	@Test(priority = 11)
	public void maximumTagValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Tags Field");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxCharacterTag();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("MaxTagValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MaxTagValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearTag();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTittleMaximumValidation.png");
			mandatory.clearTag();
		}

	}

	@Test(priority = 12)
	public void duplicateTagsValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Duplicate Validation in Tags Field");
		JobPage mandatory = new JobPage(driver);
		mandatory.duplicateTags();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("DuplicateTags"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DuplicateTags"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.removeTags();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTittleMaximumValidation.png");
			mandatory.removeTags();
		}

	}

	@Test(priority = 13)
	public void maxTagLimitValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Limit Validation in Tags Field");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxTagCountValidation();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("TagLimitValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TagLimitValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.removeMultipleTags();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTittleMaximumValidation.png");
			mandatory.removeMultipleTags();
		}

	}

	@Test(priority = 14)
	private void maximumValidationNotes() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Description Field");
		JobPage mandatory = new JobPage(driver);
		mandatory.maxValidationNotes();
		String errorPasswordField = mandatory.notesError();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearNotes();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTittleMaximumValidation.png");
			mandatory.clearNotes();
		}

	}

	@Test(priority = 15)
	private void unsssignedJob() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Create a Unassigned Job and check the successful message");
		JobPage mandatory = new JobPage(driver);
		mandatory.fromDateTimeScheduleJob();
		String errorPasswordField = mandatory.createdMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
		}
	}

	@Test(priority = 16)
	private void jobUnassignedStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Check the Job Status as an Unassigned");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("UnassignedStatus"));
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

//	@Test(priority = 17)
	private void editJobwithFromDateFromTime() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Edit the Job, and change the status as Schdeuled");
		JobPage mandatory = new JobPage(driver);
		mandatory.editJob1();
		String errorPasswordField = mandatory.updatedMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobUpdatedMessage"));
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
		mandatory.createdJob();
		String errorPasswordField = mandatory.createdMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreatedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreatedJob.png");
		}
	}

	@Test(priority = 19)
	private void jobScheduleStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Check the Job Status as an Scheduled");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("ScheduleStatus"));
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
		String errorPasswordField = mandatory.dispatchTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobDispatchMessage"));
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
		extentTest = extentReports.createTest("Verify the Job has been dispacthed status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("DispatchedStatus"));
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
		String errorPasswordField = mandatory.startTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobStartedMessgae"));
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
		extentTest = extentReports.createTest("Verify the Job has been started status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("StartedStatus"));
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
		String errorPasswordField = mandatory.completedTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobCompletedMessage"));
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
	private void cancelledJob() throws IOException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.cancelledTigerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DeletedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DeletedJob.png");
		}

	}

	@Test(priority = 26)
	private void jobCancelledStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.cancelJobStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCancelledMessage"))) {
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

	@Test(priority = 27)
	private void jobDraftStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job has been draft status");
		JobPage mandatory = new JobPage(driver);
		mandatory.draftJob();
		String errorPasswordField = mandatory.jobStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("DraftStatus"));
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

	@Test(priority = 28)
	private void deletedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Deleted tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.deletedTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("JobDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DeletedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DeletedJob.png");
		}

	}

	@Test(priority = 29)
	private void searchJobNo() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field Job No");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.searchJobNo1();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.resetOption();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchJobNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchJobNo.png");
			mandatory.resetOption();
		}

	}

	@Test(priority = 30)
	private void searchLocation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field Location");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.searchLocation1();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearSearch();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchLocation.png");
			mandatory.clearSearch();
		}

	}

	@Test(priority = 31)
	private void searchFilterByDate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job List filter by date");
		JobPage mandatory = new JobPage(driver);
		mandatory.filterByDate();
		String validateListFromDate = mandatory.validateListFromDate1();
		String validateToDate = mandatory.validateToDate1();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + validateListFromDate + "to" + validateToDate);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + validateListFromDate + "to" + validateToDate);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ((validateListFromDate + "to" + validateToDate).equals(validateListFromDate + "to" + validateToDate)) {
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

	@Test(priority = 32)
	private void searchInvalid() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field is Invalid data");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.invalidSearch1();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + errorPasswordField);
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
	
}
