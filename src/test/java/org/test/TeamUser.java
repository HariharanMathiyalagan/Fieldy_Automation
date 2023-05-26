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
import com.zaigo.pageobjects.TeamUserPage;
import com.zaigo.pageobjects.CreateContractorPage;
import com.zaigo.pageobjects.LoginPage;
import com.zaigo.pageobjects.OnBoardingPage;
import com.zaigo.utility.BrowserSetup;

public class TeamUser extends BaseClass {
	private WebDriver driver = null;
	ExtentReports extentReports;
	ExtentHtmlReporter extentHtmlReporter;
	ExtentTest extentTest;
	static String listValidation;

	@BeforeClass
	public void setup() throws IOException {
		extentReports = new ExtentReports();
		extentHtmlReporter = new ExtentHtmlReporter("TeamUser.html");
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
	public void TeamUserPage() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the User to Land on the Team User List Page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		edit.clickEvent("Navigate");
		String assertionMessage = edit.labelValidation("ListLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamListLabel"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(getPropertyValue("TeamListLabel"))) {
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
		extentTest = extentReports
				.createTest("Verify the User to Land on Team User Create Page and Validate the Label");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String labelValidation = landing.labelValidation("FormLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + labelValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamCreateUserPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (labelValidation.equals(getPropertyValue("TeamCreateUserPage"))) {
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
	private void mandatoryValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Creation form page, First Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("SaveUpdate");
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
			File file = new File("40.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("40.png");
		}

	}

	@Test(priority = 4)
	private void mandatoryValidationTypeField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User creation form page, User Type field is set as Mandatory & Error Message is displayed when it is BLANK");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String mandatoryValidationFirstNameField = landing.errorField("UserType");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("MandatoryErrorMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("MandatoryErrorMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("42.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("42.png");
		}

	}

	@Test(priority = 5)
	private void mandatoryValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User creation form page, Email field is set as Mandatory & Error Message is displayed when it is BLANK");
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
			File file = new File("41.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("41.png");
		}

	}

