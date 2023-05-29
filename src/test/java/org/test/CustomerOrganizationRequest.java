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
	public void setup() throws IOException {
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
			File file = new File("OrganizationLogin.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("OrganizationLogin.png");
		}
	}

	@Test(priority = 1)
	private void organizationModule() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Navigate to Customer Organization Page");
		CustomerCreateOrganizationPage initElements = PageFactory.initElements(driver,
				CustomerCreateOrganizationPage.class);
		String editContact = initElements.modulePage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerOrganizationList"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CustomerOrganizationList"))) {
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

	@Test(priority = 2)
	private void createOrganization() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify the Customer Organization Successful Message");
		CustomerCreateOrganizationPage create = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
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

	static String customerOrganizationRequestListPage;

	@Test(priority = 2)
	private void listabelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User is Land on the Customer / Organization / Request page");
		RequestPage jobPage = PageFactory.initElements(driver, RequestPage.class);
		String jobLandPage = jobPage.labelValidation("Customer");
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("OrganizationRequestListPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("OrganizationRequestListPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerOrganizationRequestListPage = jobPage.customerName("DetailScreenCustomerName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateRequestLabel.png");
			customerOrganizationRequestListPage = jobPage.customerName("DetailScreenCustomerName");
		}
	}

	@Test(priority = 3)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Create Request page is opened from Organization-> Request -> Create Request");
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

	@Test(priority = 4)
	private void namePrepopulation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Customer Organization Name:("
				+ customerOrganizationRequestListPage + ") is prepopulated in the Organization Name Field");
		RequestPage jobPage = PageFactory.initElements(driver, RequestPage.class);
		String customerName = jobPage.customerName("PlaceHolderName");
		extentTest.log(Status.INFO, "Actual Result is -" + customerOrganizationRequestListPage);
		extentTest.log(Status.INFO, "Expected Result is -" + customerName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (customerOrganizationRequestListPage.equals(customerName)) {
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

	@Test(priority = 5)
	private void autoCompleteOrganizationContactCreation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Organization Contact Creation in the Autocomplete field");
		RequestPage contactMandatory = PageFactory.initElements(driver, RequestPage.class);
		contactMandatory.autoCompleteField("OrganizationContactCreate");
		String errorContact = contactMandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContact.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contactMandatory.autoCompleteField("VisibleName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AutocompleteOrganzationContactCreate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AutocompleteOrganzationContactCreate.png");
			contactMandatory.autoCompleteField("VisibleName");
		}

	}

	@Test(priority = 6)
	private void mandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
		RequestPage mandatoryValidation = PageFactory.initElements(driver, RequestPage.class);
		mandatoryValidation.clickEvent("ButtonClick");
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

	@Test(priority = 7)
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

	@Test(priority = 8)
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
			File file = new File("CustomerOrgnizationRequestTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrgnizationRequestTittleMaximumValidation.png");
			mandatory.clearFields("Tittle");
		}

	}

	@Test(priority = 9)
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
			File file = new File("CustomerOrganizationRequestMandatoryDescriptionValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestMandatoryDescriptionValidation.png");
		}
	}

	@Test(priority = 10)
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
			File file = new File("CustomerOrganizationRequestDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
		}

	}

	@Test(priority = 11)
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
			File file = new File("CustomerOrganizationRequestTagMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestTagMaximumValidation.png");
			mandatory.clearFields("Tag");
		}

	}

	@Test(priority = 12)
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
			File file = new File("CustomerOrganizationRequestDuplicateTagValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestDuplicateTagValidation.png");
			mandatory.clearFields("TagRemove");
		}

	}

	@Test(priority = 13)
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
			File file = new File("CustomerOrganizationRequestTagMaximumLimitValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestTagMaximumLimitValidation.png");
			mandatory.clearFields("RemoveMultipleTag");
		}

	}

	@Test(priority = 14)
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
			File file = new File("CustomerOrganizationRequestNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestNotesMaximumValidation.png");
			mandatory.clearFields("Notes");
		}

	}

	@Test(priority = 15)
	private void createButton() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify the Customer Create Request page Schedule Request Button is displayed in the Create form page");
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

	@Test(priority = 16)
	private void unsssignedRequest() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Unassigned Request is created successfully from Customer Organization->Create Request");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.validData("Unassigned");
		String errorPasswordField = mandatory.message("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerOrganizationRequestListPage = mandatory.listValidation("RequestNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationUnscheduleRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationUnscheduleRequest.png");
			customerOrganizationRequestListPage = mandatory.listValidation("RequestNo");
		}
	}

	@Test(priority = 17)
	private void requestCreatedCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the Customer Organziation Request Count is added in the Total Request Count");
		RequestPage create = PageFactory.initElements(driver, RequestPage.class);
		int actualTotal = create.countValidation(1);
		int expectedResult = create.countValidation(2);
		extentTest.log(Status.INFO, "Actual Result - Total Request Count is:" + actualTotal);
		extentTest.log(Status.INFO, "Expected Result - Total Request Count is:" + expectedResult);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualTotal == expectedResult) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("JobCountValidate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("JobCountValidate.png");
		}

	}

	@Test(priority = 18)
	private void requestUnassignedStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Request No:(" + customerOrganizationRequestListPage + ") is in the Unassigned Status");
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
			File file = new File("CustomerOrganizationUnscheduleStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationUnscheduleStatus.png");
		}
	}

	@Test(priority = 19)
	private void labelEditValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Edit Request page is opened from Organization-> Request -> Edit Request");
		RequestPage jobPage = PageFactory.initElements(driver, RequestPage.class);
		String jobLandPage = jobPage.labelValidation("EditLabel");
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
			File file = new File("CreateRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateRequestLabel.png");
			jobPage.clearAllFields();
		}
	}

	@Test(priority = 20)
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

	@Test(priority = 21)
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

	@Test(priority = 22)
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
			File file = new File("CustomerOrgnizationRequestTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrgnizationRequestTittleMaximumValidation.png");
			mandatory.clearFields("Tittle");
		}

	}

	@Test(priority = 23)
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
			File file = new File("CustomerOrganizationRequestMandatoryDescriptionValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestMandatoryDescriptionValidation.png");
		}
	}

	@Test(priority = 24)
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
			File file = new File("CustomerOrganizationRequestDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
		}

	}

	@Test(priority = 25)
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
			File file = new File("CustomerOrganizationRequestTagMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestTagMaximumValidation.png");
			mandatory.clearFields("Tag");
		}

	}

	@Test(priority = 26)
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
			File file = new File("CustomerOrganizationRequestDuplicateTagValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestDuplicateTagValidation.png");
			mandatory.clearFields("TagRemove");
		}

	}

	@Test(priority = 27)
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
			File file = new File("CustomerOrganizationRequestTagMaximumLimitValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestTagMaximumLimitValidation.png");
			mandatory.clearFields("RemoveMultipleTag");
		}

	}

	@Test(priority = 28)
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
			File file = new File("CustomerOrganizationRequestNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestNotesMaximumValidation.png");
			mandatory.clearFields("Notes");
		}

	}

	@Test(priority = 29)
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

	@Test(priority = 29)
	private void updatedRequest() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Scheduled Request is updated successfully from Customer Contact->Edit Request");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.validData("Updated");
		String errorPasswordField = mandatory.message("FormMessage");
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
			mandatory.message("AlternateFormMessage");
		}
	}

	@Test(priority = 30)
	private void createRequest_FromDateandTime_ToDateandTime()
			throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Create a Request with From Date & Time - To Date & Time with Scheduled status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.validData("Schedule");
		String errorPasswordField = mandatory.message("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerOrganizationRequestListPage = mandatory.listValidation("RequestNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationCreatedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationCreatedRequest.png");
			mandatory.message("AlternateFormMessage");
			customerOrganizationRequestListPage = mandatory.listValidation("RequestNo");
		}
	}

	@Test(priority = 31)
	private void requestScheduleStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Request No:(" + customerOrganizationRequestListPage + ") is in the Schedule Status");
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
			File file = new File("CustomerOrganizationScheduledStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationScheduledStatus.png");
		}
	}

	@Test(priority = 32)
	private void dispatchedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Dispatch tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("Dispatach");
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
			File file = new File("CustomerOrganizationDispatchedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDispatchedRequest.png");
		}

	}

	@Test(priority = 33)
	private void requestDispatchedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Request No:(" + customerOrganizationRequestListPage + ") is in the Dispatch Status");
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
			File file = new File("CustomerOrganizationDispatchStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDispatchStatus.png");
		}

	}

	@Test(priority = 34)
	private void startedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Started tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("Start");
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
			File file = new File("CustomerOrganizationRequestStarted.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationRequestStarted.png");
		}

	}

	@Test(priority = 35)
	private void requestStartedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Request No:(" + customerOrganizationRequestListPage + ") is in the Active Status");
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
			File file = new File("CustomerOrganizationStartedStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationStartedStatus.png");
		}

	}

	@Test(priority = 36)
	private void completedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Completed tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("Complete");
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
			File file = new File("CustomerOrganizationCompletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationCompletedRequest.png");
		}

	}

	@Test(priority = 37)
	private void requestCompleteStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Request No:(" + customerOrganizationRequestListPage + ") is in the Completed Status");
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

	@Test(priority = 38)
	private void cancelledRequest() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("Cancel");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerOrganizationRequestListPage = mandatory.listValidation("CancelRequestNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationDeletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDeletedRequest.png");
			customerOrganizationRequestListPage = mandatory.listValidation("CancelRequestNo");
		}

	}

	@Test(priority = 39)
	private void requestCancelledStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Request No:(" + customerOrganizationRequestListPage + ") is in the Cancelled Status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		String errorPasswordField = mandatory.listValidation("CancelStatus");
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

	@Test(priority = 40)
	private void requestDraftStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Request has been draft status");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.draft("Draft");
		mandatory.message("Message");
		String errorPasswordField = mandatory.listValidation("Status");
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

	@Test(priority = 41)
	private void deletedRequest() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Deleted tigger function in the List page");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.tiggerFunction("Delete");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("RequestDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("RequestDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerOrganizationRequestListPage = mandatory.listValidation("RequestNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationDeletedRequest.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationDeletedRequest.png");
			customerOrganizationRequestListPage = mandatory.listValidation("RequestNo");
		}

	}

	static String dateFrom;
	static String dateTo;

	@Test(priority = 42)
	private void searchRequestNo() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Enter the Request No:(" + customerOrganizationRequestListPage
				+ ") in the Search field & Request list retrived successfully");
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.listValidation("SearchData");
		String errorPasswordField = mandatory.listValidation("RequestNo");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerOrganizationRequestListPage = mandatory.listValidation("Location");
			mandatory.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationsearchRequestNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationsearchRequestNo.png");
			customerOrganizationRequestListPage = mandatory.listValidation("Location");
			mandatory.clearFields("Search");
		}

	}

	@Test(priority = 43)
	private void searchLocation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Enter the Request Location:(" + customerOrganizationRequestListPage
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
			File file = new File("CustomerOrganizationsearchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationsearchLocation.png");
			dateFrom = mandatory.dateFrom();
			dateTo = mandatory.dateTo();
			mandatory.clearFields("Search");
		}

	}

	@Test(priority = 44)
	private void searchFilterByDate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Request List filter by From date:" + dateFrom + " & To date:" + dateTo);
		RequestPage mandatory = PageFactory.initElements(driver, RequestPage.class);
		mandatory.filterByDate();
		String validateListFromDate = mandatory.validateListFromDate();
		String validateToDate = mandatory.validateToDate();
		extentTest.log(Status.INFO, "Actual Result is -" + validateListFromDate + "to" + validateToDate);
		extentTest.log(Status.INFO, "Expected Result is -" + validateListFromDate + "to" + validateToDate);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ((validateListFromDate + "to" + validateToDate).equals(validateListFromDate + "to" + validateToDate)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerOrganizationsearchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationsearchLocation.png");
			mandatory.clearFields("Search");
		}

	}

	@Test(priority = 45)
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
			File file = new File("CustomerOrganizationsearchInvalid.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerOrganizationsearchInvalid.png");
		}

	}
}
