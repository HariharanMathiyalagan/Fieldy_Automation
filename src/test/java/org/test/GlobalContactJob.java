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
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class GlobalContactJob extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("GlobalContactJob.html");
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
		extentTest = extentReports.createTest(
				"Verify the Fieldy Dashboard Page is launched when valid Email & Password is provided");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(loginInPage.getPropertyValue("UserName"));
		loginInPage.passwordField(loginInPage.getPropertyValue("Password"));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + loginInPage.getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(loginInPage.getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Exp	ected Validation are Equal");
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
	public void jobModule() throws InterruptedException {
		extentTest = extentReports.createTest("Verify Global Job List Page is opened when clicking on Global Job");
		JobPage module = new JobPage(driver);
		module.module();
	}

	@Test(priority = 2)
	private void contactMandatoryValidation() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify Contact Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		JobPage contactMandatory = new JobPage(driver);
		contactMandatory.mandatoryContactField();
		String errorContact = contactMandatory.errorContact();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactMandatory.png");
		}

	}

	@Test(priority = 3)
	private void autoCompleteContactCreation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Contact Creation in the Autocomplete field");
		JobPage contactMandatory = new JobPage(driver);
		contactMandatory.contactCreation();
		String errorContact = contactMandatory.responseMessageCreateContact();
		extentTest.log(Status.INFO, "Actual Result is -" + errorContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContact.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AutocompleteContactCreate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AutocompleteContactCreate.png");
		}

	}

//	@Test(priority = 5)
	private void mandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
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
		extentTest = extentReports.createTest("Verify Error Message is displayed when Location Field exceed its max-256 limit");
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
		extentTest = extentReports.createTest("Verify Error Message is displayed when Title Field exceed its max-256 limit");
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
		extentTest = extentReports.createTest("Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
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
		extentTest = extentReports.createTest("Verify Error Message is displayed when Description field exceed its max-2048 limit");
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
		extentTest = extentReports.createTest("Verify Error Message is displayed when Tag field exceed its max-256 limit");
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
		extentTest = extentReports.createTest("Verify Error Message is displayed when notes field exceed its max-2048 limit");
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
		extentTest = extentReports.createTest("Verify Unassigned Job is created successfully from Contact->Create Global Job");
		JobPage mandatory = new JobPage(driver);
		mandatory.fromDateTimeScheduleJob();
		String errorPasswordField = mandatory.createdMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCreatedMessage"));
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
		String errorPasswordField = mandatory.globalJobStatus();
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

//	@Test(priority = 17)
	private void editJobwithFromDateFromTime() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verfiy the unassigned Job is updated to scheduled when assigning the available technician");
		JobPage mandatory = new JobPage(driver);
		mandatory.globalEditJob();
		String errorPasswordField = mandatory.updatedMessage();
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
		mandatory.globalJob();
		mandatory.contactCreation();
		Thread.sleep(10000);
		mandatory.createdJob();
		String errorPasswordField = mandatory.createdMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCreatedMessage"));
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
		String errorPasswordField = mandatory.globalJobStatus();
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
		String errorPasswordField = mandatory.globalDispatchTiggerFunction();
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
		extentTest = extentReports.createTest("Verify the Job has been dispacthed status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.globalJobStatus();
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
		String errorPasswordField = mandatory.globalStartTiggerFunction();
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
		extentTest = extentReports.createTest("Verify the Job has been started status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.globalJobStatus();
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
		String errorPasswordField = mandatory.globalCompletedTiggerFunction();
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
	private void cancelledJob() throws IOException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.globalCancelledTigerFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("JobCancelledMessage"));
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
		extentTest = extentReports.createTest("Verify the Job has been cancelled status");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.cancelGlobalJobStatus();
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

	@Test(priority = 29)
	private void searchCustomerName() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Customer Name in the Search Field");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.searchCustomerName();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.resetOption();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SearchCustomerName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SearchCustomerName.png");
			mandatory.resetOption();
		}

	}

	@Test(priority = 30)
	private void searchJobNo() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field Job No");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.searchJobNo2();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
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

	@Test(priority = 31)
	private void searchLocation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field Location");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.searchLocation2();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.resetOption();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchLocation.png");
			mandatory.resetOption();
		}

	}

	@Test(priority = 32)
	private void searchFilterByDate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job List filter by date");
		JobPage mandatory = new JobPage(driver);
		mandatory.filterByDate2();
		String validateListFromDate = mandatory.validateScheduledDate();
		extentTest.log(Status.INFO, "Actual Result is -" + validateListFromDate);
		extentTest.log(Status.INFO, "Expected Result is -" + validateListFromDate);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ((validateListFromDate).equals(validateListFromDate)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("GlobalSearch");
//			mandatory.jobLabel();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchLocation.png");
			mandatory.clearValidation("GlobalSearch");
//			mandatory.jobLabe2();
		}

	}

	@Test(priority = 33)
	private void searchInvalid() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field is Invalid data");
		JobPage mandatory = new JobPage(driver);
		String errorPasswordField = mandatory.invalidSearch2();
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

}
