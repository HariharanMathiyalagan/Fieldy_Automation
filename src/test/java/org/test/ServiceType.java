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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.BusinessDaysPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class ServiceType extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String ListField;

	@BeforeClass
	public void setup() {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("ServiceType.html");
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
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password"));
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
				.createTest("Verify the Service Type Label is displayed in the Business Settings page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.businessDaysLabel("ServiceType");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ServiceTypeModule"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("ServiceTypeModule"))) {
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
		extentTest = extentReports.createTest("Verify the User to Land on the Create Service Page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.labelValidation("ServiceTypeCreate");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreateServiceTypePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CreateServiceTypePage"))) {
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

	@Test(priority = 5)
	private void mandatoryValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Lead Source Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		initElements.serviceTypeField("Mandatory");
		String editContact = initElements.errorField("ServiceType");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearField("ServiceType");
			ListField = initElements.serviceTypeField("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearField("ServiceType");
			ListField = initElements.serviceTypeField("ValidData");
		}
	}

	@Test(priority = 4)
	private void maximumValidationServiceType() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Service Type Name Field exceed its max-256 limit");
		BusinessDaysPage mandatory = PageFactory.initElements(driver, BusinessDaysPage.class);
		mandatory.serviceTypeField("MaxValidation");
		String errorPasswordField = mandatory.errorField("ServiceType");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("ServiceType");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("ServiceType");
		}

	}

	@Test(priority = 6)
	public void verifySaveButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Create Service Type Save & Complete Button is displayed in the Create form page");
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
	private void serviceTypeCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify a new Service Type is created successfully through [Business Settings]");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ServiceTypeCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("ServiceTypeCreatedMessage"))) {
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
	private void serviceTypeCreateList() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify a newly created Service Type Name:" + ListField + " is displayed in the Service Type List page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.listValdidation("ServiceType");
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
		extentTest = extentReports.createTest("Verify a newly created Service Type is in the Active Status");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.listValdidation("ServiceTypeStatus");
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
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Service Type Page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.labelValidation("ServiceTypeEdit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditServiceTypePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("EditServiceTypePage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearField("ServiceType");
			initElements.clearField("ServiceType");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearField("ServiceType");
			initElements.clearField("ServiceType");
		}
	}

	@Test(priority = 11)
	private void editmandatoryValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Service Type Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		initElements.serviceTypeField("Mandatory");
		String editContact = initElements.errorField("ServiceType");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearField("ServiceType");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearField("ServiceType");
		}
	}

	@Test(priority = 12)
	private void editmaximumValidationLeadSource() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Service Type Name Field exceed its max-256 limit");
		BusinessDaysPage mandatory = PageFactory.initElements(driver, BusinessDaysPage.class);
		mandatory.serviceTypeField("MaxValidation");
		String errorPasswordField = mandatory.errorField("ServiceType");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("ServiceType");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("ServiceType");
		}

	}

	@Test(priority = 13)
	public void verifyUpdateButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Edit Service Type Update Button is displayed in the Edit form page");
		BusinessDaysPage contractorPage = PageFactory.initElements(driver, BusinessDaysPage.class);
		String text_button = contractorPage.buttonValidation("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("UpdatedButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
			contractorPage.serviceTypeField("ValidData");
			contractorPage.buttonValidation("ButtonClick");
			contractorPage.listValdidation("ServiceType");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
			contractorPage.serviceTypeField("ValidData");
			contractorPage.buttonValidation("ButtonClick");
			contractorPage.listValdidation("ServiceType");
		}

	}

//	@Test(priority = 15)
	public void alreadyExists() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify [Service Type Exists] Service Type form, Error is dispalyed when already existing Service Type is provided");
		BusinessDaysPage contractorPage = PageFactory.initElements(driver, BusinessDaysPage.class);
		contractorPage.serviceTypeField("UniqueValidation");
		String text_button = contractorPage.errorField("ServiceType");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ServiceTypeAlreadyExists"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("ServiceTypeAlreadyExists"))) {
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
				.createTest("Verify updated successful message is displayed, when the Service type Updated");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ServiceTypeUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("ServiceTypeUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.listValdidation("ServiceType");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.message("AlternateFunction");
			landing.listValdidation("ServiceType");
		}
	}

	@Test(priority = 16)
	private void deleteBusinessUnit() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify deleted successful message is displayed, when the Service Type Deleted");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		landing.listValdidation("ServiceTypeDelete");
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ServiceTypeDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("ServiceTypeDeletedMessage"))) {
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
