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
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.RequestPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerOrganizationRequest extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerOrganizationRequest.html");
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
		extentTest = extentReports.createTest("Verify Customer Organization List Page is opened when clicking on Cusotmer->Organization");
		CustomerCreateOrganizationPage modulePage = new CustomerCreateOrganizationPage(driver);
		modulePage.modulePage();

	}

	@Test(priority = 3)
	private void createOrganization() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Customer Organization Successful Message");
		CustomerCreateOrganizationPage create = new CustomerCreateOrganizationPage(driver);
		create.organizationPage();
		create.contactPage("CreateContact");
		create.propertyPage();
		create.equipmentPage();
		String listName = create.responseMessage("ResponseMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + listName);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
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
			create.responseMessage("AlternateFunction");
		}
	}

	@Test(priority = 4)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify Create Request page is opened from Organization-> Request -> Create Request");
		RequestPage requestPage = new RequestPage(driver);
		requestPage.customerOrganizationRequestListPage();
		String requestLandPage = requestPage.requestLandPage();
		extentTest.log(Status.INFO, "Actual Result is -" + requestLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatePageRequestLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (requestLandPage.equals(getPropertyValue("CreatePageRequestLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateOrganizationRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateOrganizationRequestLabel.png");
		}
	}

	@Test(priority = 5)
	private void autoCompleteOrganizationContactCreation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Organization Contact Creation in the Autocomplete field");
		RequestPage contactMandatory = new RequestPage(driver);
		Thread.sleep(5000);
		contactMandatory.organizationContactCreate();
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
			File file = new File("AutocompleteOrganzationContactCreate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AutocompleteOrganzationContactCreate.png");
		}

	}

//	@Test(priority = 5)
	private void mandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
		RequestPage mandatoryValidation = new RequestPage(driver);
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
			File file = new File("CustomerContactRequestMandatoryLocationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestMandatoryLocationValidation.png");
			mandatoryValidation.picKLocation();
		}
	}

