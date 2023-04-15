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
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.RequestPage;
import com.zaigo.utility.BrowserSetup;

public class GlobalContactRequest extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("GlobalContactRequest.html");
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

	@Test(priority = -1) // 1-Login
	public void loginPage() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password"));
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

	@Test(priority = 0)
	public void requestModule() throws InterruptedException, WebDriverException, IOException {
		extentTest = extentReports
				.createTest("Verify Global Request List Page is opened when clicking on Global Request");
		RequestPage module = PageFactory.initElements(driver, RequestPage.class);
		String editContact = module.labelValidation("Global");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestListPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("RequestListPage"))) {
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

	@Test(priority = 1)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Create Request page is opened from Global-> Request -> Create Request");
		RequestPage requestPage = PageFactory.initElements(driver, RequestPage.class);
		String requestLandPage = requestPage.labelValidation("CreateLabel");
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

	@Test(priority = 2)
	private void contactMandatoryValidation() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation in Contact Field");
		RequestPage contactMandatory = PageFactory.initElements(driver, RequestPage.class);
		contactMandatory.clickEvent("RadioContact");
		String errorContact = contactMandatory.errorField("CustomerField");
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
		RequestPage contactMandatory = PageFactory.initElements(driver, RequestPage.class);
		contactMandatory.autoCompleteField("ContactCreate");
		String errorContact = contactMandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContact.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contactMandatory.autoCompleteField("GlobalContactVisibleName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AutocompleteContactCreate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AutocompleteContactCreate.png");
			contactMandatory.message("AlternateFunction");
			contactMandatory.autoCompleteField("GlobalContactVisibleName");
		}

	}

	@Test(priority = 4)
	private void mandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
		RequestPage mandatoryValidation = PageFactory.initElements(driver, RequestPage.class);
		String errorMandatoryValidation = mandatoryValidation.errorField("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestMandatoryLocationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestMandatoryLocationValidation.png");
		}
	}

	@Test(priority = 5)
	private void maximumValidationLocation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Location Field exceed its max-2048 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.location("MaxValidation");
		String errorPasswordField = mandatory.errorField("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestLocationMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestLocationMaximumValidation.png");
			mandatory.clearFields("Location");
		}

	}

	@Test(priority = 6)
	private void maximumValidationTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Title Field exceed its max-256 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tittle("MaxValidation");
		String errorPasswordField = mandatory.errorField("Tittle");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestTittleMaximumValidation.png");
			mandatory.clearFields("Tittle");
		}

	}

	@Test(priority = 7)
	private void mandatoryValidationDescription() throws AWTException, IOException {
		extentTest = extentReports.createTest(
				"Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		RequestPage mandatoryValidation = PageFactory.initElements(driver, RequestPage.class);
		String errorMandatoryValidation = mandatoryValidation.errorField("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
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
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-2048 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.description("MaxValidation");
		String errorPasswordField = mandatory.errorField("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
		}

	}

	@Test(priority = 9)
	public void maximumTagValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tag field exceed its max-256 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tags("MaxValidation");
		String errorPasswordField = mandatory.errorField("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MaxTagValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MaxTagValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tag");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestTagMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestTagMaximumValidation.png");
			mandatory.clearFields("Tag");
		}

	}

	@Test(priority = 10)
	public void duplicateTagsValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when Duplicate tags are added");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tags("DuplicateTags");
		String errorPasswordField = mandatory.errorField("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DuplicateTags"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DuplicateTags"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("TagRemove");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestDuplicateTagValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestDuplicateTagValidation.png");
			mandatory.clearFields("TagRemove");
		}

	}

	@Test(priority = 11)
	public void maxTagLimitValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when count of tags exceeds 64");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tags("MaxLimitTag");
		String errorPasswordField = mandatory.errorField("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TagLimitValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TagLimitValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("RemoveMultipleTag");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestTagMaximumLimitValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestTagMaximumLimitValidation.png");
			mandatory.clearFields("RemoveMultipleTag");
		}

	}

	@Test(priority = 12)
	private void maximumValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when notes field exceed its max-2048 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.notes("MaxValidation");
		String errorPasswordField = mandatory.errorField("Notes");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Notes");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestNotesMaximumValidation.png");
			mandatory.clearFields("Notes");
		}

	}

	@Test(priority = 13)
	private void createButton() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify the Global Create Request page Schedule Request Button is displayed in the Create form page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCreateButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreateButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobNotesMaximumValidation.png");
		}

	}

	static String CustomerListPage;

	@Test(priority = 14)
	private void unsssignedRequest() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Unassigned Request is created successfully from Global Request Contact->Create Request");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.validData("Unassigned");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			CustomerListPage = mandatory.listValidation("RequestNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactUnscheduleRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactUnscheduleRequest.png");
			CustomerListPage = mandatory.listValidation("RequestNo");
		}
	}

	@Test(priority = 15)
	private void requestUnassignedStatus() throws WebDriverException, IOException {
		extentTest = extentReports
				.createTest("Verify the Request No:(" + CustomerListPage + ") is in the Unassigned Status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UnassignedStatus"));
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

	@Test(priority = 16)
	private void labelEditValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Edit Request page is opened from Contacts-> Request -> Edit Request");
		RequestPage jobPage = PageFactory.initElements(driver, RequestPage.class);
		String jobLandPage = jobPage.labelValidation("GlobalEdit");
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditPageRequestLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("EditPageRequestLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			jobPage.clearAllFields();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateJobLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateJobLabel.png");
			jobPage.clearAllFields();
		}
	}

	@Test(priority = 17)
	private void editmandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
		RequestPage mandatoryValidation = PageFactory.initElements(driver, RequestPage.class);
		String errorMandatoryValidation = mandatoryValidation.errorField("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestMandatoryLocationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestMandatoryLocationValidation.png");
		}
	}

	@Test(priority = 18)
	private void editmaximumValidationLocation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Location Field exceed its max-2048 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.location("MaxValidation");
		String errorPasswordField = mandatory.errorField("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestLocationMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestLocationMaximumValidation.png");
			mandatory.clearFields("Location");
		}
	}

	@Test(priority = 19)
	private void editmaximumValidationTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Title Field exceed its max-256 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tittle("MaxValidation");
		String errorPasswordField = mandatory.errorField("Tittle");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestTittleMaximumValidation.png");
			mandatory.clearFields("Tittle");
		}

	}

	@Test(priority = 20)
	private void editmandatoryValidationDescription() throws AWTException, IOException {
		extentTest = extentReports.createTest(
				"Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		RequestPage mandatoryValidation = PageFactory.initElements(driver, RequestPage.class);
		String errorMandatoryValidation = mandatoryValidation.errorField("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
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

	@Test(priority = 21)
	private void editmaximumValidationDescription() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-2048 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.description("MaxValidation");
		String errorPasswordField = mandatory.errorField("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
		}

	}

	@Test(priority = 22)
	public void editmaximumTagValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tag field exceed its max-256 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tags("MaxValidation");
		String errorPasswordField = mandatory.errorField("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MaxTagValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MaxTagValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tag");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestMaximumTagValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestMaximumTagValidation.png");
			mandatory.clearFields("Tag");
		}

	}

	@Test(priority = 23)
	public void editduplicateTagsValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when Duplicate tags are added");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tags("DuplicateTags");
		String errorPasswordField = mandatory.errorField("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DuplicateTags"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DuplicateTags"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("TagRemove");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestDuplicateTagValidation.png");
			mandatory.clearFields("TagRemove");
		}

	}

	@Test(priority = 24)
	public void editmaxTagLimitValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when count of tags exceeds 64");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tags("MaxLimitTag");
		String errorPasswordField = mandatory.errorField("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TagLimitValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TagLimitValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("RemoveMultipleTag");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestTagMaximumLimitValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestTagMaximumLimitValidation.png");
			mandatory.clearFields("RemoveMultipleTag");
		}

	}

	@Test(priority = 25)
	private void editmaximumValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when notes field exceed its max-2048 limit");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.notes("MaxValidation");
		String errorPasswordField = mandatory.errorField("Notes");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Notes");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactRequestNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactRequestNotesMaximumValidation.png");
			mandatory.clearFields("Notes");
		}

	}

	@Test(priority = 26)
	private void updateButton() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify the Customer Create Request page Update Request Button is displayed in the Create form page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestUpdateButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestUpdateButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobNotesMaximumValidation.png");
		}

	}

	@Test(priority = 27)
	private void updatedRequest() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Scheduled Request is updated successfully from Customer Contact->Edit Request");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.validData("Updated");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestUpdatedMessage"))) {
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

	@Test(priority = 28)
	private void createRequest_FromDateandTime_ToDateandTime()
			throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Create a Request  with From Date & Time - To Date & Time with Scheduled status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.labelValidation("CreateLabel");
		mandatory.autoCompleteField("ContactCreate");
		mandatory.autoCompleteField("GlobalContactVisibleName");
		mandatory.validData("GlobalSchedule");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			CustomerListPage = mandatory.listValidation("RequestNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactCreatedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactCreatedRequest.png");
			CustomerListPage = mandatory.listValidation("RequestNo");
		}
	}

	@Test(priority = 29)
	private void requestScheduleStatus() throws WebDriverException, IOException {
		extentTest = extentReports
				.createTest("Verify the Request No:(" + CustomerListPage + ") is in the Schedule Status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
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

	@Test(priority = 30)
	private void dispatchedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Dispatch tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("GlobalDispatch");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestDispatchMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestDispatchMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactDispatchedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactDispatchedRequest.png");
		}

	}

	@Test(priority = 31)
	private void requestDispatchedStatus() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Request No:(" + CustomerListPage + ") is in the Dispatch Status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DispatchedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DispatchedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactDispatchStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactDispatchStatus.png");
		}

	}

	@Test(priority = 32)
	private void startedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Started tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("GlobalStart");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestStartedMessgae"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestStartedMessgae"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactRequestStarted.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactRequestStarted.png");
		}

	}

	@Test(priority = 33)
	private void requestStartedStatus() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Request No:(" + CustomerListPage + ") is in the Active Status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("StartedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("StartedStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactStartedStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactStartedStatus.png");
		}

	}

	@Test(priority = 34)
	private void completedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Completed tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("GlobalComplete");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCompletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCompletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactCompletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactCompletedRequest.png");
		}

	}

	@Test(priority = 35)
	private void requestCompleteStatus() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Request No:(" + CustomerListPage + ") is in the Completed Status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompletedStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CompletedStatus"))) {
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

	@Test(priority = 36)
	private void cancelledRequest() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("GlobalCancel");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			CustomerListPage = mandatory.listValidation("CancelRequestNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactDeletedRquest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactDeletedRquest.png");
			CustomerListPage = mandatory.listValidation("CancelRequestNo");
		}

	}

	@Test(priority = 37)
	private void requestCancelledStatus() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Request No:(" + CustomerListPage + ") is in the Cancelled Status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.listValidation("CancelStatus");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CancelledStatus"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CancelledStatus"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			CustomerListPage = mandatory.listValidation("CustomerName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactRequestDraftStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactRequestDraftStatus.png");
			CustomerListPage = mandatory.listValidation("CustomerName");
		}

	}

	@Test(priority = 38)
	private void searchCustomerName() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Enter the Customer Name: (" + CustomerListPage
				+ ") in the Search field & Request list retrived successfully");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.listValidation("SearchData");
		String errorPasswordField = mandatory.listValidation("CustomerName");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			CustomerListPage = mandatory.listValidation("RequestNo");
			mandatory.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("SearchCustomerName.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("SearchCustomerName.png");
			CustomerListPage = mandatory.listValidation("RequestNo");
			mandatory.clearFields("Search");
		}

	}

	@Test(priority = 39)
	private void searchRequestNo() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Enter the Request No:(" + CustomerListPage
				+ ") in the Search field & Request list retrived successfully");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.listValidation("SearchData");
		String errorPasswordField = mandatory.listValidation("RequestNo");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			CustomerListPage = mandatory.listValidation("Location");
			mandatory.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactsearchRequestNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactsearchRequestNo.png");
			CustomerListPage = mandatory.listValidation("Location");
			mandatory.clearFields("Search");
		}

	}

	static String dateFrom;
	static String dateTo;

	@Test(priority = 40)
	private void searchLocation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Enter the Request Location:(" + CustomerListPage
				+ ") in the Search field & Request list retrived successfully");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.listValidation("SearchData");
		String errorPasswordField = mandatory.listValidation("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			dateFrom = mandatory.dateFrom();
			dateTo = mandatory.dateTo();
			mandatory.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("GlobalContactRequestsearchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("GlobalContactRequestsearchLocation.png");
			dateFrom = mandatory.dateFrom();
			dateTo = mandatory.dateTo();
			mandatory.clearFields("Search");
		}

	}

	@Test(priority = 41)
	private void searchFilterByDate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Request List filter by From date:" + dateFrom + " & To date:" + dateTo);
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.filterByDate();
		String validateListFromDate = mandatory.validateListFromDate();
		extentTest.log(Status.INFO, "Actual Result is -" + validateListFromDate);
		extentTest.log(Status.INFO, "Expected Result is -" + validateListFromDate);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ((validateListFromDate).equals(validateListFromDate)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DateFilter.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DateFilter.png");
			mandatory.clearFields("Search");
		}

	}

	@Test(priority = 42)
	private void searchInvalid() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Search field is Invalid data");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.listValidation("Invlaid");
		String errorPasswordField = mandatory.listValidation("Invalid");
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
