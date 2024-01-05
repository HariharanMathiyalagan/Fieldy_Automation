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
import com.zaigo.pageobjects.InvoicePage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.QuotePage;
import com.zaigo.utility.BrowserSetup;

public class CustomerContactQuote extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String customerContactName;
	static String currentDate;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("CustomerContactQuote.html");
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
	private void contactModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Navigate to Customer Contact Page");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		String editContact = initElements.modulePage();
		System.out.println(editContact);
		extentTest.log(Status.INFO, "Actual Result is -" + editContact);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CustomerContactList"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (editContact.equals(getPropertyValue("CustomerContactList"))) {
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
	private void CreateContact() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify a new Customer Contact is created successfully through [Create Contact]");
		CustomerCreateContactPage initElements = PageFactory.initElements(driver, CustomerCreateContactPage.class);
		initElements.contactPage();
		initElements.propertyPage();
		initElements.equipmentPage("SaveComplete");
		String responseMessageCreateContact1 = initElements.responseMessage("CustomerCreate");
		System.out.println(responseMessageCreateContact1);
		extentTest.log(Status.INFO, "Actual Result create response messages is -" + responseMessageCreateContact1);
		extentTest.log(Status.INFO,
				"Expected Result create response messages is -" + getPropertyValue("CustomerCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessageCreateContact1.equals(getPropertyValue("CustomerCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateValidation.png");
			initElements.responseMessage("AlternateFunction");
		}

	}

	@Test(priority = 1)
	private void listabelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User is Land on the Customer / Contact / Quote page");
		QuotePage jobPage = PageFactory.initElements(driver, QuotePage.class);
		String jobLandPage = jobPage.labelValidation("Customer");
		System.out.println(jobLandPage);
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContactQuoteListPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("ContactQuoteListPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			customerContactName = jobPage.customerName("DetailScreenCustomerName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CreateRequestLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CreateRequestLabel.png");
			customerContactName = jobPage.customerName("DetailScreenCustomerName");
		}
	}
	
	

	@Test(priority = 2)
	private void labelValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify Create Job page is opened from Contact-> Quote -> Create Job");
		QuotePage jobPage = PageFactory.initElements(driver, QuotePage.class);
		String jobLandPage = jobPage.labelValidation("CreateLabel");
		System.out.println(jobLandPage);
		extentTest.log(Status.INFO, "Actual Result is -" + jobLandPage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreatePageQuoteLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (jobLandPage.equals(getPropertyValue("CreatePageQuoteLabel"))) {
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
		extentTest = extentReports.createTest("Verify the Customer Contact Name:" + customerContactName
				+ " is prepopulated in the Contact Name Field");
		QuotePage jobPage = PageFactory.initElements(driver, QuotePage.class);
		String customerName = jobPage.customerName("PlaceHolderName");
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

	@Test(priority = 4)
	private void mandatoryValidationExpiryDate() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Expiry Date field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
		mandatoryValidation.clearFields("Quantity");
		mandatoryValidation.clearFields("Price");
		mandatoryValidation.saveFunction("Mandatory");
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorExpiryDate");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteExpiryDateMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteExpiryDateMandatory.png");
		}
	}

	@Test(priority = 5)
	private void mandatoryValidationInventoryItem() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Inventory Item field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteInventoryItemMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteInventoryItemMandatory.png");
		}
	}

	@Test(priority = 6)
	private void mandatoryValidationQunatity() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Quantity field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityMandatory.png");
		}
	}

	@Test(priority = 6)
	private void mandatoryValidationPrice() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuotePriceMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuotePriceMandatory.png");
			
		}
	}

	@Test(priority = 7)
	private void mandatoryValidationDescription() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorDescription");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		Thread.sleep(10000);
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
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		//mandatory.ChooseLineItem();
		//mandatory.validationQuantity("Value");
		mandatory.referenceField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("ErrorReference");
		System.out.println(errorPasswordField +"Ref");
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
			File file = new File("CustomerContactQuoteReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteReferenceMaximumValidation.png");
			mandatory.clearFields("Reference");
		}

	}
	
	

	@Test(priority = 9)
	private void maximumValidationQuoteTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quote Tittle Field exceed its max-256 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.tittleField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("ErrorQuoteTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("QuoteTittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteTittleMaximumValidation.png");
			mandatory.clearFields("QuoteTittle");
		}

	}

	@Test(priority = 10)
	private void maximumValidationInventoryItem() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Inventory Item Field exceed its max-256 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.inventoryItemField("MaxValidation");
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
			File file = new File("CustomerContactQuoteInventoryMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteInventoryMaximumValidation.png");
			mandatory.clearFields("Inventory");
		}

	}

	@Test(priority = 11)
	private void emptyValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity Field is enter the negative value");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityEmptyValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityEmptyValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 12)
	private void maxQuanitityLimit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity Field exceed its max-12 Limts");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityMaximumValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 13)
	private void afterDecimalPointValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-2 after decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityDeciamlPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityDeciamlPointValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 14)
	private void beforeDecimalPointValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-12 before decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityBeforeDeciamlPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityBeforeDeciamlPointValidation.png");
			mandatory.clearFields("Quantity");
			mandatory.validationQuantity("Value");
		}

	}

	@Test(priority = 15)
	private void emptyValidationPriceField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field is enter the negative value");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuotePriceNegativeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuotePriceNegativeValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 16)
	private void maxPriceLimit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field exceed its max-6 Limts");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuotePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuotePriceMaximumValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 17)
	private void beforeDecimalPointPriceField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-6 before decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteBeforePriceMaximumValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 18)
	private void afterDecimalPointPriceField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-2 after decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("Price");
			mandatory.priceValidation("value");
		}

	}

	@Test(priority = 19)
	private void afterDecimalPointDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Discout Field exceed its max-2 after decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteAfterDecimalPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteAfterDecimalPointValidation.png");
			mandatory.clearFields("Discount");
		}

	}

	@Test(priority = 20)
	private void beforeDecimalPointDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Discount Field exceed its max-3 before decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteBeforeDiscountDecimalValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteBeforeDiscountDecimalValidation.png");
			mandatory.clearFields("Discount");
		}

	}

	@Test(priority = 21)
	private void maxLimitDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Discount Field exceed its max-100 Limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteDiscoutnMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteDiscoutnMaximumValidation.png");
			mandatory.clearFields("Discount");
			mandatory.discountValidation("value");
		}

	}
	
	

