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
import com.zaigo.pageobjects.CustomerCreateOrganizationPage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerOrganizationJob extends BaseClass {
	static String JobListData;
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerOrganizationJob.html");
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
		extentTest.log(Status.INFO, "Expected  Result is -" + loginInPage.getPropertyValue("ValidationOfLandingPage"));
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

	@Test(priority = -1)
	private void contactModule() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Navigate to Customer Contact Page");
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

	@Test(priority = 0)
	private void createOrganization() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify a new Customer Organization is created successfully through [Create Organization]");
		CustomerCreateOrganizationPage create = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		create.organizationPage();
		create.contactPage("CreateContact");
		create.propertyPage();
		create.equipmentPage("SaveComplete");
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

	static String customerOrganizationJobListPage;

	@Test(priority = 1)
	private void listabelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User is Land on the Customer / Organization / Job page");
		JobPage jobPage = PageFactory.initElements(driver, JobPage.class);
		String jobLandPage = jobPage.labelValidation("Customer");
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("OrganizationJobListPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("OrganizationJobListPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerOrganizationJobListPage = jobPage.customerName("DetailScreenCustomerName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateRequestLabel.png");
			customerOrganizationJobListPage = jobPage.customerName("DetailScreenCustomerName");
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

	@Test(priority = 3)
	private void namePrepopulation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Customer Contact Name:(" + customerOrganizationJobListPage
				+ ") is prepopulated in the Contact Name Field");
		JobPage jobPage = PageFactory.initElements(driver, JobPage.class);
		String customerName = jobPage.customerName("PlaceHolderName");
		extentTest.log(Status.INFO, "Actual Result is -" + customerOrganizationJobListPage);
		extentTest.log(Status.INFO, "Expected Result is -" + customerName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (customerOrganizationJobListPage.equals(customerName)) {
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

	@Test(priority = 4)
	private void autoCompleteOrganizationContactCreation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Organization Contact Creation in the Autocomplete field");
		JobPage contactMandatory = PageFactory.initElements(driver, JobPage.class);
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

	@Test(priority = 5)
	private void mandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
		JobPage mandatoryValidation = PageFactory.initElements(driver, JobPage.class);
		mandatoryValidation.errorValidation("ButtonClick");
		String errorMandatoryValidation = mandatoryValidation.errorValidation("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobMandatoryLocationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobMandatoryLocationValidation.png");
		}
	}

	@Test(priority = 6)
	private void maximumValidationLocation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Location Field exceed its max-2048 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.locationField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
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

	@Test(priority = 7)
	private void maximumValidationTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Title Field exceed its max-256 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tittleField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("Tittle");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("Max256CharacterValidation"));
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

	@Test(priority = 8)
	private void mandatoryValidationDescription() throws AWTException, IOException {
		extentTest = extentReports.createTest(
				"Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		JobPage mandatoryValidation = PageFactory.initElements(driver, JobPage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("Description");
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

	@Test(priority = 9)
	private void maximumValidationDescription() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-2048 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.descriptionField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("Max2048Validation"));
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

	@Test(priority = 10)
	public void maximumTagValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tag field exceed its max-256 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tagsField("MaxCharacter");
		String errorPasswordField = mandatory.errorValidation("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("MaxTagValidation"));
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

	@Test(priority = 11)
	public void duplicateTagsValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when Duplicate tags are added");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tagsField("DuplicateTags");
		String errorPasswordField = mandatory.errorValidation("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("DuplicateTags"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DuplicateTags"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobDuplicateTagValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobDuplicateTagValidation.png");
		}

	}

	@Test(priority = 12)
	public void maxTagLimitValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when count of tags exceeds 64");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tagsField("MaxLimitTags");
		String errorPasswordField = mandatory.errorValidation("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("TagLimitValidation"));
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

	@Test(priority = 13)
	private void maximumValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when notes field exceed its max-2048 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.notesField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("Notes");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("Max2048Validation"));
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

	@Test(priority = 14)
	private void createButton() throws IOException {
		extentTest = extentReports.createTest(
				"Verify the Customer Create Job page Schedule Job Button is displayed in the Create form page");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String errorPasswordField = mandatory.errorValidation("Button");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ScheduleButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("ScheduleButton"))) {
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

	@Test(priority = 15)
	private void unsssignedJob() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Unassigned Job is created successfully from Customer Organization->Create Job");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Unassigned");
		String errorPasswordField = mandatory.message("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("JobCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.listValidation("JobNo1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			mandatory.message("AlternateForm");
			JobListData = mandatory.listValidation("JobNo1");
		}
	}

	@Test(priority = 16)
	private void jobCreatedCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the Customer Organization Job Count is added in the Total Job Count");
		JobPage create = PageFactory.initElements(driver, JobPage.class);
		int actualTotal = create.countValidation(1);
		int expectedResult = create.countValidation(2);
		extentTest.log(Status.INFO, "Actual Result - Total Job Count is:" + actualTotal);
		extentTest.log(Status.INFO, "Expected Result - Total Job Count is:" + expectedResult);
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

	@Test(priority = 17)
	private void jobUnassignedStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Unassigned Status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("UnassignedStatus"));
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

	@Test(priority = 18)
	private void labelEditValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Create Job page is opened from Organization-> Jobs -> Create Job");
		JobPage jobPage = PageFactory.initElements(driver, JobPage.class);
		String jobLandPage = jobPage.labelValidation("EditLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditPageJobLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("EditPageJobLabel"))) {
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

	@Test(priority = 19)
	private void editmandatoryValidationLocation() throws AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify Location field is set as Mandatory & Error Message is displayed when it is BLANK");
		JobPage mandatoryValidation = PageFactory.initElements(driver, JobPage.class);
		mandatoryValidation.clearAllFields("Page");
		String errorMandatoryValidation = mandatoryValidation.errorValidation("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobMandatoryLocationValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobMandatoryLocationValidation.png");
		}
	}

	@Test(priority = 20)
	private void editmaximumValidationLocation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Location Field exceed its max-2048 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.scrollUp();
		mandatory.locationField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
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

	@Test(priority = 21)
	private void editmaximumValidationTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Title Field exceed its max-256 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tittleField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("Tittle");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("Max256CharacterValidation"));
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

	@Test(priority = 22)
	private void editmandatoryValidationDescription() throws AWTException, IOException {
		extentTest = extentReports.createTest(
				"Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		JobPage mandatoryValidation = PageFactory.initElements(driver, JobPage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatoryValidation.clearValidation("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobMandatoryDescriptionValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobMandatoryDescriptionValidation.png");
			mandatoryValidation.clearValidation("Description");
		}
	}

	@Test(priority = 23)
	private void editmaximumValidationDescription() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-2048 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.descriptionField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("Max2048Validation"));
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

	@Test(priority = 24)
	public void editmaximumTagValidation() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tag field exceed its max-256 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tagsField("MaxCharacter");
		String errorPasswordField = mandatory.errorValidation("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("MaxTagValidation"));
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

	@Test(priority = 25)
	public void editduplicateTagsValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when Duplicate tags are added");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tagsField("DuplicateTags");
		String errorPasswordField = mandatory.errorValidation("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("DuplicateTags"));
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

	@Test(priority = 26)
	public void editmaxTagLimitValidation() throws IOException {
		extentTest = extentReports.createTest("Verify error message is displayed when count of tags exceeds 64");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tagsField("MaxLimitTags");
		String errorPasswordField = mandatory.errorValidation("Tag");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("TagLimitValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TagLimitValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.scrollDown();
			mandatory.clearValidation("RemoveMultipleTag");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobTagMaximumLimitValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobTagMaximumLimitValidation.png");
			mandatory.scrollDown();
			mandatory.clearValidation("RemoveMultipleTag");
		}

	}

	@Test(priority = 27)
	private void editmaximumValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when notes field exceed its max-2048 limit");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.notesField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("Notes");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("Max2048Validation"));
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

	@Test(priority = 28)
	private void checkResponseCode() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Attacthment response code in customer organization job module");
		JobPage initElements = PageFactory.initElements(driver, JobPage.class);
		initElements.attachmentFileCheck("CheckResponse");
		int responseCode = initElements.responseCode();
		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseCode);
		extentTest.log(Status.INFO, "Expected Result create response messages is -" + 200);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseCode == 200) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.attachmentFileCheck("ParentWindow");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
			initElements.attachmentFileCheck("ParentWindow");
		}
	}

	@Test(priority = 28)
	private void updateButton() throws IOException {
		extentTest = extentReports
				.createTest("Verify the Customer Edit Job page Update Job Button is displayed in the Edit form page");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String errorPasswordField = mandatory.errorValidation("Button");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdateButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("UpdateButton"))) {
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
	private void editJobwithFromDateFromTime()
			throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verfiy the unassigned Job is updated to scheduled when assigning the available technician");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("UpdateData");
		String errorPasswordField = mandatory.message("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("JobUpdatedMessage"));
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
			mandatory.techcnianNotAvailable();
			mandatory.message("AlternateForm");
		}

	}

	@Test(priority = 30)
	private void createJob_FromDateandTime_ToDateandTime()
			throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Create a Job  with From Date & Time - To Date & Time with Scheduled status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.jobStatusCreation("CreateJob");
		String errorPasswordField = mandatory.message("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("JobCreatedMessage"));
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
			mandatory.techcnianNotAvailable();
			mandatory.message("AlternateForm");
		}
	}

	@Test(priority = 31)
	private void jobScheduleStatus() throws WebDriverException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Schedule Status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("ScheduleStatus"));
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

	@Test(priority = 32)
	private void dispatchedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Dispatch tigger function in the List page");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tiggerFunction("Dispatch");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("JobDispatchMessage"));
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

	@Test(priority = 33)
	private void jobDispatchedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Dispatch Status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("DispatchedStatus"));
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

	@Test(priority = 34)
	private void startedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Started tigger function in the List page");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tiggerFunction("Start");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("JobStartedMessgae"));
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

	@Test(priority = 35)
	private void jobStartedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Active Status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("StartedStatus"));
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

	@Test(priority = 36)
	private void completedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Completed tigger function in the List page");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tiggerFunction("Complete");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("JobCompletedMessage"));
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

	@Test(priority = 37)
	private void jobCompletedStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Completed Status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("CompletedStatus"));
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
	private void cancelledJob() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Cancelled tigger function in the List page");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tiggerFunction("Cancel");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("JobCancelledMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobCancelledMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.listValidation("JobNo2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DeletedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DeletedJob.png");
			JobListData = mandatory.listValidation("JobNo2");
		}

	}

	@Test(priority = 39)
	private void jobCancelledStatus() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Job No:(" + JobListData + ") is in the Cancelled Status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		String errorPasswordField = mandatory.listValidation("Status1");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("CancelledStatus"));
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

	@Test(priority = 40)
	private void jobDraftStatus() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the Job has been draft status");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("CustomerContactDraft");
		String errorPasswordField = mandatory.listValidation("Status");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("DraftStatus"));
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

	@Test(priority = 41)
	private void deletedJob() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Deleted tigger function in the List page");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.tiggerFunction("Delete");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("JobDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("JobDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			JobListData = mandatory.listValidation("JobNo1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DeletedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DeletedJob.png");
			JobListData = mandatory.listValidation("JobNo1");
		}

	}

	@Test(priority = 42)
	private void searchJobNo() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Enter the Job No:(" + JobListData + ") in the Search field & Job list retrived successfully");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.listValidation("SearchData");
		String errorPasswordField = mandatory.listValidation("JobNo1");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("Search");
			JobListData = mandatory.listValidation("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchJobNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchJobNo.png");
			mandatory.clearValidation("Search");
			JobListData = mandatory.listValidation("Location");
		}

	}

	static String dateFrom;
	static String dateTo;

	@Test(priority = 43)
	private void searchLocation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Enter the Job Location:(" + JobListData + ") in the Search field & Job list retrived successfully");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.listValidation("SearchData");
		String errorPasswordField = mandatory.listValidation("Location");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearValidation("OrganizationSearch");
			mandatory.clearValidation("Search");
			dateFrom = mandatory.listValidation("ListFromDate");
			dateTo = mandatory.listValidation("ListToDate");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchLocation.png");
			mandatory.clearValidation("OrganizationSearch");
			mandatory.clearValidation("Search");
			dateFrom = mandatory.listValidation("ListFromDate");
			dateTo = mandatory.listValidation("ListToDate");
		}

	}

	@Test(priority = 44)
	private void searchFilterByDate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Job List filter by From date:" + dateFrom + " & To date:" + dateFrom);
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.listValidation("FilterDatePicker");
		String validateListFromDate = mandatory.listValidation("FromDate");
		String validateToDate = mandatory.listValidation("ToDate");
		extentTest.log(Status.INFO, "Actual Result is -" + validateListFromDate + " to " + validateToDate);
		extentTest.log(Status.INFO, "Expected  Result is -" + validateListFromDate + " to " + validateToDate);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ((validateListFromDate + " to " + validateToDate).equals(validateListFromDate + " to " + validateToDate)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchLocation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchLocation.png");
		}

	}

	@Test(priority = 45)
	private void searchInvalid() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.listValidation("Invlaid");
		String errorPasswordField = mandatory.listValidation("InvalidResult");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected  Result is -" + getPropertyValue("InvalidData"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("InvalidData"))) {
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
	
	@Test(priority = 48)
	private void dailyRecurring()
			throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Create a Job with Daily basic recurring job");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.jobStatusCreation("DailyRecurring");
		String errorPasswordField = mandatory.message("FormMessage");
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
			mandatory.techcnianNotAvailable();
			mandatory.message("AlternateForm");
		}
	}
	
	@Test(priority = 49)
	private void weeklyRecurring()
			throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Create a Job with Weekly basic recurring job");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.jobStatusCreation("WeeklyRecurring");
		String errorPasswordField = mandatory.message("FormMessage");
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
			mandatory.techcnianNotAvailable();
			mandatory.message("AlternateForm");
		}
	}
	
	@Test(priority = 50)
	private void monthlyRecurring()
			throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Create a Job with Monthly basic recurring job");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.jobStatusCreation("MonthlyRecurring");
		String errorPasswordField = mandatory.message("FormMessage");
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
			mandatory.techcnianNotAvailable();
			mandatory.message("AlternateForm");
		}
	}
	
	@Test(priority = 51)
	private void yearlyRecurring()
			throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Create a Job with Yearly basic recurring job");
		JobPage mandatory = PageFactory.initElements(driver, JobPage.class);
		mandatory.jobStatusCreation("Create");
		mandatory.jobStatusCreation("YearlyRecurring");
		String errorPasswordField = mandatory.message("FormMessage");
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
			mandatory.techcnianNotAvailable();
			mandatory.message("AlternateForm");
		}
	}

}
