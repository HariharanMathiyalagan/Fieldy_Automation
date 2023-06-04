package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

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
import com.zaigo.pageobjects.InvoicePage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class GlobalOrganizationInvoice extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String customerContactName;
	static String currentDate;
	static String ListField;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("GlobalOrganizationInvoice.html");
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

	@Test(priority = -1)
	private void invoiceModule() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Customer Contact List Page is opened when clicking on Cusotmer->Contact");
		InvoicePage initElements = PageFactory.initElements(driver, InvoicePage.class);
		initElements.labelValidation("GlobalContactInvoice");
		initElements.labelValidation("OrganizationAPI");

	}

	@Test(priority = 0)
	private void contactMandatoryValidation() throws WebDriverException, IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Mandatory Validation in Contact Field");
		InvoicePage initElements = PageFactory.initElements(driver, InvoicePage.class);
		initElements.clearFields("DueDate");
		initElements.saveFunction("Mandatory");
//		String errorContact = initElements.errorValidation("ErrorContact");
		extentTest.log(Status.INFO, "Actual Result is -" + "null");
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if ("null".equals(getPropertyValue("MandatoryErrorMessage"))) {
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

	@Test(priority = 1)
	private void mandatoryValidationDeuDate() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Expiry Date field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorDueDate");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceExpiryDateMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceExpiryDateMandatory.png");
		}
	}

	@Test(priority = 2)
	private void mandatoryValidationInventoryItem() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Inventory Item field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorInventoryItem");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceInventoryItemMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceInventoryItemMandatory.png");
		}
	}

	@Test(priority = 3)
	private void mandatoryValidationQunatity() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Quantity field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityMandatory.png");
		}
	}

	@Test(priority = 4)
	private void mandatoryValidationPrice() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoicePriceMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoicePriceMandatory.png");
		}
	}

	@Test(priority = 5)
	private void mandatoryValidationDescription() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorDescription");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactDescriptionMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactDescriptionMandatory.png");
		}
	}

	@Test(priority = 6)
	private void autoCompleteContactCreation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Contact Creation in the Autocomplete field");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.validationQuantity("Value");
		mandatory.priceValidation("value");
		mandatory.autoCompleteField("GlobalOrganization");
		String errorContact = mandatory.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContact.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.autoCompleteField("VisibleCustomerOrgName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("AutocompleteContactCreate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("AutocompleteContactCreate.png");
			mandatory.responseMessage("AlternateFunction");
			mandatory.autoCompleteField("VisibleCustomerOrgName");
		}

	}

	@Test(priority = 7)
	private void autoCompleteOrganizationContactCreation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Organization Contact Creation in the Autocomplete field");
		InvoicePage contactMandatory = PageFactory.initElements(driver, InvoicePage.class);
		contactMandatory.autoCompleteField("OrganizationContactCreate");
		String errorContact = contactMandatory.responseMessage("Message");
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
			contactMandatory.responseMessage("AlternateFunction");
			contactMandatory.autoCompleteField("VisibleName");
		}

	}

	@Test(priority = 8)
	private void maximumValidationReference() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Reference Field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxValidationReference();
		String errorPasswordField = mandatory.errorValidation("ErrorReference");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max16CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max16CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Reference");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("Reference");
		}

	}

	@Test(priority = 9)
	private void maximumValidationInvoiceTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Invoice Tittle Field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxValidationInvoiceTittle();
		String errorPasswordField = mandatory.errorValidation("ErrorInvoiceTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("InvoiceTittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceTittleMaximumValidation.png");
			mandatory.clearFields("InvoiceTittle");
		}

	}

	@Test(priority = 10)
	private void maximumValidationInventoryItem() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Inventory Item Field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxInventoryItem();
		String errorPasswordField = mandatory.errorValidation("ErrorInventoryItem");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Inventory");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceInventoryMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceInventoryMaximumValidation.png");
			mandatory.clearFields("Inventory");
		}

	}

	@Test(priority = 11)
	private void emptyValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity Field is enter the negative value");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.clearFields("Quantity");
		mandatory.validationQuantity("EmptyValidation");
		String errorPasswordField = mandatory.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EmptyField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("EmptyField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Quantity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityEmptyValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityEmptyValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 12)
	private void maxQuanitityLimit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity Field exceed its max-12 Limts");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.validationQuantity("MaxQuantity");
		mandatory.priceValidation("value");
		String errorPasswordField = mandatory.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max12CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max12CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Quantity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityMaximumValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 13)
	private void afterDecimalPointValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-2 after decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.validationQuantity("AfterDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Quantity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityDeciamlPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityDeciamlPointValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 14)
	private void beforeDecimalPointValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-12 before decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.validationQuantity("BeforeDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuantityBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("QuantityBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Quantity");
			mandatory.validationQuantity("Value");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityBeforeDeciamlPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityBeforeDeciamlPointValidation.png");
			mandatory.clearFields("Quantity");
			mandatory.validationQuantity("Value");
		}

	}

	@Test(priority = 15)
	private void emptyValidationPriceField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field is enter the negative value");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.clearFields("Price");
		mandatory.priceValidation("EmptyValidation");
		String errorPasswordField = mandatory.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PriceEmptyField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("PriceEmptyField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoicePriceNegativeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoicePriceNegativeValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 16)
	private void maxPriceLimit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field exceed its max-6 Limts");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.priceValidation("MaxPrice");
		String errorPasswordField = mandatory.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max6CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max6CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoicePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoicePriceMaximumValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 17)
	private void beforeDecimalPointPriceField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-6 before decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.priceValidation("BeforeDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("BeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("BeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 18)
	private void afterDecimalPointPriceField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-2 after decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.priceValidation("AfterDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
			mandatory.priceValidation("value");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("Price");
			mandatory.priceValidation("value");
		}

	}

	@Test(priority = 19)
	private void afterDecimalPointDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Discout Field exceed its max-2 after decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.discountValidation("AfterDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorDiscount");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Discount");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterDecimalPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterDecimalPointValidation.png");
			mandatory.clearFields("Discount");
		}

	}

	@Test(priority = 20)
	private void beforeDecimalPointDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Discount Field exceed its max-3 before decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.discountValidation("BeforeDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorDiscount");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DisTaxBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DisTaxBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Discount");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforeDiscountDecimalValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforeDiscountDecimalValidation.png");
			mandatory.clearFields("Discount");
		}

	}

	@Test(priority = 21)
	private void maxLimitDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Discount Field exceed its max-100 Limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.discountValidation("MaxDiscount");
		String errorPasswordField = mandatory.errorValidation("ErrorDiscount");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DiscountLimit"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DiscountLimit"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Discount");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceDiscoutnMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceDiscoutnMaximumValidation.png");
			mandatory.clearFields("Discount");
			mandatory.discountValidation("value");
		}

	}

	@Test(priority = 22)
	private void afterDecimalPointTaxField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Field exceed its max-2 after decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.taxValidation("AfterDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorTax");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tax");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterDecimalPointTaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterDecimalPointTaxValidation.png");
			mandatory.clearFields("Tax");
		}

	}

	@Test(priority = 23)
	private void beforeDecimalPointTaxField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Field exceed its max-3 before decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.taxValidation("BeforeDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorTax");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DisTaxBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DisTaxBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tax");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforeTaxDecimalValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforeTaxDecimalValidation.png");
			mandatory.clearFields("Tax");
		}

	}

	@Test(priority = 24)
	private void maxLimitTaxField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tax Field exceed its max-100 Limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.taxValidation("MaxTax");
		String errorPasswordField = mandatory.errorValidation("ErrorTax");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TaxLimit"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TaxLimit"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tax");
			mandatory.taxValidation("value");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceTaxMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceTaxMaximumValidation.png");
			mandatory.clearFields("Tax");
			mandatory.taxValidation("value");
		}

	}

	@Test(priority = 25)
	private void maximumValidationDescription() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.descriptionValidation("MaxCharacter");
		String errorPasswordField = mandatory.errorValidation("ErrorDescription");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
			mandatory.descriptionValidation("value");
			mandatory.pickFirstItem("Contact");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
			mandatory.descriptionValidation("value");
			mandatory.pickFirstItem("Contact");
		}

	}

	@Test(priority = 26)
	private void maximumValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Note field exceed its max-20000 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxNotes();
		String errorPasswordField = mandatory.errorValidation("ErrorNotes");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20000Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max20000Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Notes");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceNotesMaximumValidation.png");
			mandatory.clearFields("Notes");
		}

	}

	@Test(priority = 27)
	private void expiryFieldPastDateValidation() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Expiry field enter the past date");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.dateValidation("PastDate");
		currentDate = mandatory.dateValidation("CurrentDate");
		String errorPasswordField = mandatory.responseMessage("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO,
				"Expected Result is -" + "The doc expiry date must be a date after or equal to " + currentDate + ".");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals("The doc expiry date must be a date after or equal to " + currentDate + ".")) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Expiry");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoicePastDateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoicePastDateValidation.png");
			mandatory.clearFields("Expiry");
		}

	}

	@Test(priority = 28)
	private void validateCalculation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify to check the Line item calculation");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		String amount = mandatory.inventoryItemValidation("Calculation");
		String inventoryItemValidation = mandatory.inventoryItemValidation("ExpectedAmount");
		extentTest.log(Status.INFO, "Actual Result is -" + amount);
		extentTest.log(Status.INFO, "Expected Result is -" + inventoryItemValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (amount.equals(inventoryItemValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Expiry");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteAmountValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteAmountValidation.png");
			mandatory.clearFields("Expiry");
		}
	}

	@Test(priority = 29)
	private void createInvoice() throws IOException, InterruptedException, ParseException, AWTException {
		extentTest = extentReports
				.createTest("Verify Invoice is created successfully from Customer Contact->Create Invoice");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.attachmentFileCheck("URLCheck");
		mandatory.CRUDValidation("Create");
		String errorPasswordField = mandatory.responseMessage("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvoiceCreateMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("InvoiceCreateMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteCreation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteCreation.png");
		}
	}

	@Test(priority = 31)
	private void editLableValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Invoice Page");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		String invocieLandPage = create.invocieLandPage("EditLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + invocieLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Edit"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (invocieLandPage.equals(getPropertyValue("Edit"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InvoiceEditLabelValidate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InvoiceEditLabelValidate.png");
		}

	}

	@Test(priority = 32)
	private void mandatoryValidationEditDueDate() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Expiry Date field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		mandatoryValidation.clearAllFields();
		mandatoryValidation.saveFunction("ClickButton");
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorDueDate");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceExpiryDateMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceExpiryDateMandatory.png");
		}
	}

