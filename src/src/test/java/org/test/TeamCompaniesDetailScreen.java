package org.test;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.EditDetailScreenCompaniesPage;
import com.zaigo.pageobjects.JobPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.utility.BrowserSetup;

public class TeamCompaniesDetailScreen extends BaseClass {

	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("TeamCompaniesDetailScreen.html");
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

	@Test(priority = 0)
	public void verify() throws IOException {
		// Single Account User
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password", getPropertyValue("Enviromment")));
		loginInPage.clickLoginButton();
		String text = loginInPage.dashBoardText();
		extentTest.log(Status.INFO, "Actual Result is -" + text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidationOfLandingPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text.equals(getPropertyValue("ValidationOfLandingPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("1.png");
		}
	}

	@Test(priority = 1)
	public void teamModule() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Details Screen Tenant Name");
		EditDetailScreenCompaniesPage module = new EditDetailScreenCompaniesPage(driver);
		module.modulePage();
		String assertTittle = module.assertTittle();
		extentTest.log(Status.INFO, "Actual Result is -" + assertTittle);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValueUpdate("BussinessName"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertTittle.equals(getPropertyValueUpdate("BussinessName"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("2.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("2.png");
		}

	}

	@Test(priority = 2)
	public void editLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Company Page");
		EditDetailScreenCompaniesPage edit = PageFactory.initElements(driver, EditDetailScreenCompaniesPage.class);
		String assertionMessage = edit.editContent();
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("EditCompanyLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("EditCompanyLabel"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
		}

	}

	@Test(priority = 3)
	private void maxValidationCompanyWebsiteField() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when [Company] Website Field exceed its max-2048 limit");
		EditDetailScreenCompaniesPage maxValidationLocationField = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidationLocationField.companyWebsite("MaxValidation");
		String assertionMessage = maxValidationLocationField.errorMessage();
//		String assertionMessage = maxValidationLocationField.errorFields("CompanyWebsite");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("CompanyWebsite");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyWebsiteMaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyWebsiteMaxValidation.png");
			maxValidationLocationField.clearFields("CompanyWebsite");
		}

	}

	@Test(priority = 4)
	private void maxValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Company] First Name Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxValidationLocationField = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidationLocationField.firstName("MaxValidation");
		String assertionMessage = maxValidationLocationField.errorMessage();
//		String assertionMessage = maxValidationLocationField.errorFields("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("FirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyFirstNameMaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyFirstNameMaxValidation.png");
			maxValidationLocationField.clearFields("FirstName");
		}

	}

	@Test(priority = 5)
	private void maxValidationLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Company] Last Name Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxValidationLocationField = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidationLocationField.lastName("MaxValidation");
		String assertionMessage = maxValidationLocationField.errorMessage();
//		String assertionMessage = maxValidationLocationField.errorFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("LastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLastNameMaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLastNameMaxValidation.png");
			maxValidationLocationField.clearFields("LastName");
		}

	}

	@Test(priority = 6)
	private void maxValidationTaxNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Company] Tax Number Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxValidationLocationField = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidationLocationField.taxNumber("MaxValidation");
		String assertionMessage = maxValidationLocationField.errorMessage();
//		String assertionMessage = maxValidationLocationField.errorFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("TaxNumber");
			maxValidationLocationField.fieldsFillData("BasicPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLastNameMaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLastNameMaxValidation.png");
			maxValidationLocationField.clearFields("TaxNumber");
			maxValidationLocationField.fieldsFillData("BasicPage");
		}
	}

	@Test(priority = 7)
	private void maxValidationCompanyLocationNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Company] Location Name Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxValidationLocationField = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidationLocationField.clickAddMore();
		maxValidationLocationField.locationName("MaxValidation");
//		String assertionMessage = maxValidationLocationField.errorFields("Location");
		String assertionMessage = maxValidationLocationField.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLocationMaxValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLocationMaxValidation.png");
			maxValidationLocationField.clearFields("Location");
		}

	}

	@Test(priority = 8)
	private void validateCompanyEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Compamy  Location Page] invalid email is entered in Email Field");
		EditDetailScreenCompaniesPage validateEmail = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		validateEmail.email("InvalidValidEmail");
		String emailErrorMessage = validateEmail.errorMessage();
//		String emailErrorMessage = validateEmail.errorFields("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + emailErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (emailErrorMessage.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validateEmail.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyValidEmail.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyValidEmail.png");
			validateEmail.clearFields("Email");
		}

	}

	@Test(priority = 9)
	private void maxValidationCompanyEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Company Location Page] Email Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidation.email("MaxValidation");
//		String emailErrorMessage = maxValidation.errorFields("Email");
		String emailErrorMessage = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + emailErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (emailErrorMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMaxEmail.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMaxEmail.png");
			maxValidation.clearFields("Email");
		}

	}

	@Test(priority = 10)
	private void minValidationCompanyPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Company Location Page]");
		EditDetailScreenCompaniesPage minValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		minValidation.PhoneNumber("MinValidation");
//		String phoneNumberErrorMessage = minValidation.errorFields("PhoneNumber");
		String phoneNumberErrorMessage = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + phoneNumberErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (phoneNumberErrorMessage.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMinPhoneNumber.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMinPhoneNumber.png");
			minValidation.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 11)
	private void maxValidationCompanyPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Company] Location Phone Number Field exceed its max-20 limit");
		EditDetailScreenCompaniesPage minValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		minValidation.PhoneNumber("MaxValidation");
