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
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.BusinessDaysPage;
import com.zaigo.pageobjects.InvoicePage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.ProductServicePage;
import com.zaigo.utility.BrowserSetup;

public class ProductModule extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String ListField;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("InventoryProduct.html");
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
		extentTest = extentReports.createTest("Navigate to Product & Service page");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.modulePage("Product");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ListProductService"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("ListProductService"))) {
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
		extentTest = extentReports.createTest("Verify the User to Land on the Create Product Page");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.labelValidation("Create");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreateProduct"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CreateProduct"))) {
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
	private void mandatoryValidationProductName() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Product Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		initElements.inventoryName("Mandatory");
		String editContact = initElements.errorFields("InventoryName");
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
		extentTest = extentReports.createTest(
				"Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.errorFields("Description");
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
	private void mandatoryValidationPrice() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.errorFields("Price");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PriceErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PriceErrorMandatoryError.png");
		}
	}

	@Test(priority = 6)
	private void mandatoryValidationTaxName() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Tax Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		initElements.validData("MandatoryData");
		String editContact = initElements.errorFields("TaxName");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TaxNameErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TaxNameErrorMandatoryError.png");
		}
	}

	@Test(priority = 7)
	private void mandatoryValidationTaxPercentage() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Tax Percentage field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.errorFields("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TaxPercentageErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TaxPercentageErrorMandatoryError.png");
		}
	}

	@Test(priority = 8)
	private void maximumValidationProductName() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Product Name Field exceed its max-256 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.inventoryName("MaxValidation");
		String errorPasswordField = mandatory.errorFields("InventoryName");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("InventoryName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("InventoryName");
		}

	}

	@Test(priority = 9)
	private void maximumValidationSKU() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when SKU Field exceed its max-64 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.sku("MaxValidation");
		String errorPasswordField = mandatory.errorFields("SKU");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max64CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max64CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("SKU");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("SKU");
		}
	}

	@Test(priority = 10)
	private void maximumValidationDescription() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description Field exceed its max-2048 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.description("MaxValidation");
		String errorPasswordField = mandatory.errorFields("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("Description");
		}
	}

	@Test(priority = 11)
	private void maximumValidationPrice() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field exceed its max-6 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.price("MaxValidation");
		String errorPasswordField = mandatory.errorFields("Price");
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
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("Price");
		}
	}

	@Test(priority = 12)
	private void beforeDecimalPointPriceField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Price Field exceed its max-6 before decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.price("BeforeDecimal");
		String errorPasswordField = mandatory.errorFields("Price");
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

	@Test(priority = 13)
	private void afterDecimalPointPriceField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Price Field exceed its max-2 after decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.price("AfterDecimal");
		String errorPasswordField = mandatory.errorFields("Price");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 14)
	private void maximumValidationTax() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tax Field exceed its max-3 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.validData("MandatoryData");
		mandatory.taxPercentage("MaxValidation");
		String errorPasswordField = mandatory.errorFields("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("TaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("TaxPercentage");
		}
	}

	@Test(priority = 15)
	private void beforeDecimalPointTaxPercentageField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Percentage Field exceed its max-6 before decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.taxPercentage("BeforeDecimal");
		String errorPasswordField = mandatory.errorFields("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DisTaxBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DisTaxBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("TaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			mandatory.clearFields("TaxPercentage");
		}

	}

	@Test(priority = 16)
	private void afterDecimalPointTaxPercentageField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Percentage Field exceed its max-2 after decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.taxPercentage("AfterDecimal");
		String errorPasswordField = mandatory.errorFields("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("TaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("TaxPercentage");
		}

	}

	@Test(priority = 17)
	private void maximumValidationQuantityHand() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity on Hand Field exceed its max-12 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.quantityHand("MaxValidation");
		String errorPasswordField = mandatory.errorFields("QuantityHand");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max12CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max12CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("QuantityHand");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("QuantityHand");
		}
	}

	@Test(priority = 18)
	private void beforeDecimalPointQuantityHandField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity on Hand Field exceed its max-12 before decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.quantityHand("BeforeDecimal");
		String errorPasswordField = mandatory.errorFields("QuantityHand");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuantityBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("QuantityBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("QuantityHand");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			mandatory.clearFields("QuantityHand");
		}

	}

	@Test(priority = 19)
	private void afterDecimalPointQuantityHandField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity on Hand Field exceed its max-2 after decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.quantityHand("AfterDecimal");
		String errorPasswordField = mandatory.errorFields("QuantityHand");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("QuantityHand");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("QuantityHand");
		}

	}

	@Test(priority = 20)
	private void maximumValidationLowStockThresold() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Low Stock Thresold Field exceed its max-12 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.lowStock("MaxValidation");
		String errorPasswordField = mandatory.errorFields("LowStock");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max12CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max12CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("LowStock");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("LowStock");
		}
	}

	@Test(priority = 21)
	private void beforeDecimalPointLowStockThresoldField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity on Hand Field exceed its max-12 before decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.lowStock("BeforeDecimal");
		String errorPasswordField = mandatory.errorFields("LowStock");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuantityBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("QuantityBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("LowStock");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			mandatory.clearFields("LowStock");
		}

	}

	@Test(priority = 22)
	private void afterDecimalPointLowStockThresoldField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity on Hand Field exceed its max-2 after decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.lowStock("AfterDecimal");
		String errorPasswordField = mandatory.errorFields("LowStock");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("LowStock");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("LowStock");
		}
	}

	@Test(priority = 23)
	private void createButton() throws IOException, AWTException {
		extentTest = extentReports.createTest(
				"Verify the Create Product page Save & Compelete Button is displayed in the Create form page");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		String errorPasswordField = mandatory.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SaveButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("SaveButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearAllFields("Product");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobNotesMaximumValidation.png");
			mandatory.clearAllFields("Product");
		}
	}

	static String listValidation;

	@Test(priority = 24)
	private void createProduct() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify Product is created successfully from Create Product");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.validData("FillFields");
		mandatory.validData("Taxable");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InventoryCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("InventoryCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidation = mandatory.listValidation("Taxable");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			mandatory.message("AlternateFunction");
			listValidation = mandatory.listValidation("Taxable");
		}
	}

	@Test(priority = 25)
	private void editFormLabel() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Product Page");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.labelValidation("Edit");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditProduct"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("EditProduct"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			initElements.clearAllFields("Product");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("ContactList.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("ContactList.png");
			initElements.clearAllFields("Product");
		}
	}

	@Test(priority = 26)
	private void editmandatoryValidationProductName() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Product Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		initElements.clickEvent("SaveButton");
		String editContact = initElements.errorFields("InventoryName");
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

	@Test(priority = 27)
	private void editmandatoryValidationDescription() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Description field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.errorFields("Description");
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

	@Test(priority = 28)
	private void editmandatoryValidationPrice() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.errorFields("Price");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("PriceErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("PriceErrorMandatoryError.png");

		}
	}

	@Test(priority = 29)
	private void editmandatoryValidationTaxName() throws InterruptedException, IOException, AWTException {
		if (listValidation.equals("No")) {
			throw new SkipException("Skipping / Ignoring - Script not Ready for Execution ");
		} else {
		}
		extentTest = extentReports
				.createTest("Verify Tax Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		initElements.clearFields("TaxName");
		initElements.validData("MandatoryData");
		String editContact = initElements.errorFields("TaxName");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TaxNameErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TaxNameErrorMandatoryError.png");
		}
	}

	@Test(priority = 30)
	private void editmandatoryValidationTaxPercentage() throws InterruptedException, IOException, AWTException {
		if (listValidation.equals("No")) {
			throw new SkipException("Skipping / Ignoring - Script not Ready for Execution ");
		} else {
		}
		extentTest = extentReports.createTest(
				"Verify Tax Percentage field is set as Mandatory & Error Message is displayed when it is BLANK");
		ProductServicePage initElements = PageFactory.initElements(driver, ProductServicePage.class);
		String editContact = initElements.errorFields("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("TaxPercentageErrorMandatoryError.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("TaxPercentageErrorMandatoryError.png");
		}
	}

	@Test(priority = 31)
	private void editmaximumValidationProductName() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Product Name Field exceed its max-256 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.inventoryName("MaxValidation");
		String errorPasswordField = mandatory.errorFields("InventoryName");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("InventoryName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("InventoryName");
		}

	}

	@Test(priority = 32)
	private void editmaximumValidationSKU() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when SKU Field exceed its max-64 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.sku("MaxValidation");
		String errorPasswordField = mandatory.errorFields("SKU");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max64CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max64CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("SKU");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("SKU");
		}
	}

	@Test(priority = 33)
	private void editmaximumValidationDescription() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description Field exceed its max-2048 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.description("MaxValidation");
		String errorPasswordField = mandatory.errorFields("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("Description");
		}
	}

	@Test(priority = 34)
	private void editmaximumValidationPrice() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field exceed its max-6 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.price("MaxValidation");
		String errorPasswordField = mandatory.errorFields("Price");
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
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("Price");
		}
	}

	@Test(priority = 35)
	private void editbeforeDecimalPointPriceField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Price Field exceed its max-6 before decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.price("BeforeDecimal");
		String errorPasswordField = mandatory.errorFields("Price");
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

	@Test(priority = 36)
	private void editafterDecimalPointPriceField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Price Field exceed its max-2 after decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.price("AfterDecimal");
		String errorPasswordField = mandatory.errorFields("Price");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Price");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("Price");
		}

	}