//	@Test(priority = 33)
	private void mandatoryValidationEditInventoryItem() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Inventory Item field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorInventoryItem");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceInventoryItemMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceInventoryItemMandatory.png");
		}
	}

	@Test(priority = 34)
	private void mandatoryValidationEditQunatity() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Quantity field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityMandatory.png");
		}
	}

	@Test(priority = 35)
	private void mandatoryValidationEditPrice() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoicePriceMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoicePriceMandatory.png");
		}
	}

	@Test(priority = 36)
	private void mandatoryValidationEditDescription() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorDescription");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactDescriptionMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactDescriptionMandatory.png");
		}
	}

	@Test(priority = 37)
	private void maximumValidationEditReference() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Reference Field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.pickFirstItem("Contact");
		mandatory.validationQuantity("Value");
		mandatory.maxValidationReference();
		String errorPasswordField = mandatory.errorValidation("ErrorReference");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max16CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max16CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Reference");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("Reference");
		}

	}

	@Test(priority = 38)
	private void maximumValidationEditInvoiceTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Invoice Tittle Field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxValidationInvoiceTittle();
		String errorPasswordField = mandatory.errorValidation("ErrorInvoiceTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("InvoiceTittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceTittleMaximumValidation.png");
			mandatory.clearFields("InvoiceTittle");
		}

	}

	@Test(priority = 39)
	private void maximumValidationEditInventoryItem() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Inventory Item Field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxInventoryItem();
		String errorPasswordField = mandatory.errorValidation("ErrorInventoryItem");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Inventory");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceInventoryMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceInventoryMaximumValidation.png");
			mandatory.clearFields("Inventory");
		}

	}

	@Test(priority = 40)
	private void emptyValidationEditQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity Field is enter the negative value");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.clearFields("Quantity");
		mandatory.validationQuantity("EmptyValidation");
		String errorPasswordField = mandatory.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EmptyField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("EmptyField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Quantity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityEmptyValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityEmptyValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 41)
	private void maxEditQuanitityLimit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity Field exceed its max-12 Limts");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.validationQuantity("MaxQuantity");
		mandatory.priceValidation("value");
		String errorPasswordField = mandatory.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max12CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max12CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Quantity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityMaximumValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 42)
	private void afterEditDecimalPointValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-2 after decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.validationQuantity("AfterDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Quantity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityDeciamlPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityDeciamlPointValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 43)
	private void beforeEditDecimalPointValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-12 before decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.validationQuantity("BeforeDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorQuantity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuantityBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("QuantityBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Quantity");
			mandatory.validationQuantity("Value");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceQuantityBeforeDeciamlPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceQuantityBeforeDeciamlPointValidation.png");
			mandatory.clearFields("Quantity");
			mandatory.validationQuantity("Value");
		}

	}

	@Test(priority = 44)
	private void emptyValidationEditPriceField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field is enter the negative value");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.clearFields("Price");
		mandatory.priceValidation("EmptyValidation");
		String errorPasswordField = mandatory.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PriceEmptyField"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("PriceEmptyField"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoicePriceNegativeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoicePriceNegativeValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 45)
	private void maxEditPriceLimit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field exceed its max-6 Limts");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.priceValidation("MaxPrice");
		String errorPasswordField = mandatory.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max6CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max6CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoicePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoicePriceMaximumValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 46)
	private void beforeEditDecimalPointPriceField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-6 before decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.priceValidation("BeforeDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("BeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("BeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 47)
	private void afterEditDecimalPointPriceField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-2 after decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.priceValidation("AfterDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorPrice");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
			mandatory.priceValidation("value");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("Price");
			mandatory.priceValidation("value");
		}

	}

	@Test(priority = 48)
	private void afterEditDecimalPointDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Discout Field exceed its max-2 after decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.discountValidation("AfterDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorDiscount");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Discount");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterDecimalPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterDecimalPointValidation.png");
			mandatory.clearFields("Discount");
		}

	}

	@Test(priority = 49)
	private void beforeEditDecimalPointDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Discount Field exceed its max-3 before decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.discountValidation("BeforeDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorDiscount");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DisTaxBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DisTaxBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Discount");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforeDiscountDecimalValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforeDiscountDecimalValidation.png");
			mandatory.clearFields("Discount");
		}

	}

	@Test(priority = 50)
	private void maxLimitEditDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Discount Field exceed its max-100 Limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.discountValidation("MaxDiscount");
		String errorPasswordField = mandatory.errorValidation("ErrorDiscount");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DiscountLimit"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DiscountLimit"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Discount");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceDiscoutnMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceDiscoutnMaximumValidation.png");
			mandatory.clearFields("Discount");
			mandatory.discountValidation("value");
		}

	}

	@Test(priority = 51)
	private void afterDecimalEditPointTaxField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Field exceed its max-2 after decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.taxValidation("AfterDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorTax");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tax");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterDecimalPointTaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterDecimalPointTaxValidation.png");
			mandatory.clearFields("Tax");
		}

	}

	@Test(priority = 52)
	private void beforeDecimalEditPointTaxField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Field exceed its max-3 before decimal point limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.taxValidation("BeforeDecimalPoint");
		String errorPasswordField = mandatory.errorValidation("ErrorTax");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DisTaxBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DisTaxBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tax");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforeTaxDecimalValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforeTaxDecimalValidation.png");
			mandatory.clearFields("Tax");
		}

	}

	@Test(priority = 53)
	private void maxEditLimitTaxField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tax Field exceed its max-100 Limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.taxValidation("MaxTax");
		String errorPasswordField = mandatory.errorValidation("ErrorTax");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TaxLimit"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("TaxLimit"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Tax");
			mandatory.taxValidation("value");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceTaxMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceTaxMaximumValidation.png");
			mandatory.clearFields("Tax");
			mandatory.taxValidation("value");
		}

	}

	@Test(priority = 54)
	private void maximumEditValidationDescription() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.descriptionValidation("MaxCharacter");
		String errorPasswordField = mandatory.errorValidation("ErrorDescription");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
			mandatory.descriptionValidation("value");
			mandatory.pickFirstItem("Contact");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
			mandatory.descriptionValidation("value");
			mandatory.pickFirstItem("Contact");
		}

	}

	@Test(priority = 55)
	private void maximumEditValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Note field exceed its max-20000 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxNotes();
		String errorPasswordField = mandatory.errorValidation("ErrorNotes");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20000Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max20000Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Notes");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceNotesMaximumValidation.png");
			mandatory.clearFields("Notes");
		}

	}

	@Test(priority = 56)
	private void expiryEditFieldPastDateValidation() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Expiry field enter the past date");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.dateValidation("PastDate");
		currentDate = mandatory.dateValidation("CurrentDate");
		String errorPasswordField = mandatory.responseMessage("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO,
				"Expected Result is -" + "The doc expiry date must be a date after or equal to " + currentDate + ".");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals("The doc expiry date must be a date after or equal to " + currentDate + ".")) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Expiry");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoicePastDateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoicePastDateValidation.png");
			mandatory.clearFields("Expiry");
		}

	}

	@Test(priority = 57)
	private void checkResponseCode() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Attacthment response code in global organization invoice module");
		InvoicePage initElements = PageFactory.initElements(driver, InvoicePage.class);
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

	@Test(priority = 57)
	private void updatedInvoice() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports
				.createTest("Verify Invoice is updated successfully from Customer Contact->Update Invoice");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.CRUDValidation("Edit");
		mandatory.inventoryItemValidation("Calculation");
		mandatory.saveFunction("ClickButton");
		String errorPasswordField = mandatory.responseMessage("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvoiceUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("InvoiceUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceUpdated.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceUpdated.png");
		}
	}

	@Test(priority = 58)
	private void draftInvoice() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports.createTest("Verify the Invoice has been draft status");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.CRUDValidation("CilckCreateInvoice");
		create.CRUDValidation("ClickOrgRadio");
		create.CRUDValidation("GlobalOrgDraft");
		String responseMessage = create.listTextValidation("ListDraftStatus");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Draft"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("Draft"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceDraftListStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceDraftListStatus.png");
		}

	}

	@Test(priority = 59)
	private void createdQuoteStatus() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify to Created Invoice Status is Awaiting Payment, It's is displayed in the Invoice List Page");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		String responseMessage = create.listTextValidation("ListAwaitingStatus");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AwaitingPayment"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("AwaitingPayment"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListStatus.png");
		}

	}

