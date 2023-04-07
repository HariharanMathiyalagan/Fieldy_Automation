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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.base.BaseClass;
import com.zaigo.pageobjects.TeamUserPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.utility.BrowserSetup;

public class TeamUserContractor extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String listValidation;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("TeamUserContractor.html");
		extentReports.attachReporter(extentHtmlReporter);
		this.driver = BrowserSetup.startBrowser();

	}

	@AfterClass
	public void exitBrowser() {
		this.driver.quit();
		this.extentReports.flush();
	}

	@Test(priority = 0)
	public void verify() throws IOException {
		// Single Account User
		extentTest = extentReports.createTest(
				"Verify the Fieldy Login Page to Validate the Valid Email & Valid Password and Land on the Fieldy Home Page");
		LoginPage loginInPage = new LoginPage(this.driver);
		loginInPage.userField(getPropertyValueUpdate("UserName"));
		loginInPage.passwordField(getPropertyValue("Password"));
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
	public void TeamUserPage() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the User to Land on the Team User Contractor Contractor List Page & Validate the Label");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		edit.clickEvent("NavigateContractor");
		String assertionMessage = edit.labelValidation("ListLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamContractorListLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("TeamContractorListLabel"))) {
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

	@Test(priority = 2)
	private void landingOnCreateForm() throws IOException {
		extentTest = extentReports.createTest(
				"Verify the User to Land on Team User Contractor Contractor Create Page & Validate the Label");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String labelValidation = landing.labelValidation("ContractorLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + labelValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamCreateContractorPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (labelValidation.equals(getPropertyValue("TeamCreateContractorPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("65.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("65.png");
		}

	}

	@Test(priority = 3)
	private void mandatoryValidationContractorFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Contractor creation form page, First Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("OrganizationRadioButton");
		String mandatoryValidationFirstNameField = landing.errorField("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("71.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("71.png");
		}

	}

	@Test(priority = 4)
	private void mandatoryValidationContractorEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Contractor creation form page, Email field is set as Mandatory & Error Message is displayed when it is BLANK");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String mandatoryValidationFirstNameField = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("72.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("72.png");
		}
	}

	@Test(priority = 5)
	private void mandatoryOrganizationName() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Check the User Contractor creation form page, Company Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String mandatoryValidationFirstNameField = landing.errorField("CompanyName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("73.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("73.png");
		}

	}

	@Test(priority = 6)
	private void maxValidationContractorFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor Contractor] First Name Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.firstName("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("FirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("74.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("74.png");
			landing.clearField("FirstName");
		}
	}

	@Test(priority = 7)
	private void maxValidationContractorLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor Contractor] Last Name Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.lastName("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("LastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("75.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("75.png");
			landing.clearField("LastName");
		}

	}

	@Test(priority = 8)
	private void invalidContractorEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Team User Contractor Contrctor form] invalid email is entered in Email Field");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.email("InValid");
		String mandatoryValidationFirstNameField = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("76.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("76.png");
			landing.clearField("Email");
		}

	}

	@Test(priority = 9)
	private void maxValidationContractorEmailFields() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor Contractor] Email Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.email("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("77.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("77.png");
			landing.clearField("Email");
		}

	}

	@Test(priority = 10)
	private void minValidatonContractorPhoneNumber() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Team User Contractor Contactor form]");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.phoneNumber("MinValidation");
		String mandatoryValidationFirstNameField = landing.errorField("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("78.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("78.png");
			landing.clearField("PhoneNumber");

		}

	}

	@Test(priority = 11)
	private void specialCharacterPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when special character provided in Phone Number field");
		TeamUserPage minValidation = PageFactory.initElements(driver, TeamUserPage.class);
		minValidation.phoneNumber("SpecialCharacter");
		String errorPhoneNumber = minValidation.errorField("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterPhoneNumber"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("SpecialCharacterPhoneNumber"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearField("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearField("PhoneNumber");
		}

	}

	@Test(priority = 12)
	private void maxValidatonContractorPhoneNumber() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor Contractor] Phone Number Field exceed its max-20 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.phoneNumber("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("PhoneNumber");
			landing.validateFillData("BasicContractor");
			landing.validateFillData("ContractorCompany");
			landing.clickEvent("Next");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("79.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("79.png");
			landing.clearField("PhoneNumber");
			landing.validateFillData("BasicContractor");
			landing.validateFillData("ContractorCompany");
			landing.clickEvent("Next");
		}
	}

	@Test(priority = 14)
	private void maxValidationLocationName() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Location Name Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.locationName("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("LocationName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("LocationName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("50.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("50.png");
			landing.clearField("LocationName");
		}

	}

	@Test(priority = 15)
	private void maxValidationAddress1() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Address1 Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.address1("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Address1");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Address1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("51.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("51.png");
			landing.clearField("Address1");
		}

	}

	@Test(priority = 16)
	private void maxValidationAddress2() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Address2 Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.address2("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Address2");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Address2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("52.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("52.png");
			landing.clearField("Address2");
		}

	}

	@Test(priority = 17)
	private void maxValidationState() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] State Name Field exceed its max-45 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.state("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("State");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("State");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("53.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("53.png");
			landing.clearField("State");
		}

	}

	@Test(priority = 18)
	private void maxValidationCity() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] City Name Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.city("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("City");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("City");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("54.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("54.png");
			landing.clearField("City");
		}

	}

	@Test(priority = 19)
	private void minValidationZipcode() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Team User Contractor form]");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.zipcode("MinValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Zipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("55.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("55.png");
			landing.clearField("Zipcode");
		}

	}

	@Test(priority = 20)
	private void specialCharacterValidationZipcode() throws IOException {
		extentTest = extentReports.createTest("Verify the Special Character Validation Zipcode Field");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.zipcode("SpecialCharacter");
		String mandatoryValidationFirstNameField = landing.errorField("Zipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("56.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("56.png");
			landing.clearField("Zipcode");
		}

	}

	@Test(priority = 21)
	private void maxValidationZipcode() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Zipcode Field exceed its max-10 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.zipcode("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Zipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Zipcode");
			landing.validateFillData("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("57.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("57.png");
			landing.clearField("Zipcode");
			landing.validateFillData("Location");
		}

	}

	@Test(priority = 27)
	public void verifySaveButtonExist() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Team User Contractor Save & Complete Button is displayed in the Create form page");
		TeamUserPage contractorPage = PageFactory.initElements(driver, TeamUserPage.class);
		String text_button = contractorPage.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SaveButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("SaveButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
			contractorPage.clickEvent("SaveUpdate");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
			contractorPage.clickEvent("SaveUpdate");
		}

	}

	@Test(priority = 28)
	private void userCreate() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify a new User is created successfully through [Team User]");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String createMessage = landing.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContractorCreatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("ContractorCreatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.responseMessage("AlternateFunction");

		}

	}

	@Test(priority = 29)
	private void userCount() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify the User Contractor Created Count is added in the User Admin Count");
		TeamUserPage create = PageFactory.initElements(driver, TeamUserPage.class);
		String actualTotal = create.actualResult("Contractor");
		String expectedResult = create.totalCount("Contractor");
		extentTest.log(Status.INFO, "Actual Result is - Total Contractor Count:" + actualTotal);
		extentTest.log(Status.INFO, "Expected Result is - Total Contractor Count:" + expectedResult);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (actualTotal.equals(expectedResult)) {
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
	private void alreadyExistedMailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify [Email Already Exists] User form, Error is dispalyed when already existing Email is provided");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.email("UniqueContractor");
		String createUserList = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + createUserList);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createUserList.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clickEvent("ContractorBack");
			listValidation = landing.listValidation("FirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("60.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("60.png");
			landing.clickEvent("ContractorBack");
			listValidation = landing.listValidation("FirstName");
		}
	}

	@Test(priority = 31)
	private void searchNameValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the User Contractor Name:" + listValidation
				+ "in the Search field & User Contractor Name list retrived successfully");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.listValidation("ContractorSearchData");
		String listFirstName = landing.listValidation("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("ContractorSearch");
			listValidation = landing.listValidation("ContractorPhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("61.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("61.png");
			Thread.sleep(20000);
			landing.clearField("ContractorSearch");
			listValidation = landing.listValidation("ContractorPhoneNumber");
		}

	}

	@Test(priority = 32)
	private void searchPhoneNumberValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the User Contractor Phone Number:" + listValidation
				+ "in the Search field & User Contractor Phone Number list retrived successfully");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.listValidation("ContractorSearchData");
		String listPhoneNumber = landing.listValidation("ContractorPhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("ContractorSearch");
			listValidation = landing.listValidation("ContractorListEmail");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("62.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("62.png");
			Thread.sleep(20000);
			landing.clearField("ContractorSearch");
			listValidation = landing.listValidation("ContractorListEmail");
		}

	}

	@Test(priority = 33)
	private void searchEmailValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the User Contractor Email:" + listValidation
				+ "in the Search field & User Contractor Email list retrived successfully");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.listValidation("ContractorSearchData");
		String listPhoneNumber = landing.listValidation("ContractorListEmail");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			listValidation = landing.listValidation("ContractorListCompany");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("63.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("63.png");
			Thread.sleep(20000);
			listValidation = landing.listValidation("ContractorListCompany");
		}

	}

	@Test(priority = 34)
	private void filterByCompany() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Verify the User to Select the Contractor Company:" + listValidation
				+ "in the Company Filter & User Contractor Company list retrived successfully");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String filterCompanySearch = landing.listValidation("FilterCompany");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + filterCompanySearch);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(filterCompanySearch)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("ContractorSearch");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("94.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("94.png");
			Thread.sleep(20000);
			landing.clearField("ContractorSearch");
		}

	}

	@Test(priority = 35)
	private void invalidDataValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.listValidation("ContractorInvalid");
		String errorField = landing.errorField("Invalid");
		extentTest.log(Status.INFO, "Actual Result is -" + errorField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("InvalidSearch"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorField.equals(getPropertyValue("InvalidSearch"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clickEvent("Reset");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("64.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("64.png");
			Thread.sleep(20000);
			landing.clickEvent("Reset");
		}
	}

	@Test(priority = 36)
	private void landingOnEditForm() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Land on Team Edit Page and Validate the Label");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("ContractorEdit");
		String labelValidation = landing.labelValidation("ContractorLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + labelValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamEditContractorPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (labelValidation.equals(getPropertyValue("TeamEditContractorPage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearAllFields("ContractorBasic");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("65.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("65.png");
			landing.clearAllFields("ContractorBasic");
		}

	}

	@Test(priority = 37)
	private void editmandatoryValidationContractorFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Contractor creation form page, First Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("OrganizationRadioButton");
		String mandatoryValidationFirstNameField = landing.errorField("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("71.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("71.png");
		}

	}

	@Test(priority = 38)
	private void editmandatoryValidationContractorEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Contractor creation form page, Email field is set as Mandatory & Error Message is displayed when it is BLANK");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String mandatoryValidationFirstNameField = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("72.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("72.png");
		}
	}

	@Test(priority = 40)
	private void editmaxValidationContractorFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor Contractor] First Name Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.firstName("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("FirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("74.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("74.png");
			landing.clearField("FirstName");
		}
	}

	@Test(priority = 41)
	private void editmaxValidationContractorLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor Contractor] Last Name Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.lastName("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("LastName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("75.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("75.png");
			landing.clearField("LastName");
		}

	}

	@Test(priority = 42)
	private void editinvalidContractorEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Team User Contractor Contrctor form] invalid email is entered in Email Field");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.email("InValid");
		String mandatoryValidationFirstNameField = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ValidEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("ValidEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("76.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("76.png");
			landing.clearField("Email");
		}

	}

	@Test(priority = 43)
	private void editmaxValidationContractorEmailFields() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor Contractor] Email Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.email("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("77.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("77.png");
			landing.clearField("Email");
		}

	}

	@Test(priority = 44)
	private void editminValidatonContractorPhoneNumber() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Team User Contractor Contactor form]");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.phoneNumber("MinValidation");
		String mandatoryValidationFirstNameField = landing.errorField("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min6Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Min6Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("78.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("78.png");
			landing.clearField("PhoneNumber");

		}

	}

	@Test(priority = 45)
	private void editspecialCharacterPhoneNumberField() throws AWTException, InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when special character provided in Phone Number field");
		TeamUserPage minValidation = PageFactory.initElements(driver, TeamUserPage.class);
		minValidation.phoneNumber("SpecialCharacter");
		String errorPhoneNumber = minValidation.errorField("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + errorPhoneNumber);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterPhoneNumber"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (errorPhoneNumber.equals(getPropertyValue("SpecialCharacterPhoneNumber"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			minValidation.clearField("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("MinPhoneNumberValidation.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("MinPhoneNumberValidation.png");
			minValidation.clearField("PhoneNumber");
		}

	}

	@Test(priority = 46)
	private void editmaxValidatonContractorPhoneNumber() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor Contractor] Phone Number Field exceed its max-20 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.phoneNumber("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("PhoneNumber");
			landing.validateFillData("BasicContractor");
			landing.clickEvent("Next");
			landing.clearAllFields("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("79.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("79.png");
			landing.clearField("PhoneNumber");
			landing.validateFillData("BasicContractor");
			landing.clickEvent("Next");
			landing.clearAllFields("Location");
		}
	}

	@Test(priority = 47)
	private void editmaxValidationLocationName() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Location Name Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.locationName("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("LocationName");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("LocationName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("50.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("50.png");
			landing.clearField("LocationName");
		}

	}

	@Test(priority = 48)
	private void editmaxValidationAddress1() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Address1 Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.address1("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Address1");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Address1");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("51.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("51.png");
			landing.clearField("Address1");
		}

	}

	@Test(priority = 49)
	private void editmaxValidationAddress2() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Address2 Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.address2("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Address2");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Address2");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("52.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("52.png");
			landing.clearField("Address2");
		}

	}

	@Test(priority = 50)
	private void editmaxValidationState() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] State Name Field exceed its max-45 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.state("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("State");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max45CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max45CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("State");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("53.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("53.png");
			landing.clearField("State");
		}

	}

	@Test(priority = 51)
	private void editmaxValidationCity() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] City Name Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.city("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("City");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("City");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("54.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("54.png");
			landing.clearField("City");
		}

	}

	@Test(priority = 52)
	private void editminValidationZipcode() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Team User Contractor form]");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.zipcode("MinValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Zipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Min3CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Min3CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("55.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("55.png");
			landing.clearField("Zipcode");
		}

	}

	@Test(priority = 53)
	private void editspecialCharacterValidationZipcode() throws IOException {
		extentTest = extentReports.createTest("Verify the Special Character Validation Zipcode Field");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.zipcode("SpecialCharacter");
		String mandatoryValidationFirstNameField = landing.errorField("Zipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("SpecialCharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("SpecialCharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Zipcode");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("56.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("56.png");
			landing.clearField("Zipcode");
		}

	}

	@Test(priority = 54)
	private void editmaxValidationZipcode() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User Contractor] Zipcode Field exceed its max-10 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.zipcode("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("Zipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max10CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max10CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Zipcode");
			landing.validateFillData("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("57.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("57.png");
			landing.clearField("Zipcode");
			landing.validateFillData("Location");
		}

	}

	@Test(priority = 55)
	public void verifyUpdateExist() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Team User Contractot Updated Button is displayed in the Edit Form page");
		TeamUserPage contractorPage = PageFactory.initElements(driver, TeamUserPage.class);
		String text_button = contractorPage.clickEvent("ButtonPresent");
		extentTest.log(Status.INFO, "Actual Result is -" + text_button);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UpdatedButton"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (text_button.equals(getPropertyValue("UpdatedButton"))) {
			extentTest.log(Status.PASS, "Actual & Expected Results are Equal");
			contractorPage.clickEvent("SaveUpdate");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Results are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("26.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("26.png");
			contractorPage.clickEvent("SaveUpdate");
		}

	}

	@Test(priority = 56)
	private void userEdit() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify updated successful message is displayed, when the User Contractor Updated");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String createMessage = landing.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContractorUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("ContractorUpdatedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("58.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("58.png");
			landing.responseMessage("AlternateFunction");
		}
	}

	@Test(priority = 57)
	private void deleteUserDetails() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify deleted successful message is displayed, when the User Deleted");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("Delete");
		String responseMessage = landing.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("ContractorDeletedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("ContractorDeletedMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.dataConditionCheck("Condition");
			landing.dataConditionCheck("UserContractorCreate");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("67.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("67.png");
			landing.dataConditionCheck("Condition");
			landing.dataConditionCheck("UserContractorCreate");
		}

	}

}