	@Test(priority = 6)
	private void maxValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] First Name Field exceed its max-256 limit");
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
			File file = new File("43.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("43.png");
			landing.clearField("FirstName");
		}

	}

	@Test(priority = 7)
	private void maxValidationLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Last Name Field exceed its max-256 limit");
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
			File file = new File("44.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("44.png");
			landing.clearField("LastName");
		}

	}

	@Test(priority = 8)
	private void maxValidationJobTittleField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Job Tittle Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.jobTittle("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("JobTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("JobTittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("45.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("45.png");
			landing.clearField("JobTittle");
		}

	}

	@Test(priority = 9)
	private void maxValidationEmailFields() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Job Tittle Field exceed its max-256 limit");
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
			File file = new File("46.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("46.png");
			landing.clearField("Email");
		}

	}

	@Test(priority = 10)
	private void invalidEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Team User form] invalid email is entered in Email Field");
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
			File file = new File("47.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("47.png");
			landing.clearField("Email");

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
	private void minValidatonPhoneNumber() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Team User form]");
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
			File file = new File("48.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("48.png");
			landing.clearField("PhoneNumber");

		}

	}

	@Test(priority = 13)
	private void maxValidatonPhoneNumber() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Phone Number Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.phoneNumber("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("PhoneNumber");
			landing.validateFillData("Basic");
			landing.clickEvent("Next");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("49.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("49.png");
			landing.clearField("PhoneNumber");
			landing.validateFillData("Basic");
			landing.clickEvent("Next");
		}

	}

	@Test(priority = 14)
	private void maxValidationLocationName() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Location Name Field exceed its max-256 limit");
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
				"Verify Error Message is displayed when [Team User] Address1 Field exceed its max-256 limit");
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
				"Verify Error Message is displayed when [Team User] Address2 Field exceed its max-256 limit");
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
				"Verify Error Message is displayed when [Team User] State Name Field exceed its max-45 limit");
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
				"Verify Error Message is displayed when [Team User] City Name Field exceed its max-256 limit");
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
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Team User form]");
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
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when [Team User] Zipcode Field exceed its max-10 limit");
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
				"Verify the Contractor Company Save & Complete Button is displayed in the Create form page");
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
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UserCreatedMessgae"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("UserCreatedMessgae"))) {
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
		extentTest = extentReports.createTest("Verify the User Created Count is added in the User Admin Count");
		TeamUserPage create = PageFactory.initElements(driver, TeamUserPage.class);
		String actualTotal = create.actualResult("User");
		String expectedResult = create.totalCount("User");
		extentTest.log(Status.INFO, "Actual Result is - Total User Count:" + actualTotal);
		extentTest.log(Status.INFO, "Expected Result is - Total User Count:" + expectedResult);
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
		landing.email("Unique");
		String createUserList = landing.errorField("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + createUserList);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("AlreadyExistedEmail"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createUserList.equals(getPropertyValue("AlreadyExistedEmail"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clickEvent("Back");
			listValidation = landing.listValidation("FirstName");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("60.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("60.png");
			landing.clickEvent("Back");
			listValidation = landing.listValidation("FirstName");
		}
	}

	@Test(priority = 31)
	private void searchNameValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Enter the User Name:" + listValidation + "in the Search field & User Name list retrived successfully");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.listValidation("SearchData");
		String listFirstName = landing.listValidation("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listFirstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listFirstName)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Search");
			listValidation = landing.listValidation("PhoneNumber");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("61.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("61.png");
			Thread.sleep(20000);
			landing.clearField("Search");
			listValidation = landing.listValidation("PhoneNumber");
		}

	}

	@Test(priority = 32)
	private void searchPhoneNumberValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the User Phone Number:" + listValidation
				+ "in the Search field & User Phone Number list retrived successfully");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.listValidation("SearchData");
		String listPhoneNumber = landing.listValidation("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Search");
			listValidation = landing.listValidation("Email");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("62.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("62.png");
			Thread.sleep(20000);
			landing.clearField("Search");
			listValidation = landing.listValidation("Email");
		}

	}

	@Test(priority = 33)
	private void searchEmailValidation() throws IOException, InterruptedException {
		extentTest = extentReports.createTest("Enter the User Email:" + listValidation
				+ "in the Search field & User Email list retrived successfully");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.listValidation("SearchData");
		String listPhoneNumber = landing.listValidation("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + listValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + listPhoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (listValidation.equals(listPhoneNumber)) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("Search");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("63.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("63.png");
			Thread.sleep(20000);
			landing.clearField("Search");
		}

	}

	@Test(priority = 34)
	private void invalidDataValidation() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Enter the Invalid data in the Search field - No Result Found is dispayed");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.listValidation("Invalid");
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

	@Test(priority = 35)
	private void landingOnEditForm() throws IOException {
		extentTest = extentReports.createTest("Verify the User to Land on Team Edit Page and Validate the Label");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("Edit");
		String labelValidation = landing.labelValidation("FormLabel");
		extentTest.log(Status.INFO, "Actual Result is -" + labelValidation);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("TeamUserEditPage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (labelValidation.equals(getPropertyValue("TeamUserEditPage"))) {
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

	@Test(priority = 36)
	public void firstNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the First Name:" + TeamUserPage.firstName + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("FirstName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.firstName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.firstName)) {
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

	@Test(priority = 37)
	public void lastNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Last Name:" + TeamUserPage.lastName + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("LastName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.lastName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.lastName)) {
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

	@Test(priority = 38)
	public void emailPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Email:" + TeamUserPage.email + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("Email");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.email);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.email)) {
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
	@Test(priority = 38)
	public void jobTittlePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Job Tittle:" + TeamUserPage.jobTittle + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("JobTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.jobTittle);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.jobTittle)) {
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
	public void typePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Type Name:" + TeamUserPage.type + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("Type");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.type);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.type)) {
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
	public void phoneNumberPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Phone Number:" + TeamUserPage.phoneNumber + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.phoneNumber);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.phoneNumber)) {
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

	@Test(priority = 41)
	public void locationNamePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Location Name:" + TeamUserPage.locationName + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("LocationName");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.locationName);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.locationName)) {
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
	public void locationAddress1Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Location Address1:" + TeamUserPage.address1 + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("LocationAddress1");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.address1);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.address1)) {
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
	public void locationAddress2Prepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Location Address2:" + TeamUserPage.address2 + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("LocationAddress2");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.address2);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.address2)) {
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
	public void locationCityPrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Location City:" + TeamUserPage.city + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("LocationCity");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.city);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.city)) {
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
	public void locationStatePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports
				.createTest("Verify the Location State:" + TeamUserPage.state + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("LocationState");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.state);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.state)) {
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

	@Test(priority = 46)
	public void locationZipcodePrepopulate() throws InterruptedException, IOException {
		extentTest = extentReports.createTest(
				"Verify the Location Zipcode:" + TeamUserPage.zipcode + " is prepopulated in the team user edit form page");
		TeamUserPage edit = PageFactory.initElements(driver, TeamUserPage.class);
		String assertionMessage = edit.prepopulationFields("LocationZipcode");
		extentTest.log(Status.INFO, "Actual Result is -" + assertionMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + TeamUserPage.zipcode);
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (assertionMessage.equals(TeamUserPage.zipcode)) {
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

	@Test(priority = 47)
	private void editmandatoryValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User Creation form page, First Name field is set as Mandatory & Error Message is displayed when it is BLANK");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("SaveUpdate");
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
			File file = new File("40.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("40.png");
		}

	}

	@Test(priority = 48)
	private void editmandatoryValidationEmailField() throws IOException {
		extentTest = extentReports.createTest(
				"Check the User creation form page, Email field is set as Mandatory & Error Message is displayed when it is BLANK");
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
			File file = new File("41.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("41.png");
		}

	}

	@Test(priority = 49)
	private void editmaxValidationFirstNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] First Name Field exceed its max-256 limit");
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
			File file = new File("43.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("43.png");
			landing.clearField("FirstName");
		}

	}

	@Test(priority = 50)
	private void editmaxValidationLastNameField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Last Name Field exceed its max-256 limit");
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
			File file = new File("44.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("44.png");
			landing.clearField("LastName");
		}

	}

	@Test(priority = 51)
	private void editmaxValidationJobTittleField() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Job Tittle Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.jobTittle("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("JobTittle");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max256CharacterValidation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max256CharacterValidation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("JobTittle");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("45.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("45.png");
			landing.clearField("JobTittle");
		}

	}

	@Test(priority = 52)
	private void editmaxValidationEmailFields() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Job Tittle Field exceed its max-256 limit");
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
			File file = new File("46.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("46.png");
			landing.clearField("Email");
		}

	}

	@Test(priority = 53)
	private void editinvalidEmailValidation() throws IOException {
		extentTest = extentReports.createTest(
				"Verify error message is displayed when [Team User form] invalid email is entered in Email Field");
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
			File file = new File("47.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("47.png");
			landing.clearField("Email");

		}

	}

	@Test(priority = 54)
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

	@Test(priority = 55)
	private void editminValidatonPhoneNumber() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-6 limit is provided in Phone Number field of [Team User form]");
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
			File file = new File("48.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("48.png");
			landing.clearField("PhoneNumber");

		}

	}

	@Test(priority = 56)
	private void editmaxValidatonPhoneNumber() throws IOException, InterruptedException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Phone Number Field exceed its max-256 limit");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.phoneNumber("MaxValidation");
		String mandatoryValidationFirstNameField = landing.errorField("PhoneNumber");
		extentTest.log(Status.INFO, "Actual Result is -" + mandatoryValidationFirstNameField);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("Max20Validation"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (mandatoryValidationFirstNameField.equals(getPropertyValue("Max20Validation"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.clearField("PhoneNumber");
			landing.validateFillData("Basic");
			landing.clickEvent("Next");
			landing.clearAllFields("Location");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("49.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("49.png");
			landing.clearField("PhoneNumber");
			landing.validateFillData("Basic");
			landing.clickEvent("Next");
			landing.clearAllFields("Location");
		}

	}

	@Test(priority = 57)
	private void editmaxValidationLocationName() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Location Name Field exceed its max-256 limit");
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

	@Test(priority = 58)
	private void editmaxValidationAddress1() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Address1 Field exceed its max-256 limit");
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

	@Test(priority = 59)
	private void editmaxValidationAddress2() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] Address2 Field exceed its max-256 limit");
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

	@Test(priority = 60)
	private void editmaxValidationState() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] State Name Field exceed its max-45 limit");
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

	@Test(priority = 61)
	private void editmaxValidationCity() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when [Team User] City Name Field exceed its max-256 limit");
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

	@Test(priority = 62)
	private void editminValidationZipcode() throws IOException {
		extentTest = extentReports.createTest(
				"Verify Error Message is displayed when less than min-3 limit is provided in Zipcode field of [Team User form]");
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

	@Test(priority = 63)
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

	@Test(priority = 64)
	private void editmaxValidationZipcode() throws IOException, InterruptedException {
		extentTest = extentReports
				.createTest("Verify Error Message is displayed when [Team User] Zipcode Field exceed its max-10 limit");
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

	@Test(priority = 65)
	public void verifyUpdateExist() throws InterruptedException, IOException {
		extentTest = extentReports.createTest("Verify the Team User Updated Button is displayed in the Edit Form page");
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

	@Test(priority = 66)
	private void userEdit() throws IOException, AWTException, InterruptedException {
		extentTest = extentReports.createTest("Verify updated successful message is displayed, when the User Updated");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		String createMessage = landing.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + createMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UserUpdatedMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (createMessage.equals(getPropertyValue("UserUpdatedMessage"))) {
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

	@Test(priority = 67)
	private void deleteUserDetails() throws InterruptedException, AWTException, IOException {
		extentTest = extentReports.createTest("Verify deleted successful message is displayed, when the User Deleted");
		TeamUserPage landing = PageFactory.initElements(driver, TeamUserPage.class);
		landing.clickEvent("Delete");
		String responseMessage = landing.responseMessage("Message");
		extentTest.log(Status.INFO, "Actual Result is -" + responseMessage);
		extentTest.log(Status.INFO, "Expected Result is -" + getPropertyValue("UserDeleteMessage"));
		extentTest.log(Status.INFO, "Verification of Actual & Expected Validation");
		if (responseMessage.equals(getPropertyValue("UserDeleteMessage"))) {
			extentTest.log(Status.PASS, "Actual & Expected Validation are Equal");
			landing.dataConditionCheck("Condition");
			landing.dataConditionCheck("UserCreate");
		} else {
			extentTest.log(Status.FAIL, "Actual & Expected Validation are Not are Equal");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			File file = new File("67.png");
			FileHandler.copy(screenshotAs, file);
			extentTest.addScreenCaptureFromPath("67.png");
			landing.dataConditionCheck("Condition");
			landing.dataConditionCheck("UserCreate");
		}

	}

}