//	static String ListData;
	static double ListData;

	@Test(priority = 60)
	private void UpdatedInvociePartialPayment() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Invoice has been Partial Payment status");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.listTextValidation("TotalValue");
		create.listTextValidation("PartialPayment");
		create.listTextValidation("PayAmount");
		ListData = create.doubleValue(1);
		String responseMessage = create.listTextValidation("ListPartialStatus");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("PartialPayment"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("PartialPayment"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvociePartialPaymentStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvociePartialPaymentStatus.png");
		}

	}

	@Test(priority = 61)
	private void partialPaidAmount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Check the Partial Paid Amount:" + ListData + " in the Invoice List Page");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		double responseMessage = create.doubleValue(1);
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage == ListData) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvociePartialPaymentStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvociePartialPaymentStatus.png");
		}

	}

	@Test(priority = 62)
	private void UpdatedInvociePaid() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Invoice has been Paid status");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.listTextValidation("Paid");
		create.listTextValidation("PayAmount");
		String responseMessage = create.listTextValidation("ListPaidStatus");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Paid"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("Paid"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListData = create.doubleValue(2);
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvociePaidStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvociePaidStatus.png");
			ListData = create.doubleValue(2);
		}

	}

	@Test(priority = 62)
	private void fullyPaidAmount() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Check the Fully Paid Amount:" + ListData + " in the Invoice List Page");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		double responseMessage = create.doubleValue(2);
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListData);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage == ListData) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = create.listTextValidation("GlobalCustomerName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvociePartialPaymentStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvociePartialPaymentStatus.png");
			ListField = create.listTextValidation("GlobalCustomerName");
		}

	}

	@Test(priority = 63)
	private void listCustomerName() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the Invoice Customer Name:" + ListField
				+ "in the Search field & Invoice list retrived successfully");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
