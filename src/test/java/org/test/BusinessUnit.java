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

public class BusinessUnit extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String ListField;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("BusinessUnit.html");
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
	private void businessUnitLabel() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Business Unit Label is displayed in the Business Settings page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.businessDaysLabel("BusinessUnit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("BusinessUnitModule"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("BusinessUnitModule"))) {
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
		extentTest = extentReports.createTest("Verify the User to Land on the Create Business Unit Page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.labelValidation("BusinessUnitCreate");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreateBusinessPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CreateBusinessPage"))) {
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
				"Verify Business Unit Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		initElements.businessUnitField("Mandatory");
		String editContact = initElements.errorField("BusinessUnit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearField("BussinessUnit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearField("BussinessUnit");
			
		}
	}

	@Test(priority = 5)
	private void maximumValidationBussinessUnit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Business Unit Name Field exceed its max-256 limit");
		BusinessDaysPage mandatory = PageFactory.initElements(driver, BusinessDaysPage.class);
		mandatory.businessUnitField("MaxValidation");
		String errorPasswordField = mandatory.errorField("BusinessUnit");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("BussinessUnit");
			ListField = mandatory.businessUnitField("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("BussinessUnit");
			ListField = mandatory.businessUnitField("ValidData");
		}

	}

	@Test(priority = 6)
	public void verifySaveButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Create Business Unit Save & Complete Button is displayed in the Create form page");
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
	private void businessCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify a new business unit is created successfully through [Business Settings]");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("BusinessUnitCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("BusinessUnitCreatedMessage"))) {
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
	private void businessCreateList() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify a newly created Business Unit Name:" + ListField
				+ "is displayed in the Business Unit List page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.listValdidation("BusinessUnit");
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
		extentTest = extentReports.createTest("Verify a newly created Business Unit is in the Active Status");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.listValdidation("BusinessUnitStatus");
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
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Business Unit Page");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		String editContact = initElements.labelValidation("BusinessUnitEdit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditBusinessPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("EditBusinessPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			for (int i = 0; i < 5; i++) {
				initElements.clearField("BussinessUnit");
			}
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			for (int i = 0; i < 5; i++) {
				initElements.clearField("BussinessUnit");
			}
		}
	}

	@Test(priority = 11)
	private void editmandatoryValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Business Unit Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		BusinessDaysPage initElements = PageFactory.initElements(driver, BusinessDaysPage.class);
		initElements.businessUnitField("Mandatory");
		String editContact = initElements.errorField("BusinessUnit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearField("BussinessUnit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearField("BussinessUnit");
		}
	}

	@Test(priority = 12)
	private void editmaximumValidationBussinessUnit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Business Unit Name Field exceed its max-256 limit");
		BusinessDaysPage mandatory = PageFactory.initElements(driver, BusinessDaysPage.class);
		mandatory.businessUnitField("MaxValidation");
		String errorPasswordField = mandatory.errorField("BusinessUnit");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("BussinessUnit");
			ListField = mandatory.businessUnitField("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("BussinessUnit");
			ListField = mandatory.businessUnitField("ValidData");
		}

	}

	@Test(priority = 13)
	public void verifyUpdateButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Edit Business Unit Update Button is displayed in the Edit form page");
		BusinessDaysPage contractorPage = PageFactory.initElements(driver, BusinessDaysPage.class);
		String text_button = contractorPage.buttonValidation("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("UpdatedButton"))) {
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

	@Test(priority = 14)
	private void businessUpdate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify a new business unit is created successfully through [Business Settings]");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("BusinessUnitCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("BusinessUnitCreatedMessage"))) {
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

	@Test(priority = 15)
	private void deleteBusinessUnit() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify a new business unit is created successfully through [Business Settings]");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		landing.listValdidation("BusinessUnitDelete");
		String createMessage = landing.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("BusinessUnitDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("BusinessUnitDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.createBusinessDays("CreatedBusinessUnit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.createBusinessDays("CreatedBusinessUnit");
		}
	}

	@Test(priority = 17)
	private void reflectCreateContactPage() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the newly created Business Unit Name is:" + ListField
				+ " & it's reflect the Lead Source field in the Customer Contact Page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.bussinessDays("BussinessUnit");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.createBusinessDays("EditBusinessUnit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.createBusinessDays("EditBusinessUnit");
		}
	}

	@Test(priority = 18)
	private void inactiveLeadSource() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Inactive Business Unit Name is:" + ListField
				+ " & it's not reflect the Business Unit field in the Customer Contact Page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.bussinessDays("BussinessUnit");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + "No Data Found For " + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals("No Data Found For " + ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.createBusinessDays("DeleteBusinessUnit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.createBusinessDays("DeleteBusinessUnit");
		}
	}

	@Test(priority = 19)
	private void deleteLeadSource() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Delete Business Unit Name is:" + ListField
				+ " & it's not reflect the Business Unit field in the Customer Contact Page");
		BusinessDaysPage landing = PageFactory.initElements(driver, BusinessDaysPage.class);
		String createMessage = landing.bussinessDays("BussinessUnit");
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