//	@Test(priority = 22)
//	private void afterDecimalPointTaxField() throws IOException, InterruptedException {
//		extentTest = extentReports.createTest(
//				"Verify Error Message is displayed when Tax Field exceed its max-2 after decimal point limit");
//		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
//		mandatory.taxValidation("AfterDecimalPoint");
//		String errorPasswordField = mandatory.errorValidation("ErrorTax");
//		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
//		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AfterDecimalPoint"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorPasswordField.equals(getPropertyValue("AfterDecimalPoint"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//			mandatory.clearFields("Tax");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("CustomerContactQuoteAfterDecimalPointTaxValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("CustomerContactQuoteAfterDecimalPointTaxValidation.png");
//			mandatory.clearFields("Tax");
//		}
//
//	}
//
//	@Test(priority = 23)
//	private void beforeDecimalPointTaxField() throws IOException, InterruptedException {
//		extentTest = extentReports.createTest(
//				"Verify Error Message is displayed when Tax Field exceed its max-3 before decimal point limit");
//		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
//		mandatory.taxValidation("BeforeDecimalPoint");
//		String errorPasswordField = mandatory.errorValidation("ErrorTax");
//		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
//		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("DisTaxBeforeDecimalPoint"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorPasswordField.equals(getPropertyValue("DisTaxBeforeDecimalPoint"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//			mandatory.clearFields("Tax");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("CustomerContactQuoteBeforeTaxDecimalValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("CustomerContactQuoteBeforeTaxDecimalValidation.png");
//			mandatory.clearFields("Tax");
//		}
//
//	}
//
//	@Test(priority = 24)
//	private void maxLimitTaxField() throws IOException, InterruptedException {
//		extentTest = extentReports
//				.createTest("Verify Error Message is displayed when Tax Field exceed its max-100 Limit");
//		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
//		mandatory.taxValidation("MaxTax");
//		String errorPasswordField = mandatory.errorValidation("ErrorTax");
//		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
//		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TaxLimit"));
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (errorPasswordField.equals(getPropertyValue("TaxLimit"))) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//			mandatory.clearFields("Tax");
//			mandatory.taxValidation("value");
//
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("CustomerContactQuoteTaxMaximumValidation.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("CustomerContactQuoteTaxMaximumValidation.png");
//			mandatory.clearFields("Tax");
//			mandatory.taxValidation("value");
//		}
//
//	}

	@Test(priority = 25)
	private void maximumValidationDescription() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-256 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.descriptionValidation("MaxCharacter");
		String errorPasswordField = mandatory.errorValidation("ErrorDescription");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20000Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max20000Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
			mandatory.descriptionValidation("value");
			mandatory.pickFirstItem("Contact");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
			mandatory.descriptionValidation("value");
			mandatory.pickFirstItem("Contact");
		}

	}

	@Test(priority = 26)
	private void maximumValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Note field exceed its max-20000 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.notesField("MaxValidation");
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
			File file = new File("CustomerContactQuoteNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteNotesMaximumValidation.png");
			mandatory.clearFields("Notes");
		}

	}

	@Test(priority = 27)
	private void expiryFieldPastDateValidation() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Expiry field enter the past date");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.dateValidation("PastDate");
		currentDate = mandatory.dateValidation("CurrentDateError");
		String errorPasswordField = mandatory.message("FormMessage");
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
			File file = new File("CustomerContactQuotePastDateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuotePastDateValidation.png");
			mandatory.clearFields("Expiry");
		}

	}

	@Test(priority = 28)
	private void validateCalculation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify to check the Line item calculation");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
	private void createQuote() throws IOException, InterruptedException, ParseException, AWTException {
		extentTest = extentReports
				.createTest("Verify Quote is created successfully from Customer Contact->Create Quote");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.attachmentFileCheck("URLCheck");
		mandatory.CRUDValidation("Create");
		String errorPasswordField = mandatory.message("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CreateMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("CreateMessage"))) {
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
	
	

	//We have Removed this Feature P30 So commenting this
	
//	@Test(priority = 30)
//	private void quoteCreatedCount() throws IOException, InterruptedException {
//		extentTest = extentReports
//				.createTest("Verify the Customer Contact Quote Count is added in the Total Quote Count");
//		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
//		int actualTotal = create.countValidation(1);
//		int expectedResult = create.countValidation(2);
//		extentTest.log(Status.INFO, "Actual Result is -" + actualTotal);
//		extentTest.log(Status.INFO, "Expected Result is -" + expectedResult);
//		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
//		if (actualTotal == expectedResult) {
//			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
//		} else {
//			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("QupteCountValidate.png");
//			FileHandler.copy(screenshotAs, file);
//			extentTest.addScreenCaptureFromPath("QupteCountValidate.png");
//		}
//
//	}

	
	
	@Test(priority = 31)
	private void quoteEdit() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Quote Page");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		String responseMessage = create.labelValidation("EditLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditPageQuoteLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("EditPageQuoteLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.clearAllFields();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("QupteCountValidate.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("QupteCountValidate.png");
			create.clearAllFields();
		}

	}

	@Test(priority = 32)
	private void editmandatoryValidationExpiryDate() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Expiry Date field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
		mandatoryValidation.clearFields("Quantity");
		mandatoryValidation.clearFields("Price");
		mandatoryValidation.saveFunction("Mandatory");
		String errorMandatoryValidation = mandatoryValidation.errorValidation("ErrorExpiryDate");
		extentTest.log(Status.INFO, "Actual Result is -" + errorMandatoryValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorMandatoryValidation.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteExpiryDateMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteExpiryDateMandatory.png");
		}
	}