//		String phoneNumberErrorMessage = minValidation.errorFields("PhoneNumber");
		String phoneNumberErrorMessage = minValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + phoneNumberErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (phoneNumberErrorMessage.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMaxPhoneNumber.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMaxPhoneNumber.png");
			minValidation.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 12)
	private void maxValidationCompanyAddress1Field() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when [Company] Address1 Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidation.address1("MaxValidation");
//		String errorAddress1Message = maxValidation.errorFields("Address1");
		String errorAddress1Message = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actuals Result is -" + errorAddress1Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Address1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMaxAddress1.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMaxAddress1.png");
			maxValidation.clearFields("Address1");
		}

	}

	@Test(priority = 13)
	private void maxValidationCompanyAddress2Field() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when [Company] Address2 Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidation.address2("MaxValidation");
//		String errorAddress2Message = maxValidation.errorFields("Address2");
		String errorAddress2Message = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Address2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMaxAddress2.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMaxAddress2.png");
			maxValidation.clearFields("Address2");
		}

	}

	@Test(priority = 14)
	private void maxValidationCompanyStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Company] State Name Field exceed its max-45 limit");
		EditDetailScreenCompaniesPage maxValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidation.state("MaxValidation");
//		String errorAddress2Message = maxValidation.errorFields("State");
		String errorAddress2Message = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("State");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMaxState.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMaxState.png");
			maxValidation.clearFields("State");
		}

	}

	@Test(priority = 15)
	private void maxValidationCompanyCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] City Name Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidation.city("MaxValidation");
//		String errorAddress2Message = maxValidation.errorFields("City");
		String errorAddress2Message = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("City");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMaxCity.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMaxCity.png");
			maxValidation.clearFields("City");
		}

	}

	@Test(priority = 16)
	private void maxValidationCompanyZipcodeField() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when [Company] Zipcode Field exceed its max-10 limit");
		EditDetailScreenCompaniesPage maxValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidation.zipcode("MaxValidation");
//		String errorAddress2Message = maxValidation.errorFields("Zipcode");
		String errorAddress2Message = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMaxZipcode.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMaxZipcode.png");
			maxValidation.clearFields("Zipcode");
		}

	}

	@Test(priority = 17)
	private void minValidationCompanyZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Company Location Page]");
		EditDetailScreenCompaniesPage maxValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidation.zipcode("MinValidation");
//		String errorAddress2Message = maxValidation.errorFields("Zipcode");
		String errorAddress2Message = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMinZipcode.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMinZipcode.png");
			maxValidation.clearFields("Zipcode");
		}

	}

	@Test(priority = 18)
	private void characterValidationCompanyZipcodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the special charcter validation in Zipcode field");
		EditDetailScreenCompaniesPage maxValidation = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxValidation.zipcode("SpecialCharacter");
//		String errorAddress2Message = maxValidation.errorFields("Zipcode");
		String errorAddress2Message = maxValidation.errorMessage();
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("Zipcode");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanySpecialCharacterZipcode.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanySpecialCharacterZipcode.png");
			maxValidation.clearFields("Zipcode");

		}

	}

	@Test(priority = 19)
	private void maxValidationCompanyContactPerson() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Location Contact Person Name Field exceed its max-256 limit");
		EditDetailScreenCompaniesPage maxContactPerson = PageFactory.initElements(driver,
				EditDetailScreenCompaniesPage.class);
		maxContactPerson.contactPerson("MaxValidation");
		String errorContactPersonMessage = maxContactPerson.errorMessage();
//		String errorContactPersonMessage = maxContactPerson.errorFields("ContactPerson");
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPersonMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max512CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPersonMessage.equals(getPropertyValue("Max512CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxContactPerson.clearFields("ContactPerson");
			maxContactPerson.fieldsFillData("LocationPage");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyMaxValidationContactPerson.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyMaxValidationContactPerson.png");
			maxContactPerson.clearFields("ContactPerson");
			maxContactPerson.fieldsFillData("LocationPage");
		}
	}

	@Test(priority = 20)
	private void maximumValidationDescription() throws IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when Description field exceed its max-2048 limit");
		EditDetailScreenCompaniesPage mandatory = PageFactory.initElements(driver, EditDetailScreenCompaniesPage.class);
		mandatory.description("MaxValidation");
		String errorPasswordField = mandatory.errorMessage();
//		String errorPasswordField = mandatory.errorFields("Description");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPasswordField);
		extentTest.log(Status.INFO, "Expected Expected  Result is -" + getPropertyValue("Max2048Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPasswordField.equals(getPropertyValue("Max2048Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			mandatory.clearFields("Description");
			mandatory.fieldsFillData("Description");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CustomerContactJobDescriptionMaximumValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CustomerContactJobDescriptionMaximumValidation.png");
			mandatory.clearFields("Description");
			mandatory.fieldsFillData("Description");
		}

	}

	@Test(priority = 20)
	private void responseMessage() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify updated successful message is displayed, when the Compnay Details Updated");
		EditDetailScreenCompaniesPage add = new EditDetailScreenCompaniesPage(driver);
		String responseMessage = add.responseMessages("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedTenantMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("UpdatedTenantMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CompanyUpdated.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CompanyUpdated.png");
		}
	}

	@Test(priority = 21)
	private void deleteLocation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify deleted successful message is displayed, when the Company Location Deleted");
		EditDetailScreenCompaniesPage delete = new EditDetailScreenCompaniesPage(driver);
		delete.deleteLocation();
		String deleteMessage = delete.responseMessages("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + deleteMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TenantLocationDeleteMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (deleteMessage.equals(getPropertyValue("TenantLocationDeleteMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CompanyLocationDeleted.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CompanyLocationDeleted.png");
		}
	}
}
