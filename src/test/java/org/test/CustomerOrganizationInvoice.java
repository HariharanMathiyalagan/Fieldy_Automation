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
import org.testng.Assert;
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
import com.zaigo.pageobjects.InvoicePage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class CustomerOrganizationInvoice extends BaseClass{
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
		extentHtmlReporter = new ExtentHtmlReporter("CustomerOrganizationInvoice.html");
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
		loginInPage.passwordField(loginInPage.getPropertyValue("Password"));
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
	private void modulePage() throws InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify Customer Organization List Page is opened when clicking on Cusotmer->Organization");
		CustomerCreateOrganizationPage modulePage = PageFactory.initElements(driver, CustomerCreateOrganizationPage.class);
		modulePage.modulePage();

	}

	@Test(priority = 0)
	private void createOrganization() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports
				.createTest("Verify a new Customer Organization is created successfully through [Create Organization]");
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

	@Test(priority = 1)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Invoice Page");
		InvoicePage initElements = PageFactory.initElements(driver, InvoicePage.class);
		customerContactName = initElements.labelValidation("Organization");
		String jobLandPage = initElements.invocieLandPage("CreateLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatePageInvoiceLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("CreatePageInvoiceLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateInvoiceLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateInvoiceLabel.png");
		}
	}

	@Test(priority = 2)
	private void namePrepopulation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the Customer Contact Name:" + customerContactName
				+ " is prepopulated in the Contact Name Field");
		InvoicePage initElements = PageFactory.initElements(driver, InvoicePage.class);
		String customerName = initElements.customerName("PlaceHolderName");
		extentTest.log(Status.INFO, "Actual Result is -" + customerContactName);
		extentTest.log(Status.INFO, "Expected Result is -" + customerName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (customerContactName.equals(customerName)) {
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
	
	@Test(priority = 3)
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
			contactMandatory.autoCompleteField("VisibleName");
		}

	}

	@Test(priority = 4)
	private void mandatoryValidationDeuDate() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Expiry Date field is set as Mandatory & Error Message is displayed when it is BLANK");
		InvoicePage mandatoryValidation = PageFactory.initElements(driver, InvoicePage.class);
		mandatoryValidation.clearFields("DueDate");
		mandatoryValidation.clearFields("Quantity");
		mandatoryValidation.clearFields("Price");
		mandatoryValidation.saveFunction("Mandatory");
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

	@Test(priority = 5)
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

	@Test(priority = 6)
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

	@Test(priority = 6)
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

	@Test(priority = 7)
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

	@Test(priority = 8)
	private void maximumValidationReference() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Reference Field exceed its max-256 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.pickFirstItem("Organization");
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
				.createTest("Verify Error Message is displayed when Note field exceed its max-2048 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxNotes();
		String errorPasswordField = mandatory.errorValidation("ErrorNotes");
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
		String errorPasswordField = mandatory.responseMessage("Message");
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
	private void createInvoice() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports
				.createTest("Verify Invoice is created successfully from Customer Contact->Create Invoice");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.CRUDValidation("Create");
		String errorPasswordField = mandatory.responseMessage("Message");
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

	@Test(priority = 30)
	private void invoiceCreatedCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the Customer Contact Invoice Count is added in the Total Invoice Count");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		int actualTotal = create.countValidation(1);
		int expectedResult = create.countValidation(2);
		extentTest.log(Status.INFO, "Actual Result is -" + actualTotal);
		extentTest.log(Status.INFO, "Expected Result is -" + expectedResult);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualTotal == expectedResult) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("InvoiceCountValidate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("InvoiceCountValidate.png");
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
				.createTest("Verify Error Message is displayed when Note field exceed its max-2048 limit");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.maxNotes();
		String errorPasswordField = mandatory.errorValidation("ErrorNotes");
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
		String errorPasswordField = mandatory.responseMessage("Message");
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
	private void updatedInvoice() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports
				.createTest("Verify Invoice is updated successfully from Customer Contact->Update Invoice");
		InvoicePage mandatory = PageFactory.initElements(driver, InvoicePage.class);
		mandatory.CRUDValidation("Edit");
		mandatory.inventoryItemValidation("Calculation");
		mandatory.saveFunction("ClickButton");
		String errorPasswordField = mandatory.responseMessage("Message");
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
		create.CRUDValidation("DraftOrg");
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
			ListField = create.listTextValidation("InvoiceNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactInvociePartialPaymentStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactInvociePartialPaymentStatus.png");
			ListField = create.listTextValidation("InvoiceNo");
		}

	}

	@Test(priority = 63)
	private void listInvoiceNo() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Enter the Invoice No:" + ListField + " in the Search field & Invoice list retrived successfully");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
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

	@Test(priority = 64)
	private void listQuoteReference() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports.createTest(
				"Enter the Quote Reference:" + ListField + " in the Search field & Quote list retrived successfully");
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.listTextValidation("Reference");
		create.listTextValidation("SearchData");
		String expected = create.listTextValidation("SearchReference");
		extentTest.log(Status.INFO, "Actual Result is -" + ListField);
		extentTest.log(Status.INFO, "Expected Result is -" + expected);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (ListField.equals(expected)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.clearFields("Search");
			ListField = create.dateValidation("DueDate");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListQuoteReferenceValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListQuoteReferenceValidation.png");
			create.clearFields("Search");
			ListField = create.dateValidation("DueDate");
		}

	}

	@Test(priority = 65)
	private void filterDateInvoice() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports.createTest("Verify the Invoice Due Date List filter by Due From Date:" + ListField);
		InvoicePage create = PageFactory.initElements(driver, InvoicePage.class);
		create.dateValidation("Convertion");
		String dateValidation = create.dateValidation("DueDate");
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

	@Test(priority = 66)
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
