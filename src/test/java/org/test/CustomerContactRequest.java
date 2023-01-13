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
import com.zaigo.pageobjects.CustomerCreateContactPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.RequestPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerContactRequest extends BaseClass{
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerContactRequest.html");
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
			File file = new File("LoginFunctionality.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("LoginFunctionality.png");
		}
	}

	@Test(priority = 1)
	private void contactModule() throws InterruptedException {
		extentTest = extentReports.createTest("Navigate to Customer Contact Page");
		CustomerCreateContactPage module = new CustomerCreateContactPage(driver);
		module.modulePage();

	}

	@Test(priority = 2)
	private void CreateContact() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Customer Contact Successful Message");
		CustomerCreateContactPage create = new CustomerCreateContactPage(driver);
		create.contactPage();
		create.propertyPage();
		create.equipmentPage();
		String responseMessageCreateContact = create.responseMessageCreateContact();
		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseMessageCreateContact);
		extentTest.log(Status.INFO,
				"Expected Result create response messages is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.responseMessageCreateContact();

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
			create.responseMessageCreateContact();
		}

	}
	
	@Test(priority = 3)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Request Page");
		RequestPage jobPage = new RequestPage(driver);
		jobPage.customerContactRequestListPage();
		String jobLandPage = jobPage.requestLandPage();
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatePageRequestLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("CreatePageRequestLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateRequestLabel.png");
		}
	}

	
//	@Test(priority = 5)
	private void mandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation Location field in Request page");
		RequestPage mandatoryValidation = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestMandatoryLocationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestMandatoryLocationValidation.png");
			mandatoryValidation.picKLocation();
		}
	}

//	@Test(priority = 4)
	private void maximumValidationLocation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Location Field");
		RequestPage mandatory = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestLocationMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestLocationMaximumValidation.png");
			mandatory.clearLocation();

		}

	}
	
	@Test(priority = 6)
	private void maximumValidationTittle() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Tittle Field");
		RequestPage mandatory = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestTittleMaximumValidation.png");
			mandatory.clearTittle();
		}

	}

	@Test(priority = 7)
	private void mandatoryValidationDescription() throws AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation Description field");
		RequestPage mandatoryValidation = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestMandatoryDescriptionValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestMandatoryDescriptionValidation.png");
		}
	}

	@Test(priority = 8)
	private void maximumValidationDescription() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Description Field");
		RequestPage mandatory = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestDescriptionMaximumValidation.png");
			mandatory.clearDescription();
		}

	}

	@Test(priority = 11)
	public void maximumTagValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Tags Field");
		RequestPage mandatory = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestMaximumTagValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestMaximumTagValidation.png");
			mandatory.clearTag();
		}

	}

	@Test(priority = 12)
	public void duplicateTagsValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Duplicate Validation in Tags Field");
		RequestPage mandatory = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestDuplicateTagValidation.png");
			mandatory.removeTags();
		}

	}

	@Test(priority = 13)
	public void maxTagLimitValidation() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Limit Validation in Tags Field");
		RequestPage mandatory = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestTagMaximumLimitValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestTagMaximumLimitValidation.png");
			mandatory.removeMultipleTags();
		}

	}

	@Test(priority = 14)
	private void maximumValidationNotes() throws IOException {
		extentTest = extentReports.createTest("Verify the Maximum Validation in Description Field");
		RequestPage mandatory = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestNotesMaximumValidation.png");
			mandatory.clearNotes();
		}

	}
	
	@Test(priority = 15)
	private void unsssignedRequest() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Create a Unassigned Request and check the successful message");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.fromDateTimeScheduleRequest();
		String errorPasswordField = mandatory.createdMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleRequest.png");
		}
	}
	
	@Test(priority = 16)
	private void requestUnassignedStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Check the Request Status as an Unassigned");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("UnassignedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("UnassignedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleRequestStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleRequestStatus.png");
		}
	}
	
//	@Test(priority = 17)
	private void editRequestwithFromDateFromTime() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Edit the Request, and change the status as Schdeuled");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.editRequest();
		String errorPasswordField = mandatory.updatedMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("RequestUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditRequest.png");
		}

	}
	
	@Test(priority = 18)
	private void createRequest_FromDateandTime_ToDateandTime()
			throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Create a Request  with From Date & Time - To Date & Time with Scheduled status");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.customerContactRequest();
		mandatory.createdRequest();
		String errorPasswordField = mandatory.createdMessage();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreatedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreatedRequest.png");
		}
	}
	
	@Test(priority = 19)
	private void requestScheduleStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Check the Request Status as an Scheduled");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("ScheduleStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("ScheduleStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ScheduledRequestStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ScheduledRequestStatus.png");
		}
	}
	
	@Test(priority = 20)
	private void dispatchedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Dispatch tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.dispatchTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("RequestDispatchMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestDispatchMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DispatchedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DispatchedRequest.png");
		}

	}

	@Test(priority = 21)
	private void requestDispatchedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request has been dispacthed status");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("DispatchedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DispatchedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DispatchRequestStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DispatchRequestStatus.png");
		}

	}

	@Test(priority = 22)
	private void startedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Started tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.startTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("RequestStartedMessgae"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestStartedMessgae"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("RequestStarted.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("RequestStarted.png");
		}

	}

	@Test(priority = 23)
	private void requestStartedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request has been started status");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.requestStatus();
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
	private void completedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Completed tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.completedTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("RequestCompletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequuestCompletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CompletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CompletedRequest.png");
		}

	}

	@Test(priority = 25)
	private void cancelledRequest() throws IOException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.cancelledTigerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("RequestCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CancelRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CancelRequest.png");
		}

	}

	@Test(priority = 26)
	private void requestCancelledStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.cancelRequestStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("CancelledStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CancelledStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CancelRequestStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CancelRequestStatus.png");
		}

	}

	@Test(priority = 27)
	private void requestDraftStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request has been draft status");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.draftRequest();
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("DraftStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DraftStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DraftRequestStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DraftRequestStatus.png");
		}

	}

	@Test(priority = 28)
	private void deletedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Deleted tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.deletedTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + getPropertyValue("RequestDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DeletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DeletedRequest.png");
		}

	}

	@Test(priority = 29)
	private void searchRequestNo() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field Request No");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.searchRequestNo();
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
			File file = new File("searchRequestNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchRequestNo.png");
			mandatory.resetOption();
		}

	}

	@Test(priority = 30)
	private void searchLocation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field Location");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.searchLocation();
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
			File file = new File("searchRequestLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchRequestLocation.png");
			mandatory.clearSearch();
		}

	}

	@Test(priority = 31)
	private void searchFilterByDate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request List filter by date");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.filterByDate();
		String validateListFromDate = mandatory.validateListFromDate();
		String validateToDate = mandatory.validateToDate();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + validateListFromDate + "to" + validateToDate);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + validateListFromDate + "to" + validateToDate);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ((validateListFromDate + "to" + validateToDate).equals(validateListFromDate + "to" + validateToDate)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.resetOption();
			mandatory.requestLabel();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DateFilter.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DateFilter.png");
			mandatory.resetOption();
			mandatory.requestLabel();
		}

	}

	@Test(priority = 32)
	private void searchInvalid() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field is Invalid data");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.invalidSearch();
		extentTest.log(Status.INFO, "Actual Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result Validation Data -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchRequestInvalid.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchRequestInvalid.png");
		}

	}
	
}
