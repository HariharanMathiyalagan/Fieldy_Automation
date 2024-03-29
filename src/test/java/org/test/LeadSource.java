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
import com.zaigo.pageobjects.BusinessDaysPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class LeadSource extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String ListField;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("LeadSource.html");
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
	private void settingModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Navigate to Business Settings page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.modulePage();
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("BusinessDaysPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("BusinessDaysPage"))) {
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
	private void leadSourceLabel() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Lead Source Label is displayed in the Business Settings page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.businessDaysLabel("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceModule"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("LeadSourceModule"))) {
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
	private void createFormLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Lead Source Page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.labelValidation("LeadSourceCreate");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreateLeadSourcePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CreateLeadSourcePage"))) {
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

	@Test(priority = 4)
	private void mandatoryValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Lead Source Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		initElements.leadSourceField("Mandatory");
		String editContact = initElements.errorField("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearField("LeadSource");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearField("LeadSource");
		}
	}

	@Test(priority = 4)
	private void maximumValidationLeadSource() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Lead Source Name Field exceed its max-256 limit");
		BusinessDaysPage mandatory = PageFactory.initElements(driver, BusinessDaysPage.class);
		mandatory.leadSourceField("MaxValidation");
		String errorPasswordField = mandatory.errorField("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("LeadSource");
			ListField = mandatory.leadSourceField("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("LeadSource");
			ListField = mandatory.leadSourceField("ValidData");
		}

	}

	@Test(priority = 6)
	public void verifySaveButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Create Lead Source Save & Complete Button is displayed in the Create form page");
		BusinessDaysPage contractorPage = PageFactory.initElements(driver, BusinessDaysPage.class);
		String text_button = contractorPage.buttonValidation("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SaveButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("SaveButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
			contractorPage.buttonValidation("ButtonClick");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
			contractorPage.buttonValidation("ButtonClick");
		}

	}

	@Test(priority = 7)
	private void leadSourceCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify a new Lead Source is created successfully through [Business Settings]");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("LeadSourceCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.message("AlternateFunction");
		}
	}

	@Test(priority = 8)
	private void leadSourceCreateList() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify a newly created Lead Source Name:" + ListField + "is displayed in the Lead Source List page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.listValdidation("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + ListField);
		extentTest.log(Status.INFO, "Expected Result is -" + createMessage);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("BusinessNameList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BusinessNameList.png");
		}
	}

	@Test(priority = 9)
	private void activeStatus() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify a newly created Lead Source is in the Active Status");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.listValdidation("LeadSourceStatus");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Active"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("Active"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("BusinessNameList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("BusinessNameList.png");
		}
	}

	@Test(priority = 10)
	private void editFormLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Lead Source Page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.labelValidation("LeadSourceEdit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditLeadSourcePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("EditLeadSourcePage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearField("LeadSource");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearField("LeadSource");
		}
	}

	@Test(priority = 11)
	private void editmandatoryValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Lead Source Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		initElements.leadSourceField("Mandatory");
		String editContact = initElements.errorField("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearField("LeadSource");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearField("LeadSource");
		}
	}

	@Test(priority = 12)
	private void editmaximumValidationLeadSource() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Lead Source Name Field exceed its max-256 limit");
		BusinessDaysPage mandatory = PageFactory.initElements(driver, BusinessDaysPage.class);
		mandatory.leadSourceField("MaxValidation");
		String errorPasswordField = mandatory.errorField("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("LeadSource");
			ListField = mandatory.leadSourceField("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("LeadSource");
			ListField = mandatory.leadSourceField("ValidData");
		}

	}

	@Test(priority = 13)
	public void verifyUpdateButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Edit Lead Source Update Button is displayed in the Edit form page");
		BusinessDaysPage contractorPage = PageFactory.initElements(driver, BusinessDaysPage.class);
		String text_button = contractorPage.buttonValidation("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("UpdatedButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
			contractorPage.leadSourceField("ValidData");
			contractorPage.buttonValidation("ButtonClick");
			contractorPage.listValdidation("LeadSource");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
			contractorPage.leadSourceField("ValidData");
			contractorPage.buttonValidation("ButtonClick");
			contractorPage.listValdidation("LeadSource");
		}

	}

	@Test(priority = 15)
	public void alreadyExists() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify [Lead Source Name Exists] Lead Source form, Error is dispalyed when already existing Lead Source is provided");
		BusinessDaysPage contractorPage = PageFactory.initElements(driver, BusinessDaysPage.class);
		contractorPage.leadSourceField("UniqueValidation");
		String text_button = contractorPage.errorField("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceAlreadyExists"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("LeadSourceAlreadyExists"))) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
			contractorPage.buttonValidation("CancelButton");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
			contractorPage.buttonValidation("CancelButton");
		}

	}

	@Test(priority = 14)
	private void leadSourceUpdate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify updated successful message is displayed, when the Lead source Updated");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("LeadSourceUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.listValdidation("LeadSource");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.message("AlternateFunction");
			landing.listValdidation("LeadSource");
		}
	}

	@Test(priority = 16)
	private void deleteBusinessUnit() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify deleted successful message is displayed, when the Lead Source Deleted");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		landing.listValdidation("LeadSourceDelete");
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("LeadSourceDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("LeadSourceDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.createBusinessDays("CreatedLeadSource");
			System.out.println(ListField + "Iam Deleted , Create") ;
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.createBusinessDays("CreatedLeadSource");
		}
	}

	@Test(priority = 17)
	private void reflectCreateContactPage() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the newly created Lead Source Name is:" + ListField
				+ " & it's reflect the Lead Source field in the Customer Contact Page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		landing.customerClick();
		String createMessage = landing.bussinessDays("LeadSource");
		System.out.println(createMessage + "Iam Relected in Contact Create page.");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.createBusinessDays("EditLeadSource");
			System.out.println(ListField);
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.createBusinessDays("EditLeadSource");
		}
	}

	@Test(priority = 18)
	private void inactiveLeadSource() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Inactive Lead Source Name is:" + ListField
				+ " & it's not reflect the Lead Source field in the Customer Contact Page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
	//	landing.contactClick();
		landing.customerClick();
		String createMessage = landing.bussinessDays("LeadSource");
		System.out.println(createMessage + "Inactive");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + "No Data Found For " + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals("No Data Found For " + ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.createBusinessDays("DeleteLeadSource");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.createBusinessDays("DeleteLeadSource");
		}
	}

	@Test(priority = 19)
	private void deleteLeadSource() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Delete Lead Source Name is:" + ListField
				+ " & it's not reflect the Lead Source field in the Customer Contact Page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		//landing.contactClick();
		landing.customerClick();
		String createMessage = landing.bussinessDays("LeadSource");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + "No Data Found For " + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals("No Data Found For " + ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
		}
	}

}