//		Thread.sleep(5000);
		create.listTextValidation("GlobalCustomerName");
		create.listTextValidation("CustomerSearchData");
		String expected = create.listTextValidation("SearchGlobalCustomerName");
		extentTest.log(Status.INFO, "Actual Result is -" + expected);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (ListField.equals(expected)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = create.listTextValidation("SearchInvoiceNo");
			create.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListQuoteNoValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListQuoteNoValidation.png");
			ListField = create.listTextValidation("SearchInvoiceNo");
			create.clearFields("Search");
		}

	}

	@Test(priority = 64)
	private void listInvoiceNo() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Enter the Invoice No:" + ListField + " in the Search field & Invoice list retrived successfully");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.listTextValidation("SearchInvoiceNo");
		create.listTextValidation("SearchData");
		String expected = create.listTextValidation("SearchInvoiceNo");
		extentTest.log(Status.INFO, "Actual Result is -" + ListField);
		extentTest.log(Status.INFO, "Expected Result is -" + expected);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (ListField.equals(expected)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = create.listTextValidation("SearchReference");
			create.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListInvoiceNoValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListInvoiceNoValidation.png");
			ListField = create.listTextValidation("SearchReference");
			create.clearFields("Search");
		}

	}

	@Test(priority = 65)
	private void listQuoteReference() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports.createTest("Enter the Invoice Reference:" + ListField
				+ " in the Search field & Invoice list retrived successfully");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.listTextValidation("SearchReference");
		create.listTextValidation("SearchData");
		String expected = create.listTextValidation("SearchReference");
		extentTest.log(Status.INFO, "Actual Result is -" + ListField);
		extentTest.log(Status.INFO, "Expected Result is -" + expected);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (ListField.equals(expected)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.clearFields("Search");
			ListField = create.dateValidation("GlobalDueDate");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListQuoteReferenceValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListQuoteReferenceValidation.png");
			create.clearFields("Search");
			ListField = create.dateValidation("GlobalDueDate");
		}

	}

	@Test(priority = 66)
	private void filterDateInvoice() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports.createTest("Verify the Invoice Due Date List filter by Due From Date:" + ListField);
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.dateValidation("Convertion");
		String dateValidation = create.dateValidation("GlobalDueDate");
		extentTest.log(Status.INFO, "Actual Result is -" + ListField);
		extentTest.log(Status.INFO, "Expected Result is -" + dateValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (ListField.equals(dateValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceListDueDateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceListDueDateValidation.png");
		}

	}

	@Test(priority = 67)
	private void listInvalid() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.listTextValidation("Invalid");
		String actual = create.listTextValidation("InvalidList");
		extentTest.log(Status.INFO, "Actual Result is -" + actual);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actual.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceListInvalidValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceListInvalidValidation.png");
		}

	}

}
