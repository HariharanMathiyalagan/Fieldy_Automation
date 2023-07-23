package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class TeamCompanyContractor extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("TeamCompanyContractor.html");
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

	@Test(priority = 1)
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

	@Test(priority = 2)
	public void createLabel() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Create Contractor Company Page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		edit.clickEvent("Navigate");
		String assertionMessage = edit.labelValidation();
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorCreatePage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("CompanyContractorCreatePage"))) {
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
	public void verifyContractorNameMandatory() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Check Company Contractor Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.clickEvent("SaveButton");
		String error_text = contractorPage.errorFields("CompanyName");
		extentTest.log(Status.INFO, "Actual Result is -" + error_text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (error_text.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.companyName("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("7.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("7.png");
			contractorPage.companyName("ValidData");
		}

	}

	@Test(priority = 7)
	public void verifyContractorNumberMinValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Company Contractor form]");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorPhoneNumber("MinValidation");
		String email_error = contractorPage.errorFields("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("8.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("8.png");
			contractorPage.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 8)
	public void verifyContractorEmailFormat() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Compamy Contractor form] invalid email is entered in Email Field");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorEmail("InValid");
		String email_error = contractorPage.errorFields("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("9.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("9.png");
			contractorPage.clearFields("Email");
		}

	}

	@Test(priority = 9)
	public void verifyContractorNumberMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Company Contractor Phone Number Field exceed its max-20 limit");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorPhoneNumber("MaxValidation");
		String email_error = contractorPage.errorFields("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("10.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("10.png");
			contractorPage.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 10)
	public void verifyFaxMinValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Fax field of [Company Contractor form]");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorFax("MinValidation");
		String fax_error = contractorPage.errorFields("Fax");
		extentTest.log(Status.INFO, "Actual Result is -" + fax_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (fax_error.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("Fax");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("11.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("11.png");
			contractorPage.clearFields("Fax");
		}

	}

	@Test(priority = 11)
	public void verifyFaxMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Company Contractor Fax Name Field exceed its max-20 limit");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorFax("MaxValidation");
		String fax_error = contractorPage.errorFields("Fax");
		extentTest.log(Status.INFO, "Actual Result is -" + fax_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (fax_error.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("Fax");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("12.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("12.png");
			contractorPage.clearFields("Fax");
		}
	}

	@Test(priority = 12)
	public void verifyCpersonFirstNameMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] First Name Field exceed its max-256 limit");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorFirstName("MaxValdation");
		String cperson_error = contractorPage.errorFields("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + cperson_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (cperson_error.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("FirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("13.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("13.png");
			contractorPage.clearFields("FirstName");
		}
	}

	@Test(priority = 13)
	public void verifyCpersonLastNameMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Last Name Field exceed its max-256 limit");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorLastName("MaxValidation");
		String cperson_error = contractorPage.errorFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + cperson_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (cperson_error.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("LastName");
			contractorPage.clickEvent("Next");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("13.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("13.png");
			contractorPage.clearFields("LastName");
			contractorPage.clickEvent("Next");
		}

	}

	@Test(priority = 14)
	private void maxValidationLocationNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Location Name Field exceed its max-256 limit");
		CreateContractorPage maxValidationLocationField = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidationLocationField.locationName("MaxValidation");
		String assertionMessage = maxValidationLocationField.errorFields("LocationName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("LocationName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("14.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("14.png");
			maxValidationLocationField.clearFields("LocationName");
		}

	}

	@Test(priority = 15)
	private void validateEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Compamy Contrctor Location Page] invalid email is entered in Email Field");
		CreateContractorPage validateEmail = PageFactory.initElements(driver, CreateContractorPage.class);
		validateEmail.locationEmail("InValid");
		String emailErrorMessage = validateEmail.errorFields("LocationEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + emailErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (emailErrorMessage.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validateEmail.clearFields("LocationEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("15.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("15.png");
			validateEmail.clearFields("LocationEmail");
		}

	}

	@Test(priority = 16)
	private void maxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Email Field exceed its max-256 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationEmail("MaxValidation");
		String emailErrorMessage = maxValidation.errorFields("LocationEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + emailErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (emailErrorMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("16.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("16.png");
			maxValidation.clearFields("LocationEmail");
		}

	}

	@Test(priority = 17)
	private void minValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Company Contractor Location Page]");
		CreateContractorPage minValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		minValidation.locationPhoneNumber("MinValidation");
		String phoneNumberErrorMessage = minValidation.errorFields("LocationPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + phoneNumberErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (phoneNumberErrorMessage.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("LocationPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("17.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("17.png");
			minValidation.clearFields("LocationPhoneNumber");
		}

	}

	@Test(priority = 18)
	private void maxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Location Phone Number Field exceed its max-20 limit");
		CreateContractorPage minValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		minValidation.locationPhoneNumber("MaxValidation");
		String phoneNumberErrorMessage = minValidation.errorFields("LocationPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + phoneNumberErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (phoneNumberErrorMessage.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("LocationPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("18.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("18.png");
			minValidation.clearFields("LocationPhoneNumber");
		}

	}

	@Test(priority = 19)
	private void maxValidationAddress1Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Address1 Field exceed its max-256 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationAddress1("MaxValidation");
		String errorAddress1Message = maxValidation.errorFields("LocationAddress1");
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationAddress1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("19.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("19.png");
			maxValidation.clearFields("LocationAddress1");
		}

	}

	@Test(priority = 20)
	private void maxValidationAddress2Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Address2 Field exceed its max-256 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationAddress2("MaxValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationAddress2");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationAddress2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("20.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("20.png");
			maxValidation.clearFields("LocationAddress2");
		}

	}

	@Test(priority = 21)
	private void maxValidationStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] State Name Field exceed its max-45 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationState("MaxValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationState");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationState");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("21.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("21.png");
			maxValidation.clearFields("LocationState");
		}

	}

	@Test(priority = 22)
	private void maxValidationCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] City Name Field exceed its max-256 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationCity("MaxValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationCity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationCity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("22.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("22.png");
			maxValidation.clearFields("LocationCity");
		}

	}

	@Test(priority = 23)
	private void maxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Zipcode Field exceed its max-10 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationZipcode("MaxValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationZipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("23.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("23.png");
			maxValidation.clearFields("LocationZipcode");
		}

	}

	@Test(priority = 24)
	private void minValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Company Contractor Location Page]");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationZipcode("MinValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationZipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("24.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("24.png");
			maxValidation.clearFields("LocationZipcode");
		}

	}

	@Test(priority = 25)
	private void characterValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the special charcter validation in Zipcode field");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationZipcode("SpecialCharacter");
		String errorAddress2Message = maxValidation.errorFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationZipcode");

		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("24.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("24.png");
			maxValidation.clearFields("LocationZipcode");

		}

	}

	@Test(priority = 26)
	private void maxValidationContactPerson() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Location Contact Person Name Field exceed its max-256 limit");
		CreateContractorPage maxContactPerson = PageFactory.initElements(driver, CreateContractorPage.class);
		maxContactPerson.locationContactPerson("MaxValidation");
		String errorContactPersonMessage = maxContactPerson.errorFields("LocationContactPerson");
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPersonMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max512CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPersonMessage.equals(getPropertyValue("Max512CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxContactPerson.clearFields("LocationContactPerson");
			maxContactPerson.clickEvent("Previous");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("25.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("25.png");
			maxContactPerson.clearFields("LocationContactPerson");
			maxContactPerson.clickEvent("Previous");
		}
	}

	@Test(priority = 27)
	public void verifySaveButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Contractor Company Save & Complete Button is displayed or Not");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		String text_button = contractorPage.clickEvent("SubmissionButton");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + "Save & Complete");
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals("Save & Complete")) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
		}

	}

	@Test(priority = 28)
	public void createContract() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the Contractor Company has Successfully Created");
		CreateContractorPage create = PageFactory.initElements(driver, CreateContractorPage.class);
		create.attachmentFileCheck("CompanyContractor");
		create.validData("BasicPage");
		create.validData("LocationPage");
		String asssertCreate = create.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals(getPropertyValue("CompanyContractorCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
			create.responseMessage("AlternateFunction");
		}
	}

	@Test(priority = 29)
	private void companiesContractorCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the Companies Contractor Created Count is added in the Total Contractor Count");
		CreateContractorPage create = PageFactory.initElements(driver, CreateContractorPage.class);
		int actualTotal = create.actualResult();
		int expectedResult = create.totalCount();
		extentTest.log(Status.INFO, "Actual Result is -" + actualTotal);
		extentTest.log(Status.INFO, "Expected Result is -" + expectedResult);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualTotal == expectedResult) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("CompaniesContractorCount.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("CompaniesContractorCount.png");
		}

	}

	@Test(priority = 30)
	public void contractotCompanyNameExistedValidation() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest(
				"Verify [Contractor Company Name Already Exists] ContractorCompany form, Error is dispalyed when already existing Contractor Company Name is provided");
		CreateContractorPage create = PageFactory.initElements(driver, CreateContractorPage.class);
		create.companyName("Unique");
		String asssertCreate = create.errorFields("CompanyName");
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ExistedCompanyName"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals(getPropertyValue("ExistedCompanyName"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.clickEvent("BackButton");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
			create.clickEvent("BackButton");
		}

	}

	static String listValidation;

	@Test(priority = 31)
	private void contractorAlreadyEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify [Email Already Exists] Contactor Company form, Error is dispalyed when already existing Email Name is provided");
		CreateContractorPage create = PageFactory.initElements(driver, CreateContractorPage.class);
		create.contractorEmail("Unique");
		String asssertCreate = create.errorFields("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			create.clickEvent("BackButton");
			listValidation = create.listValidation("ListCompanyName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
			create.clickEvent("BackButton");
			listValidation = create.listValidation("ListCompanyName");

		}

	}

	@Test(priority = 33)
	private void searchCompanyNameListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the Company Name:" + listValidation
				+ "in the Search field & Company Contractor list retrived successfully");
		CreateContractorPage listValidations = PageFactory.initElements(driver, CreateContractorPage.class);
		listValidations.listValidation("SearchData");
		String listCompanyName = listValidations.listValidation("ListCompanyName");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listCompanyName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidations.clearFields("Search");
			listValidation = listValidations.listValidation("ListName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("29.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("29.png");
			Thread.sleep(20000);
			listValidations.clearFields("Search");
			listValidation = listValidations.listValidation("ListName");
		}

	}

	@Test(priority = 34)
	private void searchNameListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Enter the Name:" + listValidation + "in the Search field & Name List reterived seccessfully");
		CreateContractorPage listValidations = PageFactory.initElements(driver, CreateContractorPage.class);
		listValidations.listValidation("SearchData");
		String listCompanyName = listValidations.listValidation("ListName");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listCompanyName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidations.clearFields("Search");
			listValidation = listValidations.listValidation("ListEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("30.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("30.png");
			Thread.sleep(20000);
			listValidations.clearFields("Search");
			listValidation = listValidations.listValidation("ListEmail");
		}

	}

	@Test(priority = 35)
	private void searchEmailListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Enter the Email:" + listValidation + "in the Search field & Email List reterived seccessfully");
		CreateContractorPage listValidations = PageFactory.initElements(driver, CreateContractorPage.class);
		listValidations.listValidation("SearchData");
		String listCompanyName = listValidations.listValidation("ListEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listCompanyName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidations.clearFields("Search");
			listValidation = listValidations.listValidation("ListPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("31.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("31.png");
			Thread.sleep(20000);
			listValidations.clearFields("Search");
			listValidation = listValidations.listValidation("ListPhoneNumber");
		}

	}

	@Test(priority = 36)
	private void searchPhoneNumberListValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the Phone Number:" + listValidation
				+ "in the Search field & Phone Number List reterived seccessfully");
		CreateContractorPage listValidations = PageFactory.initElements(driver, CreateContractorPage.class);
		listValidations.listValidation("SearchData");
		String listCompanyName = listValidations.listValidation("ListPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listCompanyName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listCompanyName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidations.clearFields("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("32.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("32.png");
			Thread.sleep(20000);
			listValidations.clearFields("Search");
		}

	}

	@Test(priority = 37)
	public void invalidValidationData() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		CreateContractorPage errorValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		errorValidation.listValidation("Invalid");
		String invlaidValidate = errorValidation.errorFields("Invalid");
		extentTest.log(Status.INFO, "Actual Result is -" + invlaidValidate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (invlaidValidate.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			errorValidation.clickEvent("Reset");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("33.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("33.png");
			Thread.sleep(20000);
			errorValidation.clickEvent("Reset");
		}

	}

	@Test(priority = 38)
	public void editLable() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Edit Contractor Company Page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		edit.clickEvent("Edit");
		String assertionMessage = edit.labelValidation();
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorEditPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("CompanyContractorEditPage"))) {
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

	@Test(priority = 39)
	public void contractorNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Company  Name:" + CreateContractorPage.ContractorName
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("CompanyName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorName)) {
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

	@Test(priority = 40)
	public void firstNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the First Name:" + CreateContractorPage.ContractorFirstName
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorFirstName)) {
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

	@Test(priority = 41)
	public void lastNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Last Name:" + CreateContractorPage.ContractorLastName
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLastName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLastName)) {
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

	@Test(priority = 42)
	public void emailPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Email:" + CreateContractorPage.ContractorEmail
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorEmail);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorEmail)) {
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

	@Test(priority = 43)
	public void faxPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Fax Number:" + CreateContractorPage.ContractorFaxNumber
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("Fax");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorFaxNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorFaxNumber)) {
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

	@Test(priority = 44)
	public void phoneNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Phone Number:" + CreateContractorPage.ContractorPhoneNumber
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorPhoneNumber)) {
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

	@Test(priority = 45)
	public void websitePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Website:" + CreateContractorPage.ContractorWebSite
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("Website");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorWebSite);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorWebSite)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			edit.clickEvent("Next");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
			edit.clickEvent("Next");
		}

	}

	@Test(priority = 46)
	public void locationNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Location Name:" + CreateContractorPage.ContractorLocationName
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationName)) {
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

	@Test(priority = 47)
	public void locationEmailPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Location Name Email:"
				+ CreateContractorPage.ContractorLocationEmail + " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationEmail);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationEmail)) {
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

	@Test(priority = 48)
	public void locationContactPersonPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Location Contact Person:" + CreateContractorPage.ContractorLocationContactPerson
						+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationContactPerson");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationContactPerson);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationContactPerson)) {
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

	@Test(priority = 49)
	public void locationPhoneNumberPersonPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Location Phone Number:" + CreateContractorPage.ContractorLocationPhoneNumber
						+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationPhoneNumber)) {
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

	@Test(priority = 50)
	public void locationAddress1Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Location Address1:" + CreateContractorPage.ContractorLocationAddress1
						+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationAddress1");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationAddress1);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationAddress1)) {
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

	@Test(priority = 51)
	public void locationAddress2Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Location Address2:" + CreateContractorPage.ContractorLocationAddress2
						+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationAddress2");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationAddress2);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationAddress2)) {
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

	@Test(priority = 52)
	public void locationCityPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Location City:" + CreateContractorPage.ContractorLocationCity
				+ " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationCity");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationCity);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationCity)) {
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

	@Test(priority = 53)
	public void locationStatePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Location State:"
				+ CreateContractorPage.ContractorLocationState + " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationState");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationState);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationState)) {
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

	@Test(priority = 54)
	public void locationZipcodePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Location Zipcode:"
				+ CreateContractorPage.ContractorLocationZipcode + " is prepopulated in the contractor edit form page");
		CreateContractorPage edit = PageFactory.initElements(driver, CreateContractorPage.class);
		String assertionMessage = edit.prepopulationFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + CreateContractorPage.ContractorLocationZipcode);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(CreateContractorPage.ContractorLocationZipcode)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			edit.clickEvent("Previous");
			edit.clearAllFields("Basic");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("EditCompanyLabel.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("EditCompanyLabel.png");
			edit.clickEvent("Previous");
			edit.clearAllFields("Basic");
		}

	}

	@Test(priority = 55)
	public void editverifyContractorNameMandatory() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Check Company Contractor Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.clickEvent("SaveButton");
		String error_text = contractorPage.errorFields("CompanyName");
		extentTest.log(Status.INFO, "Actual Result is -" + error_text);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (error_text.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.companyName("ValidData");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("7.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("7.png");
			contractorPage.companyName("ValidData");
		}

	}

	@Test(priority = 56)
	public void editverifyContractorNumberMinValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Company Contractor form]");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorPhoneNumber("MinValidation");
		String email_error = contractorPage.errorFields("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("8.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("8.png");
			contractorPage.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 57)
	public void editverifyContractorEmailFormat() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Compamy Contrctor form] invalid email is entered in Email Field");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorEmail("InValid");
		String email_error = contractorPage.errorFields("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("9.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("9.png");
			contractorPage.clearFields("Email");
		}

	}

	@Test(priority = 58)
	public void editverifyContractorNumberMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Company Contractor Phone Number Field exceed its max-20 limit");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorPhoneNumber("MaxValidation");
		String email_error = contractorPage.errorFields("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + email_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (email_error.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("10.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("10.png");
			contractorPage.clearFields("PhoneNumber");
		}

	}

	@Test(priority = 59)
	public void editverifyFaxMinValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Fax field of [Company Contractor form]");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorFax("MinValidation");
		String fax_error = contractorPage.errorFields("Fax");
		extentTest.log(Status.INFO, "Actual Result is -" + fax_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (fax_error.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("Fax");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("11.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("11.png");
			contractorPage.clearFields("Fax");
		}

	}

	@Test(priority = 60)
	public void editverifyFaxMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when Company Contractor Fax Name Field exceed its max-20 limit");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorFax("MaxValidation");
		String fax_error = contractorPage.errorFields("Fax");
		extentTest.log(Status.INFO, "Actual Result is -" + fax_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (fax_error.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("Fax");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("12.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("12.png");
			contractorPage.clearFields("Fax");
		}
	}

	@Test(priority = 61)
	public void editverifyCpersonFirstNameMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] First Name Field exceed its max-256 limit");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorFirstName("MaxValdation");
		String cperson_error = contractorPage.errorFields("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + cperson_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (cperson_error.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("FirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("13.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("13.png");
			contractorPage.clearFields("FirstName");
		}

	}

	@Test(priority = 62)
	public void editverifyCpersonLastNameMaxValidation() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Last Name Field exceed its max-256 limit");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		contractorPage.contractorLastName("MaxValidation");
		String cperson_error = contractorPage.errorFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + cperson_error);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (cperson_error.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			contractorPage.clearFields("LastName");
			contractorPage.clickEvent("Next");
			contractorPage.clearAllFields("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("13.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("13.png");
			contractorPage.clearFields("LastName");
			contractorPage.clickEvent("Next");
			contractorPage.clearAllFields("Location");
		}

	}

	@Test(priority = 63)
	private void editmaxValidationLocationNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Location Name Field exceed its max-256 limit");
		CreateContractorPage maxValidationLocationField = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidationLocationField.locationName("MaxValidation");
		String assertionMessage = maxValidationLocationField.errorFields("LocationName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidationLocationField.clearFields("LocationName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("14.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("14.png");
			maxValidationLocationField.clearFields("LocationName");
		}

	}

	@Test(priority = 64)
	private void editvalidateEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Compamy Contrctor Location Page] invalid email is entered in Email Field");
		CreateContractorPage validateEmail = PageFactory.initElements(driver, CreateContractorPage.class);
		validateEmail.locationEmail("InValid");
		String emailErrorMessage = validateEmail.errorFields("LocationEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + emailErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (emailErrorMessage.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			validateEmail.clearFields("LocationEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("15.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("15.png");
			validateEmail.clearFields("LocationEmail");
		}

	}

	@Test(priority = 65)
	private void editmaxValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Email Field exceed its max-256 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationEmail("MaxValidation");
		String emailErrorMessage = maxValidation.errorFields("LocationEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + emailErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (emailErrorMessage.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("16.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("16.png");
			maxValidation.clearFields("LocationEmail");
		}

	}

	@Test(priority = 66)
	private void editminValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Company Contractor Location Page]");
		CreateContractorPage minValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		minValidation.locationPhoneNumber("MinValidation");
		String phoneNumberErrorMessage = minValidation.errorFields("LocationPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + phoneNumberErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (phoneNumberErrorMessage.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("LocationPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("17.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("17.png");
			minValidation.clearFields("LocationPhoneNumber");
		}

	}

	@Test(priority = 67)
	private void editmaxValidationPhoneNumberField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Location Phone Number Field exceed its max-20 limit");
		CreateContractorPage minValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		minValidation.locationPhoneNumber("MaxValidation");
		String phoneNumberErrorMessage = minValidation.errorFields("LocationPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + phoneNumberErrorMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (phoneNumberErrorMessage.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearFields("LocationPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("18.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("18.png");
			minValidation.clearFields("LocationPhoneNumber");
		}

	}

	@Test(priority = 68)
	private void editmaxValidationAddress1Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Address1 Field exceed its max-256 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationAddress1("MaxValidation");
		String errorAddress1Message = maxValidation.errorFields("LocationAddress1");
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress1Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationAddress1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("19.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("19.png");
			maxValidation.clearFields("LocationAddress1");
		}

	}

	@Test(priority = 69)
	private void editmaxValidationAddress2Field() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Address2 Field exceed its max-256 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationAddress2("MaxValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationAddress2");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationAddress2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("20.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("20.png");
			maxValidation.clearFields("LocationAddress2");
		}

	}

	@Test(priority = 70)
	private void editmaxValidationStateField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] State Name Field exceed its max-45 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationState("MaxValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationState");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationState");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("21.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("21.png");
			maxValidation.clearFields("LocationState");
		}

	}

	@Test(priority = 71)
	private void editmaxValidationCityField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] City Name Field exceed its max-256 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationCity("MaxValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationCity");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationCity");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("22.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("22.png");
			maxValidation.clearFields("LocationCity");
		}

	}

	@Test(priority = 72)
	private void editmaxValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Zipcode Field exceed its max-10 limit");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationZipcode("MaxValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationZipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("23.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("23.png");
			maxValidation.clearFields("LocationZipcode");
		}

	}

	@Test(priority = 73)
	private void editminValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Company Contractor Location Page]");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationZipcode("MinValidation");
		String errorAddress2Message = maxValidation.errorFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationZipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("24.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("24.png");
			maxValidation.clearFields("LocationZipcode");
		}

	}

	@Test(priority = 74)
	private void editcharacterValidationZipcodeField() throws IOException {
		extentTest = extentReports.createTest("Verify the special charcter validation in Zipcode field");
		CreateContractorPage maxValidation = PageFactory.initElements(driver, CreateContractorPage.class);
		maxValidation.locationZipcode("SpecialCharacter");
		String errorAddress2Message = maxValidation.errorFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + errorAddress2Message);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorAddress2Message.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxValidation.clearFields("LocationZipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("24.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("24.png");
			maxValidation.clearFields("LocationZipcode");

		}

	}

	@Test(priority = 75)
	private void editmaxValidationContactPerson() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Contractor Company] Location Contact Person Name Field exceed its max-256 limit");
		CreateContractorPage maxContactPerson = PageFactory.initElements(driver, CreateContractorPage.class);
		maxContactPerson.locationContactPerson("MaxValidation");
		String errorContactPersonMessage = maxContactPerson.errorFields("LocationContactPerson");
		extentTest.log(Status.INFO, "Actual Result is -" + errorContactPersonMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max512CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorContactPersonMessage.equals(getPropertyValue("Max512CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			maxContactPerson.clearFields("LocationContactPerson");
			maxContactPerson.clickEvent("Previous");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("25.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("25.png");
			maxContactPerson.clearFields("LocationContactPerson");
			maxContactPerson.clickEvent("Previous");
		}
	}

	@Test(priority = 76)
	public void verifyUpdateButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Contractor Company Update Button is displayed or Not");
		CreateContractorPage contractorPage = PageFactory.initElements(driver, CreateContractorPage.class);
		String text_button = contractorPage.clickEvent("SubmissionButton");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("UpdatedButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
		}

	}

	@Test(priority = 77)
	public void UpdateContract() throws InterruptedException, IOException, AWTException {
		extentTest = extentReports.createTest("Verify the Contractor Company has Successfully Updated");
		CreateContractorPage create = PageFactory.initElements(driver, CreateContractorPage.class);
		create.validData("BasicPage");
		create.validData("LocationPage");
		String asssertCreate = create.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + asssertCreate);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("CompanyContractorUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (asssertCreate.equals(getPropertyValue("CompanyContractorUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("27.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("27.png");
			create.responseMessage("AlternateFunction");
		}

	}

	@Test(priority = 78)
	private void deleteContractorDetails() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the User to Delete the Contractor Details and Successfully Deleted");
		CreateContractorPage companiesPage = PageFactory.initElements(driver, CreateContractorPage.class);
		companiesPage.clickEvent("Delete");
		String deleteFunction = companiesPage.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + deleteFunction);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ComapanyContractorDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (deleteFunction.equals(getPropertyValue("ComapanyContractorDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			companiesPage.dataConditionCheck("Condition");
			companiesPage.dataConditionCheck("AlternateFunction");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("36.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("36.png");
			companiesPage.dataConditionCheck("Condition");
			companiesPage.dataConditionCheck("AlternateFunction");
		}

	}

}