//	@Test(priority = 37)
	private void editmaximumValidationTax() throws IOException, InterruptedException, AWTException {
		if (listValidation.equals("No")) {
			throw new SkipException("Skipping / Ignoring - Script not Ready for Execution ");
		} else {
		}
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tax Field exceed its max-3 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.validData("MandatoryData");
		mandatory.taxPercentage("MaxValidation");
		String errorPasswordField = mandatory.errorFields("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("TaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("TaxPercentage");
		}
	}

	@Test(priority = 38)
	private void editbeforeDecimalPointTaxPercentageField() throws IOException, InterruptedException, AWTException {
		if (listValidation.equals("No")) {
			throw new SkipException("Skipping / Ignoring - Script not Ready for Execution ");
		} else {
		}
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Percentage Field exceed its max-6 before decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.taxPercentage("BeforeDecimal");
		String errorPasswordField = mandatory.errorFields("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DisTaxBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("DisTaxBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("TaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			mandatory.clearFields("TaxPercentage");
		}

	}

	@Test(priority = 39)
	private void editafterDecimalPointTaxPercentageField() throws IOException, InterruptedException, AWTException {
		if (listValidation.equals("No")) {
			throw new SkipException("Skipping / Ignoring - Script not Ready for Execution ");
		} else {
		}
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Percentage Field exceed its max-2 after decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.taxPercentage("AfterDecimal");
		String errorPasswordField = mandatory.errorFields("TaxPercentage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("TaxPercentage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("TaxPercentage");
		}

	}

	@Test(priority = 40)
	private void editmaximumValidationQuantityHand() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity on Hand Field exceed its max-12 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.quantityHand("MaxValidation");
		String errorPasswordField = mandatory.errorFields("QuantityHand");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max12CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max12CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("QuantityHand");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("QuantityHand");
		}
	}

	@Test(priority = 41)
	private void editbeforeDecimalPointQuantityHandField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity on Hand Field exceed its max-12 before decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.quantityHand("BeforeDecimal");
		String errorPasswordField = mandatory.errorFields("QuantityHand");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuantityBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("QuantityBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("QuantityHand");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			mandatory.clearFields("QuantityHand");
		}

	}

	@Test(priority = 42)
	private void editafterDecimalPointQuantityHandField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity on Hand Field exceed its max-2 after decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.quantityHand("AfterDecimal");
		String errorPasswordField = mandatory.errorFields("QuantityHand");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("QuantityHand");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("QuantityHand");
		}

	}

	@Test(priority = 43)
	private void editmaximumValidationLowStockThresold() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Low Stock Thresold Field exceed its max-12 limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.lowStock("MaxValidation");
		String errorPasswordField = mandatory.errorFields("LowStock");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max12CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max12CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("LowStock");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceReferenceMaximumValidation.png");
			mandatory.clearFields("LowStock");
		}
	}

	@Test(priority = 44)
	private void editbeforeDecimalPointLowStockThresoldField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity on Hand Field exceed its max-12 before decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.lowStock("BeforeDecimal");
		String errorPasswordField = mandatory.errorFields("LowStock");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("QuantityBeforeDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("QuantityBeforeDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("LowStock");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceBeforePriceMaximumValidation.png");
			mandatory.clearFields("LowStock");
		}

	}

	@Test(priority = 45)
	private void editafterDecimalPointLowStockThresoldField() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity on Hand Field exceed its max-2 after decimal point limit");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.lowStock("AfterDecimal");
		String errorPasswordField = mandatory.errorFields("LowStock");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("LowStock");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvoiceAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("LowStock");
		}
	}

	@Test(priority = 46)
	private void updateButton() throws IOException, AWTException {
		extentTest = extentReports
				.createTest("Verify the Create Product page Update Button is displayed in the Edit form page");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		String errorPasswordField = mandatory.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("UpdatedButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearAllFields("Product");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobNotesMaximumValidation.png");
			mandatory.clearAllFields("Product");
		}
	}

	@Test(priority = 47)
	private void updateProduct() throws WebDriverException, IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify Product is updated successfully from Edit Product");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.validData("FillFields");
		if (listValidation.equals("Yes")) {
			mandatory.validData("Taxable");
		} else {

		}
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InventoryUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("InventoryUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidation = mandatory.listValidation("InventoryName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("UnscheduleJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("UnscheduleJob.png");
			mandatory.message("AlternateFunction");
			listValidation = mandatory.listValidation("InventoryName");
		}
	}

	@Test(priority = 48)
	private void searchProductName() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Enter the Product Name:(" + listValidation
				+ ") in the Search field & Product list retrived successfully");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.listValidation("SearchData");
		String errorPasswordField = mandatory.listValidation("InventoryName");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + listValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(listValidation)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchJobNo.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchJobNo.png");
			mandatory.clearFields("Search");
		}
	}

	@Test(priority = 49)
	private void searchInvalid() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.listValidation("Invalid");
		String errorPasswordField = mandatory.listValidation("InvalidResult");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clickEvent("Reset");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("searchInvalid.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("searchInvalid.png");
			mandatory.clickEvent("Reset");
		}
	}

	@Test(priority = 50)
	private void deletedProduct() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports
				.createTest("Verify deleted successful message is displayed, when the Product Deleted");
		ProductServicePage mandatory = PageFactory.initElements(driver, ProductServicePage.class);
		mandatory.listValidation("Delete");
		String errorPasswordField = mandatory.message("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InventoryDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("InventoryDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			ListField = mandatory.createProdutService("Create");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("DeletedJob.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("DeletedJob.png");
			ListField = mandatory.createProdutService("Create");
		}
	}

	@Test(priority = 51)
	private void reflectQuotePage() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the newly created Product Name is:" + ListField
				+ " & it's reflect the Inventory field in the Quote Create Page");
		ProductServicePage landing = PageFactory.initElements(driver, ProductServicePage.class);
		String createMessage = landing.reflectedFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clickEvent("Product");
			ListField = landing.createProdutService("Edit");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.clickEvent("Product");
			ListField = landing.createProdutService("Edit");
		}
	}

	@Test(priority = 52)
	private void inactiveProduct() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Inactive Product Name is:" + ListField
				+ " & it's not reflect the Inventory field in the Quote Create Page");
		ProductServicePage landing = PageFactory.initElements(driver, ProductServicePage.class);
		String createMessage = landing.reflectedFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField + " Will be added");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField + " Will be added")) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clickEvent("Product");
			ListField = landing.deleteProduct();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.clickEvent("Product");
			ListField = landing.deleteProduct();
		}
	}

	@Test(priority = 53)
	private void deleteProduct() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Delete Product Name is:" + ListField
				+ " & it's not reflect the Inventory field in the Quote Create Page");
		ProductServicePage landing = PageFactory.initElements(driver, ProductServicePage.class);
		String createMessage = landing.reflectedFunction();
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + ListField + " Will be added");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(ListField + " Will be added")) {
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