//	@Test(priority = 4)
	private void maximumValidationLocation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify Error Message is displayed when Location Field exceed its max-256 limit");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.maxValidationLocationField();
		String errorPasswordField = mandatory.locationError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
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
		extentTest = extentReports.createTest("Verify Error Message is displayed when Title Field exceed its max-256 limit");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.maxValidationTittle();
		String errorPasswordField = mandatory.tittleError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearTittle();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrgnizationRequestTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrgnizationRequestTittleMaximumValidation.png");
			mandatory.clearTittle();
		}

	}

	@Test(priority = 7)
	private void mandatoryValidationDescription() throws AWTException, IOException {
		extentTest = extentReports.createTest("Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		RequestPage mandatoryValidation = new RequestPage(driver);
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
			File file = new File("CustomerOrganizationRequestMandatoryDescriptionValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestMandatoryDescriptionValidation.png");
		}
	}

	@Test(priority = 8)
	private void maximumValidationDescription() throws IOException {
		extentTest = extentReports.createTest("Verify Error Message is displayed when Description field exceed its max-2048 limit");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.maxValidationDescription();
		String errorPasswordField = mandatory.descriptionError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearDescription();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationRequestDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestDescriptionMaximumValidation.png");
			mandatory.clearDescription();
		}

	}

	@Test(priority = 11)
	public void maximumTagValidation() throws IOException {
		extentTest = extentReports.createTest("Verify Error Message is displayed when Tag field exceed its max-256 limit");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.maxCharacterTag();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MaxTagValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MaxTagValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearTag();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationRequestTagMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestTagMaximumValidation.png");
			mandatory.clearTag();
		}

	}

	@Test(priority = 12)
	public void duplicateTagsValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when Duplicate tags are added");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.duplicateTags();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DuplicateTags"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DuplicateTags"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.removeTags();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationRequestDuplicateTagValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestDuplicateTagValidation.png");
			mandatory.removeTags();
		}

	}

	@Test(priority = 13)
	public void maxTagLimitValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when count of tags exceeds 64");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.maxTagCountValidation();
		String errorPasswordField = mandatory.tagsError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TagLimitValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TagLimitValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.removeMultipleTags();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationRequestTagMaximumLimitValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestTagMaximumLimitValidation.png");
			mandatory.removeMultipleTags();
		}

	}

	@Test(priority = 14)
	private void maximumValidationNotes() throws IOException {
		extentTest = extentReports.createTest("Verify Error Message is displayed when notes field exceed its max-2048 limit");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.maxValidationNotes();
		String errorPasswordField = mandatory.notesError();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearNotes();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationRequestNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestNotesMaximumValidation.png");
			mandatory.clearNotes();
		}

	}

	@Test(priority = 15)
	private void unsssignedRequest() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify Unassigned Request is created successfully from Customer Organization->Create Request");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.fromDateTimeScheduleRequest();
		String errorPasswordField = mandatory.createdMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("requestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationUnscheduleRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationUnscheduleRequest.png");
		}
	}

	@Test(priority = 16)
	private void requestUnassignedStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Check the Request Status as an Unassigned");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UnassignedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("UnassignedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationUnscheduleStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationUnscheduleStatus.png");
		}
	}

	@Test(priority = 18)
	private void createRequest_FromDateandTime_ToDateandTime()
			throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Create a Request with From Date & Time - To Date & Time with Scheduled status");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.customerOrganizationRequest();
		mandatory.createdRequest();
		String errorPasswordField = mandatory.createdMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationCreatedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationCreatedRequest.png");
		}
	}

	@Test(priority = 19)
	private void requestScheduleStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Check the Request Status as an Scheduled");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ScheduleStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("ScheduleStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationScheduledStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationScheduledStatus.png");
		}
	}

	@Test(priority = 20)
	private void dispatchedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Dispatch tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.dispatchTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestDispatchMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestDispatchMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationDispatchedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDispatchedRequest.png");
		}

	}

	@Test(priority = 21)
	private void requestDispatchedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request has been dispacthed status");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DispatchedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationDispatchStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDispatchStatus.png");
		}

	}

	@Test(priority = 22)
	private void startedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Started tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.startTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestStartedMessgae"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestStartedMessgae"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationRequestStarted.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestStarted.png");
		}

	}

	@Test(priority = 23)
	private void requestStartedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request has been started status");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("StartedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("StartedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationStartedStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationStartedStatus.png");
		}

	}

	@Test(priority = 24)
	private void completedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Completed tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.completedTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCompletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCompletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationCompletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationCompletedRequest.png");
		}

	}

	@Test(priority = 25)
	private void cancelledRequest() throws IOException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.cancelledTigerFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationDeletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDeletedRequest.png");
		}

	}

	@Test(priority = 26)
	private void requestCancelledStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request has been cancelled status");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.cancelRequestStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CancelledStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CancelledStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationDraftStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDraftStatus.png");
		}

	}

	@Test(priority = 27)
	private void requestDraftStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request has been draft status");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.draftRequest1();
		String errorPasswordField = mandatory.requestStatus();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DraftStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DraftStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationDraftStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDraftStatus.png");
		}

	}

	@Test(priority = 28)
	private void deletedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Deleted tigger function in the List page");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.deletedTiggerFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationDeletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDeletedRequest.png");
		}

	}

	@Test(priority = 29)
	private void searchRequestNo() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field Request No");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.searchRequestNo1();
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
			File file = new File("CustomerOrganizationsearchRequestNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationsearchRequestNo.png");
			mandatory.resetOption();
		}

	}

	@Test(priority = 30)
	private void searchLocation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field Location");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.searchLocation1();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearSearch1();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationsearchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationsearchLocation.png");
			mandatory.clearSearch1();
		}

	}

	@Test(priority = 31)
	private void searchFilterByDate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request List filter by date");
		RequestPage mandatory = new RequestPage(driver);
		mandatory.filterByDate1();
		String validateListFromDate = mandatory.validateListFromDate();
		String validateToDate = mandatory.validateToDate();
		extentTest.log(Status.INFO, "Actual Result is -" + validateListFromDate + "to" + validateToDate);
		extentTest.log(Status.INFO, "Expected Result is -" + validateListFromDate + "to" + validateToDate);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ((validateListFromDate + "to" + validateToDate).equals(validateListFromDate + "to" + validateToDate)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.resetOption();
			mandatory.requestLabel();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationsearchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationsearchLocation.png");
			mandatory.resetOption();
			mandatory.requestLabel();
		}

	}

	@Test(priority = 32)
	private void searchInvalid() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field is Invalid data");
		RequestPage mandatory = new RequestPage(driver);
		String errorPasswordField = mandatory.invalidSearch1();
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationsearchInvalid.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationsearchInvalid.png");
		}

	}
}
