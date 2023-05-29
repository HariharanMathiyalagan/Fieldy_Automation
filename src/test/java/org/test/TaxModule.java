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
import com.zaigo.pageobjects.ProductServicePage;
import com.zaigo.pageobjects.TaxPage;
import com.zaigo.utility.BrowserSetup;

public class TaxModule extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String ListField;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("TaxModule.html");
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
		extentTest = extentReports.createTest("Navigate to Tax Settings page");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		String editContact = initElements.modulePage("ListPage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ListTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("ListTax"))) {
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
	private void createFormLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Tax Page");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		String editContact = initElements.modulePage("CreatePage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreateTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CreateTax"))) {
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
	private void mandatoryValidationTaxName() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Tax Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		initElements.taxNameField("Mandatory");
		String editContact = initElements.errorField("TaxName");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ProductErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ProductErrorMandatoryError.png");
		}
	}

	@Test(priority = 4)
	private void mandatoryValidationDescription() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Tax Rate field is set as Mandatory & Error Message is displayed when it is BLANK");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		String editContact = initElements.errorField("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DescriptionErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DescriptionErrorMandatoryError.png");
		}
	}

	@Test(priority = 5)
	private void maximumValidationProductName() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tax Name Field exceed its max-190 limit");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.taxNameField("MaxValidation");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max190CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max190CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("TaxName");
			mandatory.clearField("TaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("TaxName");
			mandatory.clearField("TaxPercentage");
		}

	}

	@Test(priority = 6)
	private void maximumValidationTaxPercentage() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Rate (%) Field exceed its max-1 to 99.99 limit");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		listValidation = mandatory.texPercentageField("MaxValidation");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MaxTaxPercentage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MaxTaxPercentage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("TaxPercentage");
			mandatory.texPercentageField("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("TaxPercentage");
			mandatory.texPercentageField("ValidData");
		}
	}

	@Test(priority = 6)
	private void subTaxNameUnique() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed, when Associate Tax Name is same as the Parent Tax Name");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.taxNameField("AssociateTaxUnique");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AssociateTaxNameUnique"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AssociateTaxNameUnique"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("SubTaxName");
			mandatory.clearField("SubTaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("SubTaxName");
			mandatory.clearField("SubTaxPercentage");
		}
	}

	@Test(priority = 6)
	private void subTaxPercentageEqual() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed, when Associate Tax Percentage should be equal to Parent Tax Percentage");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.taxNameField("TaxPercentage");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AssociateTaxValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AssociateTaxValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("SubTaxPercentage");
			mandatory.taxNameField("SubTaxValue");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("SubTaxPercentage");
			mandatory.taxNameField("SubTaxValue");
		}
	}

	@Test(priority = 7)
	private void createButton() throws IOException, AWTException {
		extentTest = extentReports
				.createTest("Verify the Create Tax page Save & Compelete Button is displayed in the Create form page");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		String errorPasswordField = mandatory.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SaveButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("SaveButton"))) {
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

	static String listValidation;

	@Test(priority = 8)
	private void createTax() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify created successful message is displayed, when the Tax Created");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.clickEvent("ClickButton");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatedTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CreatedTax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			mandatory.message("AlternateMessage");
		}
	}

	@Test(priority = 9)
	private void createdTaxList() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Create Tax Name :" + listValidation + " is listed in the Setting page");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		String errorPasswordField = mandatory.listValidation();
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
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

	@Test(priority = 9)
	private void uniqueValidationTaxName() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify [Tax Name Already Exists] Error is dispalyed when already existing tax name is provided");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.taxNameField("UniqueValidation");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AlreadyTax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clickEvent("BackButton");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			mandatory.clickEvent("BackButton");
		}
	}

	@Test(priority = 10)
	private void editFormLabel() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Tax Page");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		String editContact = initElements.modulePage("EditPage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("EditTax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearAllFields("SubTax");
			initElements.clickEvent("CheckBox");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearAllFields("SubTax");
			initElements.clickEvent("CheckBox");
		}
	}

	@Test(priority = 11)
	private void editmandatoryValidationTaxName() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Tax Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		initElements.clickEvent("ClickButton");
		String editContact = initElements.errorField("TaxName");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ProductErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ProductErrorMandatoryError.png");
		}
	}

	@Test(priority = 12)
	private void editmandatoryValidationDescription() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		TaxPage initElements = PageFactory.initElements(driver, TaxPage.class);
		String editContact = initElements.errorField("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DescriptionErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DescriptionErrorMandatoryError.png");
		}
	}

	@Test(priority = 13)
	private void editmaximumValidationProductName() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tax Name Field exceed its max-190 limit");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.taxNameField("MaxValidation");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max190CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max190CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("TaxName");
			mandatory.clearField("TaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("TaxName");
			mandatory.clearField("TaxPercentage");
		}

	}

	@Test(priority = 14)
	private void editmaximumValidationTaxPercentage() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Rate (%) Field exceed its max-1 to 99.99 limit");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		listValidation = mandatory.texPercentageField("MaxValidation");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MaxTaxPercentage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("MaxTaxPercentage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("TaxPercentage");
			mandatory.texPercentageField("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("TaxPercentage");
			mandatory.texPercentageField("ValidData");
		}
	}

	@Test(priority = 15)
	private void editsubTaxNameUnique() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed, when Associate Tax Name is same as the Parent Tax Name");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.taxNameField("AssociateTaxUnique");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AssociateTaxNameUnique"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AssociateTaxNameUnique"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("SubTaxName");
			mandatory.clearField("SubTaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("SubTaxName");
			mandatory.clearField("SubTaxPercentage");
		}
	}

	@Test(priority = 16)
	private void editsubTaxPercentageEqual() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed, when Associate Tax Percentage should be equal to Parent Tax Percentage");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.taxNameField("TaxPercentage");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AssociateTaxValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AssociateTaxValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearField("SubTaxPercentage");
			mandatory.taxNameField("SubTaxValue");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearField("SubTaxPercentage");
			mandatory.taxNameField("SubTaxValue");
		}
	}

	@Test(priority = 17)
	private void updateButton() throws IOException, AWTException {
		extentTest = extentReports
				.createTest("Verify the Edit Tax page Update Button is displayed in the Edit form page");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		String errorPasswordField = mandatory.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("UpdatedButton"))) {
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

	@Test(priority = 18)
	private void updatedTax() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify updated successful message is displayed, when the Tax Updated");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.clickEvent("ClickButton");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditedTax"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("EditedTax"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			mandatory.message("AlternateMessage");
		}
	}

	@Test(priority = 19)
	private void deleteTax() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify deleted successful message is displayed, when the Tax deleted");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		mandatory.clickEvent("Delete");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TaxDelete"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TaxDelete"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			mandatory.message("AlternateMessage");
		}
	}

	@Test(priority = 20)
	private void updatedTaxList() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Updated Tax Name :" + listValidation + " is listed in the Setting page");
		TaxPage mandatory = PageFactory.initElements(driver, TaxPage.class);
		String errorPasswordField = mandatory.listValidation();
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(errorPasswordField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = mandatory.createEdit("Create");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			ListField = mandatory.createEdit("Create");
		}
	}

	@Test(priority = 21)
	private void createdTaxRefectProductService() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the newly created Tax Name is:" + ListField
				+ " & it's reflect the Tax field in the Product & Service Create Page");
		TaxPage landing = PageFactory.initElements(driver, TaxPage.class);
		String createMessage = landing.reflection();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.createEdit("Edit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.createEdit("Edit");
		}
	}

	@Test(priority = 22)
	private void editTaxRefectProductService() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Edited Tax Name is:" + ListField
				+ " & it's reflect the Tax field in the Product & Service Create Page");
		TaxPage landing = PageFactory.initElements(driver, TaxPage.class);
		String createMessage = landing.reflection();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = landing.createEdit("Delete");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			ListField = landing.createEdit("Delete");
		}
	}

//	@Test(priority = 23)
	private void deleteTaxRefectProductService() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Deleted Tax Name is:" + ListField
				+ " & it's reflect the Tax field in the Product & Service Create Page");
		TaxPage landing = PageFactory.initElements(driver, TaxPage.class);
		String createMessage = landing.reflection();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
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