//	@Test(priority = 33)
	private void editmandatoryValidationInventoryItem() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Inventory Item field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteInventoryItemMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteInventoryItemMandatory.png");
		}
	}

	@Test(priority = 34)
	private void editmandatoryValidationQunatity() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Quantity field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityMandatory.png");
		}
	}

	@Test(priority = 35)
	private void editmandatoryValidationPrice() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuotePriceMandatory.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuotePriceMandatory.png");
		}
	}

	@Test(priority = 36)
	private void editmandatoryValidationDescription() throws AWTException, IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Price field is set as Mandatory & Error Message is displayed when it is BLANK");
		QuotePage mandatoryValidation = PageFactory.initElements(driver, QuotePage.class);
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
	private void editmaximumValidationReference() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Reference Field exceed its max-256 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		//mandatory.pickFirstItem("Contact");
		//mandatory.validationQuantity("Value");
		mandatory.referenceField("MaxValidation");
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
			File file = new File("CustomerContactQuoteReferenceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteReferenceMaximumValidation.png");
			mandatory.clearFields("Reference");
		}

	}

	@Test(priority = 38)
	private void editmaximumValidationQuoteTittle() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quote Tittle Field exceed its max-256 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.tittleField("MaxValidation");
		String errorPasswordField = mandatory.errorValidation("ErrorQuoteTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("QuoteTittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteTittleMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteTittleMaximumValidation.png");
			mandatory.clearFields("QuoteTittle");
		}

	}
	
	/*

	@Test(priority = 39)
	private void editmaximumValidationInventoryItem() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Inventory Item Field exceed its max-256 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.inventoryItemField("MaxValidation");
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
			File file = new File("CustomerContactQuoteInventoryMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteInventoryMaximumValidation.png");
			mandatory.clearFields("Inventory");
		}

	}
	
	*/

	@Test(priority = 40)
	private void editemptyValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity Field is enter the negative value");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityEmptyValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityEmptyValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 41)
	private void editmaxQuanitityLimit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Quantity Field exceed its max-12 Limts");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityMaximumValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 42)
	private void editafterDecimalPointValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-2 after decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityDeciamlPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityDeciamlPointValidation.png");
			mandatory.clearFields("Quantity");
		}

	}

	@Test(priority = 43)
	private void editbeforeDecimalPointValidationQuantityField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-12 before decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteQuantityBeforeDeciamlPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteQuantityBeforeDeciamlPointValidation.png");
			mandatory.clearFields("Quantity");
			mandatory.validationQuantity("Value");
		}

	}

	@Test(priority = 44)
	private void editemptyValidationPriceField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field is enter the negative value");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuotePriceNegativeValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuotePriceNegativeValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 45)
	private void editmaxPriceLimit() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Price Field exceed its max-6 Limts");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuotePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuotePriceMaximumValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 46)
	private void editbeforeDecimalPointPriceField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-6 before decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteBeforePriceMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteBeforePriceMaximumValidation.png");
			mandatory.clearFields("Price");
		}

	}

	@Test(priority = 47)
	private void editafterDecimalPointPriceField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Quantity Field exceed its max-2 after decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteAfterPriceDecimalPointsValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteAfterPriceDecimalPointsValidation.png");
			mandatory.clearFields("Price");
			mandatory.priceValidation("value");
		}

	}

	@Test(priority = 48)
	private void editafterDecimalPointDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Discout Field exceed its max-2 after decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteAfterDecimalPointValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteAfterDecimalPointValidation.png");
			mandatory.clearFields("Discount");
		}

	}

	@Test(priority = 49)
	private void editbeforeDecimalPointDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Discount Field exceed its max-3 before decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteBeforeDiscountDecimalValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteBeforeDiscountDecimalValidation.png");
			mandatory.clearFields("Discount");
		}

	}

	@Test(priority = 50)
	private void editmaxLimitDiscountField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Discount Field exceed its max-100 Limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteDiscoutnMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteDiscoutnMaximumValidation.png");
			mandatory.clearFields("Discount");
			mandatory.discountValidation("value");
		}

	}
	
	/*

	@Test(priority = 51)
	private void editafterDecimalPointTaxField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Field exceed its max-2 after decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteAfterDecimalPointTaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteAfterDecimalPointTaxValidation.png");
			mandatory.clearFields("Tax");
		}

	}

	@Test(priority = 52)
	private void editbeforeDecimalPointTaxField() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Tax Field exceed its max-3 before decimal point limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteBeforeTaxDecimalValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteBeforeTaxDecimalValidation.png");
			mandatory.clearFields("Tax");
		}

	}

	@Test(priority = 53)
	private void editmaxLimitTaxField() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Tax Field exceed its max-100 Limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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
			File file = new File("CustomerContactQuoteTaxMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteTaxMaximumValidation.png");
			mandatory.clearFields("Tax");
			mandatory.taxValidation("value");
		}

	}
	
	*/

	@Test(priority = 54)
	private void editmaximumValidationDescription() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-256 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.descriptionValidation("MaxCharacter");
		String errorPasswordField = mandatory.errorValidation("ErrorDescription");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20000Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max20000Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
			mandatory.descriptionValidation("value");
			mandatory.pickFirstItem("Contact");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
			mandatory.descriptionValidation("value");
			mandatory.pickFirstItem("Contact");
		}

	}

	@Test(priority = 55)
	private void editmaximumValidationNotes() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Note field exceed its max-20000 limit");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.notesField("MaxValidation");
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
			File file = new File("CustomerContactQuoteNotesMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteNotesMaximumValidation.png");
			mandatory.clearFields("Notes");
		}

	}

	@Test(priority = 56)
	private void editexpiryFieldPastDateValidation() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Expiry field enter the past date");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.dateValidation("PastDate");
		currentDate = mandatory.dateValidation("CurrentDateError");
		String errorPasswordField = mandatory.message("FormMessage");
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
			File file = new File("CustomerContactQuotePastDateValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuotePastDateValidation.png");
			mandatory.clearFields("Expiry");
		}

	}

	@Test(priority = 57)
	private void editvalidateCalculation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify to check the Line item calculation");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
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

	@Test(priority = 58)
	private void checkResponseCode() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Attacthment response code in customer contact quote module");
		QuotePage initElements = PageFactory.initElements(driver, QuotePage.class);
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

	@Test(priority = 58)
	private void updateQuote() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports.createTest("Verify Quote is updated successfully from Customer Contact->Edit Quote");
		QuotePage mandatory = PageFactory.initElements(driver, QuotePage.class);
		mandatory.CRUDValidation("Create");
		String errorPasswordField = mandatory.message("FormMessage");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("UpdatedMessage"))) {
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
	
	

	@Test(priority = 59)
	private void createdQuoteStatus() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Verify to Created Quote Status is Open, It's is displayed in the Quote List Page");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		String responseMessage = create.listTextValidation("ListStatus");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Open"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("Open"))) {
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

	
	//Draft Option is Removed for Customer Contact Quote - Date - 05/01/2024
	
	/*
	@Test(priority = 61)
	private void draftQuote() throws IOException, InterruptedException, ParseException, AWTException {
		extentTest = extentReports.createTest("Verify the Quote has been draft status");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		String responseMessage = create.listTextValidation("ListStatus");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Draft"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("Draft"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteDraftListStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteDraftListStatus.png");
		}

	}
	
	*/

	@Test(priority = 60)
	private void convertQuote() throws IOException, InterruptedException, ParseException, AWTException {
		extentTest = extentReports.createTest("Verify the Quote has been Convert status");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		//create.CRUDValidation("DraftEdit");
		create.CustomeracceptQuote();
		String responseMessage = create.listTextValidation("ListStatus");
		System.out.println(responseMessage);
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Convert"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("Convert"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteConvertStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteConvertStatus.png");
		}

	}

	static String QuoteListData;

	@Test(priority = 62)
	private void declinedStatus() throws IOException, InterruptedException, ParseException, AWTException {
		extentTest = extentReports.createTest("Verify the Quote has been Declined status");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		create.CRUDValidation("CreateDeclined");
		String responseMessage = create.listTextValidation("ListStatus");
		System.out.println(responseMessage);
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Declined"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("Declined"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.visible();
			QuoteListData = create.listTextValidation("QuoteNo");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteDeclinedStatus.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteDeclinedStatus.png");
			create.visible();
			QuoteListData = create.listTextValidation("QuoteNo");
		}

	}

	@Test(priority = 63)
	private void listQuoteNo() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Enter the Quote No:" + QuoteListData + "in the Search field & Quote list retrived successfully");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		create.listTextValidation("SearchData");
		String expected = create.listTextValidation("QuoteNo");
		System.out.println(expected);
		extentTest.log(Status.INFO, "Actual Result is -" + QuoteListData);
		extentTest.log(Status.INFO, "Expected Result is -" + expected);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (QuoteListData.equals(expected)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.clickEvent();
			QuoteListData = create.listTextValidation("Tittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListQuoteNoValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListQuoteNoValidation.png");
			create.clickEvent();
			QuoteListData = create.listTextValidation("Tittle");

		}

	}

	@Test(priority = 64)
	private void listQuoteTittle() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest(
				"Enter the Quote Tittle:" + QuoteListData + " in the Search field & Quote list retrived successfully");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		create.listTextValidation("SearchData");
		String expected = create.listTextValidation("Tittle");
		System.out.println(expected);
		extentTest.log(Status.INFO, "Actual Result is -" + QuoteListData);
		extentTest.log(Status.INFO, "Expected Result is -" + expected);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (QuoteListData.equals(expected)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.clickEvent();
			QuoteListData = create.listTextValidation("Reference");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListQuoteTittleValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListQuoteTittleValidation.png");
			create.clickEvent();
			QuoteListData = create.listTextValidation("Reference");
		}

	}

	@Test(priority = 65)
	private void listQuoteReference() throws IOException, InterruptedException, ParseException, AWTException {
		extentTest = extentReports.createTest("Enter the Quote Reference:" + QuoteListData
				+ " in the Search field & Quote list retrived successfully");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		create.RefreshPage();
		create.listTextValidation("SearchData");
		create.SearchReference();
		String expected = create.listTextValidation("Reference");
		System.out.println(expected);
		extentTest.log(Status.INFO, "Actual Result is -" + QuoteListData);
		extentTest.log(Status.INFO, "Expected Result is -" + expected);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (QuoteListData.equals(expected)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.clickEvent();
			//QuoteListData = create.dateValidation("ListCreateDate");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListQuoteReferenceValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListQuoteReferenceValidation.png");
			create.clickEvent();
			//QuoteListData = create.dateValidation("ListCreateDate");

		}

	}
	
	/*

	@Test(priority = 66)
	private void filterDateInvoice() throws IOException, InterruptedException, ParseException {
		extentTest = extentReports
				.createTest("Verify the Invoice Due Date List filter by Due From Date:" + QuoteListData);
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		create.dateValidation("Convertion");
		String dateValidation = create.dateValidation("ListCreateDate");
		extentTest.log(Status.INFO, "Actual Result is -" + QuoteListData);
		extentTest.log(Status.INFO, "Expected Result is -" + dateValidation);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (QuoteListData.equals(dateValidation)) {
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
	
	*/

	@Test(priority = 67)
	private void listInvalid() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		create.listTextValidation("Invalid");
		create.SearchReference();
		String actual = create.listTextValidation("InvalidList");
		System.out.println(actual);
		extentTest.log(Status.INFO, "Actual Result is -" + actual);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actual.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.reset();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListInvalidValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListInvalidValidation.png");
			create.reset();
		}

	}

	@Test(priority = 68)
	private void shareLinkResponseCOde() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify the user check the response code on the Share link page");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		create.ShareLink();
		create.attachmentFileCheck("NewWindow");
		int actual = create.responseCode();
		System.out.println(actual);
		extentTest.log(Status.INFO, "Actual Result is -" + actual);
		extentTest.log(Status.INFO, "Expected Result is -" + 200);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actual == 200) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.attachmentFileCheck("ParentWindow");
			create.cancelButton();
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListInvalidValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListInvalidValidation.png");
			create.attachmentFileCheck("ParentWindow");
			create.cancelButton();
		}
	}

	@Test(priority = 69)
	private void pdfLinkResponseCOde() throws IOException, InterruptedException, AWTException {
		extentTest = extentReports.createTest("Verify the user check the response code on the PDF link page");
		QuotePage create = PageFactory.initElements(driver, QuotePage.class);
		//create.listTextValidation("PDFPage");
		create.SharePDF();
		create.attachmentFileCheck("NewWindow");
		int actual = create.responseCode();
		System.out.println(actual);
		extentTest.log(Status.INFO, "Actual Result is -" + actual);
		extentTest.log(Status.INFO, "Expected Result is -" + 200);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actual == 200) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactQuoteListInvalidValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactQuoteListInvalidValidation.png");
		}
	}
	
	
}
